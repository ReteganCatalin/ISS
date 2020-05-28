package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.converter.ParticipationConverter;
import ro.ubb.iss.CMS.Services.ParticipationService;
import ro.ubb.iss.CMS.domain.Participation;
import ro.ubb.iss.CMS.domain.Role;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.dto.ParticipationDto;
import ro.ubb.iss.CMS.dto.ParticipationsDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
public class ParticipationController {

  public static final Logger log = LoggerFactory.getLogger(ParticipationController.class);

  @Autowired private ParticipationService service;

  @Autowired private ParticipationConverter converter;

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @RequestMapping(value = "/participations", method = RequestMethod.GET)
  public ResponseEntity<ParticipationsDto> getAllParticipations() {
    log.trace("getAllParticipations - method entered");
    ParticipationsDto result =
        new ParticipationsDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllParticipations - method finished: result={}", result);
    return new ResponseEntity<>(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/participations/{id}", method = RequestMethod.GET)
  public ResponseEntity<ParticipationDto> getParticipation(@PathVariable Integer id) {
    log.trace("getParticipation - method entered id={}", id);
    Optional<Participation> metaInformation = service.findParticipation(id);
    ParticipationDto result = null;
    if (metaInformation.isPresent()) result = converter.convertModelToDto(metaInformation.get());
    log.trace("getParticipation - method finished: result={}", result);
    return new ResponseEntity<>(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/participations", method = RequestMethod.POST)
  public ResponseEntity<ParticipationDto> saveParticipation(@RequestBody ParticipationDto participationDto) {
    log.trace("saveParticipation - method entered participationDto={}", participationDto);
    Participation result =
        service.saveParticipation(
            entityManager.getReference(Section.class, participationDto.getSectionID()),
            entityManager.getReference(User.class, participationDto.getSectionID()),
            entityManager.getReference(Role.class, participationDto.getSectionID()));
    ParticipationDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveParticipation - method finished: result={}", resultToReturn);
    return new ResponseEntity<>(resultToReturn,HttpStatus.OK);
  }

  @RequestMapping(value = "/participations", method = RequestMethod.PUT)
  public ResponseEntity<ParticipationDto> updateParticipation(@RequestBody ParticipationDto participationDto) {
    log.trace("updateParticipation - method entered: participationDto={}", participationDto);
    ParticipationDto result =
        converter.convertModelToDto(
            service.updateParticipation(
                participationDto.getParticipantListID(),
                entityManager.getReference(Section.class, participationDto.getSectionID()),
                entityManager.getReference(User.class, participationDto.getSectionID()),
                entityManager.getReference(Role.class, participationDto.getSectionID())));
    log.trace("updateParticipation - method finished: result={}", result);
    return new ResponseEntity<>(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/participations/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteParticipation(@PathVariable Integer id) {
    log.trace("deleteParticipation - method entered: id={}", id);

    try {
      service.deleteParticipation(id);
    } catch (RestClientException ex) {
      log.trace("deleteParticipation - exception caught ex={}", ex.getMessage());
      log.trace("deleteParticipation - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteParticipation - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
