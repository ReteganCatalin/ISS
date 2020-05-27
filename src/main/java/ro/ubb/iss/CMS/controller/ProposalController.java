package ro.ubb.iss.CMS.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.AbstractService;
import ro.ubb.iss.CMS.Services.ProposalService;
import ro.ubb.iss.CMS.converter.*;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProposalController {

  public static final Logger log = LoggerFactory.getLogger(ProposalController.class);

  @Autowired private ProposalService service;

  @Autowired private ProposalConverter converter;
  @Autowired private ReviewConverter reviewConverter;
  @Autowired private UserConverter userConverter;
  @Autowired private MetaInfoConverter metaInfoConverter;
  @Autowired private SectionConverter sectionConverter;

  @Autowired ObjectMapper mapper;

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @RequestMapping(value = "/proposals/{id}/detailed", method = RequestMethod.GET)
  public Map<String, Object> getDetailedProposal(@PathVariable Integer id) {
    log.trace("getDetailedProposal - method entered");
    Optional<Proposal> proposal = service.findProposal(id);
    Map<String, Object> result = new HashMap<>();
    if (proposal.isPresent()) {
      result.put("proposal_id",proposal.get().getProposalID());
      result.put("proposal_proper_info",converter.convertModelToDto(proposal.get()));
      result.put(
          "author_list",
          proposal.get().getAuthors().stream().map(Author::getName).collect(Collectors.toList()));
      result.put(
          "section",
          proposal.get().getProposalListsForSections().stream()
              .map(elem -> sectionConverter.convertModelToDto(elem.getSection()))
              .collect(Collectors.toList()));
      result.put(
          "meta_info", metaInfoConverter.convertModelToDto(proposal.get().getMetaInformation()));
      result.put("abstract_location", proposal.get().getAnAbstract().getByteFileLocation());
      result.put("paper_location", proposal.get().getPaper().getByteFileLocation());
      result.put(
          "qualifier",
          proposal.get().getReviews().stream()
              .map(elem -> elem.getQualifier().getName())
              .collect(Collectors.toList()));
    }
    log.trace("getDetailedProposal - method finished: result={}", result);
    return result;
  }

  //  @RequestMapping(value = "/proposals/{id}/detailed", method = RequestMethod.GET)
  //  public Map<String, List<String>> getDetailedProposal(@PathVariable Integer id) {
  //    log.trace("getDetailedProposal - method entered");
  //    Optional<Proposal> proposal = service.findProposal(id);
  //    Map<String,List<String>> result = new HashMap<>();
  //    if (proposal.isPresent()){
  //
  // result.put("author_list",proposal.get().getAuthors().stream().map(a->a.getName()).collect(Collectors.toList()));
  //
  // result.put("section",proposal.get().getProposalListsForSections().stream().map(elem->elem.getSection()).map(elem->elem.getSectionID().toString()+";"+elem.getDateOfPresentation().toString()).collect(Collectors.toList()));
  ////      result.put("meta_info",proposal.get().getMetaInformation());
  //
  //    }
  //    log.trace("getDetailedProposal - method finished: result={}", result);
  //    return result;
  //  }

  @RequestMapping(value = "/proposals", method = RequestMethod.GET)
  public ProposalsDto getAllProposals() {
    log.trace("getAllProposals - method entered");
    ProposalsDto result = new ProposalsDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllProposals - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/proposals/{id}", method = RequestMethod.GET)
  public ProposalDto getProposal(@PathVariable Integer id) {
    log.trace("getProposal - method entered id={}", id);
    Optional<Proposal> proposal = service.findProposal(id);
    ProposalDto result = null;
    if (proposal.isPresent()) result = converter.convertModelToDto(proposal.get());
    log.trace("getProposal - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/proposals/{id}/reviews", method = RequestMethod.GET)
  @Transactional
  public ReviewsDto getProposalReviews(@PathVariable Integer id) {
    log.trace("getProposalReviews - method entered id={}", id);
    Optional<Proposal> proposal = service.findProposal(id);
    ReviewsDto result = null;
    if (proposal.isPresent())
      result =
          ReviewsDto.builder()
              .reviewDtoList(reviewConverter.convertModelsToDtos(proposal.get().getReviews()))
              .build();
    log.trace("getProposalReviews - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/proposals/{id}/reviewer_users", method = RequestMethod.GET)
  @Transactional
  public UsersDto getProposalReviewerUsers(@PathVariable Integer id) {
    log.trace("getProposalReviewerUsers - method entered id={}", id);
    Optional<Proposal> proposal = service.findProposal(id);
    UsersDto result = null;
    if (proposal.isPresent())
      result =
          UsersDto.builder()
              .userDtoList(
                  userConverter.convertModelsToDtos(
                      proposal.get().getReviews().stream()
                          .map(Review::getUser)
                          .collect(Collectors.toList())))
              .build();
    log.trace("getProposalReviewerUsers - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/proposals/{id}/available", method = RequestMethod.GET)
  @Transactional
  public UsersDto getAllAvailable(@PathVariable Integer id) {
    log.trace("getAllAvailable - method entered id={}", id);
    Optional<Proposal> proposal = service.findProposal(id);
    UsersDto result = null;
    if (proposal.isPresent())
      result =
          UsersDto.builder()
              .userDtoList(
                  userConverter.convertModelsToDtos(
                      proposal.get().getReviews().stream()
                          .map(Review::getUser)
                          .collect(Collectors.toList())))
              .build();
    log.trace("getAllAvailable - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/proposals", method = RequestMethod.POST)
  public ProposalDto saveProposal(@RequestBody ProposalDto proposalDto) {
    log.trace("saveProposal - method entered abstractDto={}", proposalDto);
    Proposal result =
        service.saveProposal(
            entityManager.getReference(UserInfo.class, proposalDto.getUserInfoID()),
            entityManager.getReference(Paper.class, proposalDto.getPaperID()),
            entityManager.getReference(MetaInformation.class, proposalDto.getMetaInfoID()),
            entityManager.getReference(Abstract.class, proposalDto.getAbstractID()));

    ProposalDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveProposal - method finished: result={}", resultToReturn);
    return resultToReturn;
  }

  @RequestMapping(value = "/proposals", method = RequestMethod.PUT)
  public ProposalDto updateProposal(@RequestBody ProposalDto proposalDto) {
    log.trace("updateProposal - method entered: proposalDto={}", proposalDto);
    ProposalDto result =
        converter.convertModelToDto(
            service.updateProposal(
                proposalDto.getProposalID(),
                entityManager.getReference(UserInfo.class, proposalDto.getUserInfoID()),
                entityManager.getReference(Paper.class, proposalDto.getPaperID()),
                entityManager.getReference(MetaInformation.class, proposalDto.getMetaInfoID()),
                entityManager.getReference(Abstract.class, proposalDto.getAbstractID())));
    log.trace("updateProposal - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/proposals/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteProposal(@PathVariable Integer id) {
    log.trace("deleteProposal - method entered: id={}", id);
    try {
      service.deleteProposal(id);
    } catch (RestClientException ex) {
      log.trace("deleteProposal - exception caught ex={}", ex.getMessage());
      log.trace("deleteProposal - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteProposal - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
