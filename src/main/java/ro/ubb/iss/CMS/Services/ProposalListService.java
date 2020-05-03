package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.ProposalList;
import ro.ubb.iss.CMS.domain.ProposalListKey;

import java.util.List;
import java.util.Optional;

public interface ProposalListService {
  Optional<ProposalList> findProposalList(ProposalListKey proposalKey);

  List<ProposalList> findAll();

//  ProposalList updateProposal(ProposalListKey proposalKey, String briefAnalysis, Boolean refuse);

  Optional<ProposalList> saveProposal(
      ProposalListKey proposalKey);

  void deleteProposal(ProposalListKey proposalKey);
}
