package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.AbstractService;
import ro.ubb.iss.CMS.Services.PaperService;
import ro.ubb.iss.CMS.Services.PresentationService;
import ro.ubb.iss.CMS.converter.AbstractConverter;
import ro.ubb.iss.CMS.converter.PaperConverter;
import ro.ubb.iss.CMS.converter.PresentationConverter;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.AbstractsDto;
import ro.ubb.iss.CMS.dto.PresentationDto;
import ro.ubb.iss.CMS.dto.PresentationsDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
public class PresentationController {

  public static final Logger log = LoggerFactory.getLogger(PresentationController.class);

  @Autowired private PresentationService service;

  @Autowired private PresentationConverter converter;

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @RequestMapping(value = "/presentations", method = RequestMethod.GET)
  public PresentationsDto getAllPresentations() {
    log.trace("getAllPresentations - method entered");
    PresentationsDto result =
        new PresentationsDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllPresentations - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/presentations/{id}", method = RequestMethod.GET)
  public PresentationDto getPresentation(@PathVariable Integer id) {
    log.trace("getPresentation - method entered id={}", id);
    Optional<Presentation> presentation = service.findPresentation(id);
    PresentationDto result = null;
    if (presentation.isPresent()) result = converter.convertModelToDto(presentation.get());
    log.trace("getPresentation - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/presentations", method = RequestMethod.POST)
  public PresentationDto savePresentation(@RequestBody PresentationDto presentationDto) {
    log.trace("savePresentation - method entered presentationDto={}", presentationDto);
    Presentation result =
        service.savePresentation(
            entityManager.getReference(Section.class, presentationDto.getSectionID()),
            entityManager.getReference(Conference.class, presentationDto.getConferenceID()),
            presentationDto.getFormat(),
            presentationDto.getByteFileLocation());

    PresentationDto resultToReturn = converter.convertModelToDto(result);
    log.trace("savePresentation - method finished: result={}", resultToReturn);
    return resultToReturn;
  }

  @RequestMapping(value = "/presentations", method = RequestMethod.PUT)
  public PresentationDto updatePresentation(@RequestBody PresentationDto presentationDto) {
    log.trace("updatePresentation - method entered: presentationDto={}", presentationDto);
    PresentationDto result =
        converter.convertModelToDto(
            service.updatePresentation(
                presentationDto.getPresentationID(),
                entityManager.getReference(Section.class, presentationDto.getSectionID()),
                entityManager.getReference(Conference.class, presentationDto.getConferenceID()),
                presentationDto.getFormat(),
                presentationDto.getByteFileLocation()));
    log.trace("updatePresentation - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/presentations/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deletePresentation(@PathVariable Integer id) {
    log.trace("deletePresentation - method entered: id={}", id);
    try {
      service.deletePresentation(id);
    } catch (RestClientException ex) {
      log.trace("deletePresentation - exception caught ex={}", ex.getMessage());
      log.trace("deletePresentation - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deletePresentation - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
