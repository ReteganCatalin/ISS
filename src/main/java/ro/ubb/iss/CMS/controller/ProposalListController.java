package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.ReviewService;
import ro.ubb.iss.CMS.Services.ProposalListService;
import ro.ubb.iss.CMS.converter.ReviewConverter;
import ro.ubb.iss.CMS.converter.ProposalListConverter;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.ReviewDto;
import ro.ubb.iss.CMS.dto.ReviewsDto;
import ro.ubb.iss.CMS.dto.ProposalListDto;
import ro.ubb.iss.CMS.dto.ProposalListsDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
public class ProposalListController {
  public static final Logger log = LoggerFactory.getLogger(ProposalListController.class);

  @Autowired private ProposalListService service;

  @Autowired private ProposalListConverter converter;

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @RequestMapping(value = "/proposalLists", method = RequestMethod.GET)
  public ProposalListsDto getAllProposalLists() {
    log.trace("getAllProposalLists - method entered");
    ProposalListsDto result =
        new ProposalListsDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllProposalLists - method finished: result={}", result);
    return result;
  }

  @RequestMapping(
      value = "/proposalLists/{sectionID}/{proposalID}",
      method = RequestMethod.GET)
  public ProposalListDto getProposalList(
      @PathVariable Integer sectionID,
      @PathVariable Integer proposalID) {
    log.trace(
        "getProposalList - method entered sectionID={} proposalID={}",
        sectionID,
        proposalID);
    ProposalListKey proposalListKey = new ProposalListKey(sectionID, proposalID);
    Optional<ProposalList> anAbstract = service.findProposalList(proposalListKey);
    ProposalListDto result = null;
    if (anAbstract.isPresent()) result = converter.convertModelToDto(anAbstract.get());
    log.trace("getProposalList - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/proposalLists", method = RequestMethod.POST)
  public ProposalListDto saveProposalList(@RequestBody ProposalListKey proposalListKey) {
    log.trace("saveProposalList - method entered proposalListKey={}", proposalListKey);
    Optional<ProposalList> result =
        service.saveProposal(
            new ProposalListKey(
                proposalListKey.getSectionID(),
                proposalListKey.getProposalID()));
    ProposalListDto resultToReturn = null;
    if (result.isPresent()) resultToReturn = converter.convertModelToDto(result.get());
    log.trace("saveProposalList - method finished: result={}", resultToReturn);
    return resultToReturn;
  }

  @RequestMapping(
      value = "/proposalLists/{sectionID}/{proposalID}/{conferenceID}",
      method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteProposalList(
      @PathVariable Integer sectionID,
      @PathVariable Integer proposalID,
      @PathVariable Integer conferenceID) {
    log.trace(
        "deleteProposalList - method entered: sectionID={} proposalID={} conferenceID={}",
        sectionID,
        proposalID,
        conferenceID);
    ProposalListKey proposalListKey = new ProposalListKey(sectionID, proposalID);

    try {
      service.deleteProposal(proposalListKey);
    } catch (RestClientException ex) {
      log.trace("deleteProposalList - exception caught ex={}", ex.getMessage());
      log.trace("deleteProposalList - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteProposalList - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
