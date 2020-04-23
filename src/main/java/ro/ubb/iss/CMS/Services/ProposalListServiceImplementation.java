package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.ProposalList;
import ro.ubb.iss.CMS.domain.ProposalListKey;

import java.util.List;
import java.util.Optional;

public class ProposalListServiceImplementation implements ProposalListService {
  @Override
  public Optional<ProposalList> findProposalList(ProposalListKey proposalKey) {
    return Optional.empty();
  }

  @Override
  public List<ProposalList> findAll() {
    return null;
  }

  @Override
  public ProposalList updateProposal(
      ProposalListKey proposalKey, String briefAnalysis, Boolean refuse) {
    return null;
  }

  @Override
  public Optional<ProposalList> saveProposal(
      ProposalListKey proposalKey, String briefAnalysis, Boolean refuse) {
    return Optional.empty();
  }

  @Override
  public void deleteProposal(ProposalListKey proposalKey) {}
}
