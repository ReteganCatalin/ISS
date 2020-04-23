package ro.ubb.service;

import ro.ubb.domain.ProposalList;
import ro.ubb.domain.ProposalListKey;

import java.util.List;
import java.util.Optional;

public interface ProposalListService {
    Optional<ProposalList> findProposalList(ProposalListKey proposalKey);

    List<ProposalList> findAll();

    ProposalList updateProposal(ProposalListKey proposalKey, String briefAnalysis, Boolean refuse);

    Optional<ProposalList> saveProposal(ProposalListKey proposalKey,String briefAnalysis, Boolean refuse);

    void deleteProposal(ProposalListKey proposalKey);
}
