package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.iss.CMS.Repository.ProposalRepository;
import ro.ubb.iss.CMS.domain.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProposalServiceImplementation implements ProposalService {
  private static final Logger log = LoggerFactory.getLogger(ProposalServiceImplementation.class);

  @Autowired private ProposalRepository proposalRepository;

  @Override
  public Optional<Proposal> findProposal(int proposalID) {
    log.trace("findProposal - method entered proposalID={}", proposalID);
    Optional<Proposal> result = proposalRepository.findById(proposalID);
    log.trace("findProposal - method exit result={}", result);
    return result;
  }

  @Override
  public List<Proposal> findAll() {
    log.trace("findAll - method entered");
    List<Proposal> result = proposalRepository.findAll();
    log.trace("findAll - method exit result={}", result);
    return result;
  }

  @Override
  @Transactional
  public Proposal updateProposal(
      int proposalID,
      UserInfo userInfoID,
      Paper paperID,
      MetaInformation metaInfoID,
      Abstract abstractID) {
    log.trace(
        "updateProposal - method entered: proposalID={}, userInfoID={}, paperID={}, metaInfoID={}, abstractID={}",
        proposalID,
        userInfoID,
        paperID,
        metaInfoID,
        abstractID);

    Optional<Proposal> abstractOptional = proposalRepository.findById(proposalID);

    abstractOptional.ifPresent(
        newProposal -> {
          newProposal.setUserInfo(userInfoID);
          newProposal.setPaper(paperID);
          newProposal.setMetaInformation(metaInfoID);
          newProposal.setAnAbstract(abstractID);
          log.debug("updateProposal - updated: newProposal={}", newProposal);
        });
    log.trace("updateProposal - method finished result={}", abstractOptional);
    return abstractOptional.orElse(null);
  }

  @Override
  public Proposal saveProposal(
      UserInfo userInfoID, Paper paperID, MetaInformation metaInfoID, Abstract abstractID) {
    log.trace(
        "saveProposal - method entered: userInfoID={}, paperID={}, metaInfoID={}, abstractID={}",
        userInfoID,
        paperID,
        metaInfoID,
        abstractID);
    Proposal newProposal =
        Proposal.builder()
            .userInfo(userInfoID)
            .paper(paperID)
            .metaInformation(metaInfoID)
            .anAbstract(abstractID)
            .build();

    proposalRepository.save(newProposal);

    log.trace("saveProposal - method finished result={}", newProposal);
    return newProposal;
  }

  @Override
  public void deleteProposal(int proposalID) {
    log.trace("deleteProposal - method entered: proposalID={}", proposalID);
    proposalRepository.deleteById(proposalID);
    log.trace("deleteProposal - method finished");
  }
}
