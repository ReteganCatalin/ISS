package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.*;

import java.util.List;
import java.util.Optional;

public class ProposalServiceImplementation implements ProposalService {
  @Override
  public Optional<Proposal> findProposal(int proposalID) {
    return Optional.empty();
  }

  @Override
  public List<Proposal> findAll() {
    return null;
  }

  @Override
  public Proposal updateProposal(
      int proposalID,
      UserInfo userInfoID,
      Paper paperID,
      MetaInformation metaInfoID,
      Abstract abstractID) {
    return null;
  }

  @Override
  public Proposal saveProposal(
      UserInfo userInfoID, Paper paperID, MetaInformation metaInfoID, Abstract abstractID) {
    return null;
  }

  @Override
  public void deleteProposal(int proposalID) {}
}
