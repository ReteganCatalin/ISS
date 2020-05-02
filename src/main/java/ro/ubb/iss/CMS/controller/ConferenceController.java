package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.converter.ConferenceConverter;
import ro.ubb.iss.CMS.Services.ConferenceService;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.dto.ConferenceDto;
import ro.ubb.iss.CMS.dto.ConferencesDto;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ConferenceController {

  public static final Logger log = LoggerFactory.getLogger(ConferenceController.class);

  @Autowired private ConferenceService service;

  @Autowired private ConferenceConverter converter;

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
