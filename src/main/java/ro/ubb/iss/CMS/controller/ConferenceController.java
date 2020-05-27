package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.converter.ConferenceConverter;
import ro.ubb.iss.CMS.Services.ConferenceService;
import ro.ubb.iss.CMS.converter.ProposalConverter;
import ro.ubb.iss.CMS.converter.SectionConverter;
import ro.ubb.iss.CMS.converter.UserConverter;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ConferenceController {

  public static final Logger log = LoggerFactory.getLogger(ConferenceController.class);

  @Autowired private ConferenceService service;

  @Autowired private ConferenceConverter converter;
  @Autowired private ProposalConverter proposalConverter;
  @Autowired private UserConverter userConverter;
  @Autowired private SectionConverter sectionConverter;

  @RequestMapping(value = "/conferences", method = RequestMethod.GET)
  public ConferencesDto getAllConferences() {
    log.trace("getAllConferences - method entered");
    ConferencesDto result = new ConferencesDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllConferences - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/conferences/{id}", method = RequestMethod.GET)
  public ConferenceDto getConference(@PathVariable Integer id) {
    log.trace("getConference - method entered id={}", id);
    Optional<Conference> conference = service.findConference(id);
    ConferenceDto result = null;
    if (conference.isPresent()) result = converter.convertModelToDto(conference.get());
    log.trace("getConference - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/conferences/{id}/accepted", method = RequestMethod.GET)
  public ProposalsDto getConferenceAcceptedProposals(@PathVariable Integer id) {
    log.trace("getConferenceAccepted - method entered id={}", id);
    Optional<Conference> conference = service.findConference(id);
    ProposalsDto result = null;
    if (conference.isPresent())
      result =
          ProposalsDto.builder()
              .proposalDtoList(
                  proposalConverter.convertModelsToDtos(
                      conference.get().getProposalsForConference().stream()
                          .map(ConferenceProposal::getProposal)
                          .filter(
                              elem -> {
                                Set<Qualifier> qualifierSet =
                                    elem.getReviews().stream()
                                        .map(Review::getQualifier)
                                        .collect(Collectors.toSet());
                                if (qualifierSet.stream()
                                    .anyMatch(elem1 -> elem1.getName().equals("strong reject")))
                                  return false;
                                if (qualifierSet.stream()
                                    .anyMatch(elem1 -> elem1.getName().equals("reject")))
                                  return false;
                                if (qualifierSet.stream()
                                    .anyMatch(elem1 -> elem1.getName().equals("weak reject")))
                                  return false;
                                return true;
                              })
                          .collect(Collectors.toList())))
              .build();
    log.trace("getConferenceAccepted - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/conferences/{id}/refused", method = RequestMethod.GET)
  public ProposalsDto getConferenceRefusedProposals(@PathVariable Integer id) {
    log.trace("getConferenceRefusedProposals - method entered id={}", id);
    Optional<Conference> conference = service.findConference(id);
    ProposalsDto result = null;
    if (conference.isPresent())
      result =
          ProposalsDto.builder()
              .proposalDtoList(
                  proposalConverter.convertModelsToDtos(
                      conference.get().getProposalsForConference().stream()
                          .map(ConferenceProposal::getProposal)
                          .filter(
                              elem -> {
                                Set<Qualifier> qualifierSet =
                                    elem.getReviews().stream()
                                        .map(Review::getQualifier)
                                        .collect(Collectors.toSet());
                                if (qualifierSet.stream()
                                    .anyMatch(elem1 -> elem1.getName().equals("borderline paper")))
                                  return false;
                                if (qualifierSet.stream()
                                    .anyMatch(elem1 -> elem1.getName().equals("weak accept")))
                                  return false;
                                if (qualifierSet.stream()
                                    .anyMatch(elem1 -> elem1.getName().equals("accept")))
                                  return false;
                                if (qualifierSet.stream()
                                    .anyMatch(elem1 -> elem1.getName().equals("strong reject")))
                                  return false;
                                return true;
                              })
                          .collect(Collectors.toList())))
              .build();
    log.trace("getConferenceRefusedProposals - method finished: result={}", result);
    return result;
  }


  @RequestMapping(value = "/conferences/{id}/conflicting", method = RequestMethod.GET)
  public ProposalsDto getConferenceConflictingProposals(@PathVariable Integer id) {
    log.trace("getConferenceConflictingProposals - method entered id={}", id);
    Optional<Conference> conference = service.findConference(id);
    ProposalsDto result = null;
    if (conference.isPresent())
      result =
              ProposalsDto.builder()
                      .proposalDtoList(
                              proposalConverter.convertModelsToDtos(
                                      conference.get().getProposalsForConference().stream()
                                              .map(ConferenceProposal::getProposal)
                                              .filter(
                                                      elem -> {
                                                        Set<Qualifier> qualifierSet =
                                                                elem.getReviews().stream()
                                                                        .map(Review::getQualifier)
                                                                        .collect(Collectors.toSet());
                                                        boolean positive = qualifierSet.stream()
                                                                .anyMatch(elem1 -> elem1.getQualifierID() >= 4);
                                                        boolean negative = qualifierSet.stream()
                                                                .anyMatch(elem1 -> elem1.getQualifierID() <= 3);

                                                        return positive && negative;

                                                      })
                                              .collect(Collectors.toList())))
                      .build();
    log.trace("getConferenceConflictingProposals - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/conferences/{id}/pc_members", method = RequestMethod.GET)
  @Transactional
  public UsersDto getConferencePcMembers(@PathVariable Integer id) {
    log.trace("getConferencePcMembers - method entered id={}", id);
    Optional<Conference> conference = service.findConference(id);
    UsersDto result = null;
    if (conference.isPresent()) {
      result =
          UsersDto.builder()
              .userDtoList(
                  userConverter.convertModelsToDtos(
                      conference.get().getPcMembers().stream()
                          .map(PcMember::getUser)
                          .collect(Collectors.toList())))
              .build();
    }

    log.trace("getConferencePcMembers - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/conferences/{id}/sections", method = RequestMethod.GET)
  @Transactional
  public SectionsDto getConferenceSections(@PathVariable Integer id) {
    log.trace("getConferenceSections - method entered id={}", id);
    Optional<Conference> conference = service.findConference(id);
    SectionsDto result = null;
    if (conference.isPresent()) {
      result =
          SectionsDto.builder()
              .sectionDtoList(sectionConverter.convertModelsToDtos(conference.get().getSections()))
              .build();
    }
    log.trace("getConferenceSections - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/conferences", method = RequestMethod.POST)
  public ConferenceDto saveConference(@RequestBody ConferenceDto conferenceDto) {
    log.trace("saveConference - method entered conferenceDto={}", conferenceDto);
    Conference result =
        service.saveConference(
            conferenceDto.getName(),
            conferenceDto.getStartDate(),
            conferenceDto.getEndDate(),
            conferenceDto.getProposalDeadline(),
            conferenceDto.getPaperDeadline());

    ConferenceDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveConference - method finished: result={}", resultToReturn);
    return resultToReturn;
  }

  @RequestMapping(value = "/conferences", method = RequestMethod.PUT)
  public ConferenceDto updateConference(@RequestBody ConferenceDto conferenceDto) {
    log.trace("updateConference - method entered: conferenceDto={}", conferenceDto);
    ConferenceDto result =
        converter.convertModelToDto(
            service.updateConference(
                conferenceDto.getConferenceID(),
                conferenceDto.getName(),
                conferenceDto.getStartDate(),
                conferenceDto.getEndDate(),
                conferenceDto.getProposalDeadline(),
                conferenceDto.getPaperDeadline()));
    log.trace("updateConference - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/conferences/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteConference(@PathVariable Integer id) {
    log.trace("deleteConference - method entered: id={}", id);

    try {
      service.deleteConference(id);
    } catch (RestClientException ex) {
      log.trace("deleteConference - exception caught ex={}", ex.getMessage());
      log.trace("deleteConference - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteConference - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
