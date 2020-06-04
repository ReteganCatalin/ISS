package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.*;

import java.util.List;
import java.util.Optional;

public interface ProposalService {
  Optional<Proposal> findProposal(int proposalID);

  List<Proposal> findAll();

  String getProposalStatus(Integer proposalID);


  Proposal updateProposal(
      int proposalID,
      UserInfo userInfoID,
      Paper paperID,
      MetaInformation metaInfoID,
      Abstract abstractID);

  Proposal saveProposal(
      UserInfo userInfoID, Paper paperID, MetaInformation metaInfoID, Abstract abstractID);

  void deleteProposal(int proposalID);
}
