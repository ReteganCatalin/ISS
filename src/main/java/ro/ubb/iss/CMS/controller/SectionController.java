package ro.ubb.iss.CMS.controller;

import liquibase.pro.packaged.A;
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
import ro.ubb.iss.CMS.converter.*;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class SectionController {

  public static final Logger log = LoggerFactory.getLogger(SectionController.class);

  @Autowired private SectionService service;

  @Autowired private SectionConverter converter;
  @Autowired private PresentationConverter presentationConverter;
  @Autowired private UserConverter userConverter;
  @Autowired private ProposalConverter proposalConverter;
  @Autowired private MetaInfoConverter metaInfoConverter;
  @Autowired private UserInfoConverter userInfoConverter;

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @RequestMapping(value = "/sections", method = RequestMethod.GET)
  public ResponseEntity<SectionsDto> getAllSections() {
    log.trace("getAllSections - method entered");
    SectionsDto result = new SectionsDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllSections - method finished: result={}", result);
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/sections/{id}", method = RequestMethod.GET)
  public ResponseEntity<SectionDto> getSection(@PathVariable Integer id) {
    log.trace("getSection - method entered id={}", id);
    Optional<Section> anAbstract = service.findSection(id);
    SectionDto result = null;
    if (anAbstract.isPresent()) result = converter.convertModelToDto(anAbstract.get());
    log.trace("getSection - method finished: result={}", result);
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/sections/{id}/presentations", method = RequestMethod.GET)
  @Transactional
  public ResponseEntity<PresentationsDto> getSectionPresentations(@PathVariable Integer id) {
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
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/sections/conference/{conferenceID}", method = RequestMethod.GET)
  public List<Object> getSectionWithAllData(@PathVariable Integer conferenceID) {
    return this.service.findAll().stream()
            .filter(section -> section.getConference().getConferenceID().equals(conferenceID))
            .map(section -> {
              HashMap<String, Object> map = new HashMap<>();
              map.put("section", this.converter.convertModelToDto(section));
              map.put("supervisor", this.userConverter.convertModelToDto(section.getSupervisor()));
              map.put("proposals",section.getProposalLists().stream()
                      .map(proposalList -> {
                        HashMap<String, Object> proposalMap = new HashMap<>();
                        proposalMap.put("proposal",this.proposalConverter.convertModelToDto(proposalList.getProposal()));
                        proposalMap.put("meta_info", this.metaInfoConverter.convertModelToDto(proposalList.getProposal().getMetaInformation()));
                        proposalMap.put("user_info", this.userInfoConverter.convertModelToDto(proposalList.getProposal().getUserInfo()));
                        return proposalMap;
                      })
                      .collect(Collectors.toList()));
              return map;
            })
            .collect(Collectors.toList());
  }

  @RequestMapping(value = "/sections/{id}/suprevisor", method = RequestMethod.GET)
  @Transactional
  public ResponseEntity<UserDto> getSectionSupervisor(@PathVariable Integer id) {
    log.trace("getSectionSupervisor - method entered id={}", id);
    Optional<Section> section = service.findSection(id);
    UserDto result = null;
    if (section.isPresent())
      result = userConverter.convertModelToDto(section.get().getSupervisor());
    log.trace("getSectionSupervisor - method finished: result={}", result);
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/sections", method = RequestMethod.POST)
  public ResponseEntity<SectionDto> saveSection(@RequestBody SectionDto sectionDto) {
    log.trace("saveSection - method entered sectionDto={}", sectionDto);
    Section result =
        service.saveSection(
            entityManager.getReference(User.class, sectionDto.getSupervisorID()),
            entityManager.getReference(Conference.class, sectionDto.getConferenceID()),
            sectionDto.getDateOfPresentation());

    SectionDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveSection - method finished: result={}", resultToReturn);
    return new ResponseEntity(resultToReturn,HttpStatus.OK);
  }

  @RequestMapping(value = "/sections", method = RequestMethod.PUT)
  public ResponseEntity<SectionDto> updateSection(@RequestBody SectionDto sectionDto) {
    log.trace("updateSection - method entered: sectionDto={}", sectionDto);
    SectionDto result =
        converter.convertModelToDto(
            service.updateSection(
                sectionDto.getSectionID(),
                entityManager.getReference(User.class, sectionDto.getSupervisorID()),
                entityManager.getReference(Conference.class, sectionDto.getConferenceID()),
                sectionDto.getDateOfPresentation()));
    log.trace("updateSection - method finished: result={}", result);
    return new ResponseEntity(result,HttpStatus.OK);
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
