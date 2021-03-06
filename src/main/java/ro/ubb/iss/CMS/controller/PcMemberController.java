package ro.ubb.iss.CMS.controller;

import liquibase.pro.packaged.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.PcMemberService;
import ro.ubb.iss.CMS.converter.ConferenceConverter;
import ro.ubb.iss.CMS.converter.PcMemberConverter;
import ro.ubb.iss.CMS.converter.UserConverter;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.PcMember;
import ro.ubb.iss.CMS.domain.PcMemberKey;
import ro.ubb.iss.CMS.dto.ConferenceDto;
import ro.ubb.iss.CMS.dto.PcMemberDto;
import ro.ubb.iss.CMS.dto.PcMembersDto;
import ro.ubb.iss.CMS.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PcMemberController {

  public static final Logger log = LoggerFactory.getLogger(ConferenceController.class);

  @Autowired private PcMemberService service;

  @Autowired private PcMemberConverter converter;
  @Autowired private UserConverter userConverter;
  @Autowired private ConferenceConverter conferenceConverter;

  @RequestMapping(value = "/pc_members", method = RequestMethod.GET)
  public ResponseEntity<PcMembersDto> getAllPcMembers() {
    log.trace("getAllPcMembers - method entered");
    PcMembersDto result = new PcMembersDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllPcMembers - method finished: result={}", result);
    return new ResponseEntity<>(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/pc_members/{conferenceID}", method = RequestMethod.GET)
  public List<UserDto> getPcMembersOfConference(@PathVariable Integer conferenceID) {
      return this.service.findAll().stream()
              .filter(pcMember -> pcMember.getConference().getConferenceID().equals(conferenceID))
              .map(pcMember -> userConverter.convertModelToDto(pcMember.getUser()))
              .collect(Collectors.toList());
  }

  @RequestMapping(value = "/pc_members/conferences/{userID}")
  public List<ConferenceDto> getConferencesOfUser(@PathVariable Integer userID) {
    return this.service.findAll().stream()
            .filter(pcMember -> pcMember.getUser().getUserID().equals(userID))
            .map(pcMember -> conferenceConverter.convertModelToDto(pcMember.getConference()))
            .collect(Collectors.toList());
  }
  @RequestMapping(value = "/pc_members/{userId}/{conferenceId}", method = RequestMethod.GET)
  public ResponseEntity<PcMemberDto> getPcMember(@PathVariable Integer userId, @PathVariable Integer conferenceId) {
    log.trace("getPcMember - method entered userId={}, conferenceId={}", userId, conferenceId);
    PcMemberKey pcMemberKey =
        PcMemberKey.builder().userID(userId).conferenceID(conferenceId).build();
    Optional<PcMember> pcMember = service.findPcMember(pcMemberKey);
    PcMemberDto result = null;
    if (pcMember.isPresent()) result = converter.convertModelToDto(pcMember.get());
    log.trace("getPcMember - method finished: result={}", result);
    return new ResponseEntity<>(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/pc_members", method = RequestMethod.POST)
  public ResponseEntity<PcMemberDto> savePcMember(@RequestBody PcMemberDto pcMemberDto) {
    log.trace("savePcMember - method entered pcMemberDto={}", pcMemberDto);
    Optional<PcMember> result =
        service.savePcMember(
            PcMemberKey.builder()
                .conferenceID(pcMemberDto.getConferenceID())
                .userID(pcMemberDto.getUserID())
                .build());

    PcMemberDto resultToReturn = null;
    if (result.isPresent()) resultToReturn = converter.convertModelToDto(result.get());
    log.trace("savePcMember - method finished: result={}", resultToReturn);
    return new ResponseEntity<>(resultToReturn,HttpStatus.OK);
  }

  @RequestMapping(value = "/pc_members/{userId}/{conferenceId}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deletePcMember(
      @PathVariable Integer userId, @PathVariable Integer conferenceId) {
    log.trace("deletePcMember - method entered: userId={}, conferenceId={}", userId, conferenceId);
    PcMemberKey pcMemberKey =
        PcMemberKey.builder().userID(userId).conferenceID(conferenceId).build();

    try {
      service.deletePcMember(pcMemberKey);
    } catch (RestClientException ex) {
      log.trace("deletePcMember - exception caught ex={}", ex.getMessage());
      log.trace("deletePcMember - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deletePcMember - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
