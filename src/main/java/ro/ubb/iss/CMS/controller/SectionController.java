package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.AbstractService;
import ro.ubb.iss.CMS.Services.SectionService;
import ro.ubb.iss.CMS.converter.AbstractConverter;
import ro.ubb.iss.CMS.converter.PresentationConverter;
import ro.ubb.iss.CMS.converter.SectionConverter;
import ro.ubb.iss.CMS.converter.UserConverter;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
public class SectionController {

  public static final Logger log = LoggerFactory.getLogger(SectionController.class);

  @Autowired private SectionService service;

  @Autowired private SectionConverter converter;
  @Autowired private PresentationConverter presentationConverter;
  @Autowired private UserConverter userConverter;

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @RequestMapping(value = "/sections", method = RequestMethod.GET)
  public SectionsDto getAllSections() {
    log.trace("getAllSections - method entered");
    SectionsDto result = new SectionsDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllSections - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/sections/{id}", method = RequestMethod.GET)
  public SectionDto getSection(@PathVariable Integer id) {
    log.trace("getSection - method entered id={}", id);
    Optional<Section> anAbstract = service.findSection(id);
    SectionDto result = null;
    if (anAbstract.isPresent()) result = converter.convertModelToDto(anAbstract.get());
    log.trace("getSection - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/sections/{id}/presentations", method = RequestMethod.GET)
  @Transactional
  public PresentationsDto getSectionPresentations(@PathVariable Integer id) {
    log.trace("getSectionPresentations - method entered id={}", id);
    Optional<Section> section = service.findSection(id);
    PresentationsDto result = null;
    if (section.isPresent())
      result =
          PresentationsDto.builder()
              .presentationDtoList(
                  presentationConverter.convertModelsToDtos(section.get().getPresentations()))
              .build();
    log.trace("getSectionPresentations - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/sections/{id}/suprevisor", method = RequestMethod.GET)
  @Transactional
  public UserDto getSectionSupervisor(@PathVariable Integer id) {
    log.trace("getSectionSupervisor - method entered id={}", id);
    Optional<Section> section = service.findSection(id);
    UserDto result = null;
    if (section.isPresent())
      result = userConverter.convertModelToDto(section.get().getSupervisor());
    log.trace("getSectionSupervisor - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/sections", method = RequestMethod.POST)
  public SectionDto saveSection(@RequestBody SectionDto sectionDto) {
    log.trace("saveSection - method entered sectionDto={}", sectionDto);
    Section result =
        service.saveSection(
            entityManager.getReference(User.class, sectionDto.getSupervisorID()),
            entityManager.getReference(Conference.class, sectionDto.getConferenceID()),
            sectionDto.getDateOfPresentation());

    SectionDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveSection - method finished: result={}", resultToReturn);
    return resultToReturn;
  }

  @RequestMapping(value = "/sections", method = RequestMethod.PUT)
  public SectionDto updateSection(@RequestBody SectionDto sectionDto) {
    log.trace("updateSection - method entered: sectionDto={}", sectionDto);
    SectionDto result =
        converter.convertModelToDto(
            service.updateSection(
                sectionDto.getSectionID(),
                entityManager.getReference(User.class, sectionDto.getSupervisorID()),
                entityManager.getReference(Conference.class, sectionDto.getConferenceID()),
                sectionDto.getDateOfPresentation()));
    log.trace("updateSection - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/sections/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteSection(@PathVariable Integer id) {
    log.trace("deleteSection - method entered: id={}", id);
    try {
      service.deleteSection(id);
    } catch (RestClientException ex) {
      log.trace("deleteSection - exception caught ex={}", ex.getMessage());
      log.trace("deleteSection - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteSection - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
