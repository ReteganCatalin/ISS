package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.iss.CMS.Repository.ProposalListRepository;
import ro.ubb.iss.CMS.domain.*;

import java.util.List;
import java.util.Optional;

public class ProposalListServiceImplementation implements ProposalListService {
  private static final Logger log = LoggerFactory.getLogger(ProposalListServiceImplementation.class);

  @Autowired
  private ProposalListRepository proposalListRepository;
  @Override
  public Optional<ProposalList> findProposalList(ProposalListKey proposalKey) {
    log.trace("findProposalList - method entered userInfoID={}", proposalKey);
    Optional<ProposalList> result = proposalListRepository.findById(proposalKey);
    log.trace("findProposalList - method exit result={}", result);
    return result;
  }

  @Override
  public List<ProposalList> findAll() {
    log.trace("findAll - method entered");
    List<ProposalList> result = proposalListRepository.findAll();
    log.trace("findAll - method exit result={}", result);
    return result;
  }

//  @Override
//  public ProposalList updateProposal(
//      ProposalListKey proposalKey) {
//    log.trace(
//            "updateProposal - method entered: proposalKey={}, briefAnalysis={}, refuse={}",
//            proposalKey,
//            briefAnalysis,
//            refuse);
//
//    Optional<ProposalList> abstractOptional = proposalListRepository.findById(proposalKey);
//
//    abstractOptional.ifPresent(
//            newProposalList -> {
//              newProposalList.setProposal(briefAnalysis);
//              newProposalList.setRefuse(refuse);
//              log.debug("updateProposal - updated: newProposalList={}", newProposalList);
//            });
//    log.trace("updateProposal - method finished result={}", abstractOptional);
//    return abstractOptional.orElse(null);
//  }

  @Override
  public Optional<ProposalList> saveProposal(
      ProposalListKey proposalKey) {
    log.trace(
            "saveProposal - method entered: proposalListKey={}",
            proposalKey);
            ProposalList newProposalList =
            ProposalList.builder()
                    .proposalListKey(proposalKey)
                    .build();
    Optional<ProposalList> checkForPresence = proposalListRepository.findById(proposalKey);

    checkForPresence.ifPresentOrElse(analysis -> {}, () -> proposalListRepository.save(newProposalList));

    log.trace("saveProposal - method finished result={}", checkForPresence);
    return checkForPresence;
  }

  @Override
  public void deleteProposal(ProposalListKey proposalKey) {
    log.trace("deleteProposal - method entered: proposalKey={}", proposalKey);
    proposalListRepository.deleteById(proposalKey);
    log.trace("deleteProposal - method finished");
  }
}
