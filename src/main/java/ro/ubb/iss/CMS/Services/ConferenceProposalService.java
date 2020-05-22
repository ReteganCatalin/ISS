package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.ConferenceProposal;
import ro.ubb.iss.CMS.domain.ConferenceProposalKey;
import ro.ubb.iss.CMS.domain.PcMember;
import ro.ubb.iss.CMS.domain.PcMemberKey;

import java.util.List;
import java.util.Optional;

public interface ConferenceProposalService {
  Optional<ConferenceProposal> findConferenceProposal(ConferenceProposalKey conferenceProposalKey);

  List<ConferenceProposal> findAll();

  Optional<ConferenceProposal> saveConferenceProposal(ConferenceProposalKey conferenceProposalKey);

  void deleteConferenceProposal(ConferenceProposalKey conferenceProposalKey);
}
