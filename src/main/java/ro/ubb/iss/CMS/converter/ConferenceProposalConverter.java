package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.ConferenceProposal;
import ro.ubb.iss.CMS.domain.ConferenceProposalKey;
import ro.ubb.iss.CMS.domain.PcMember;
import ro.ubb.iss.CMS.domain.PcMemberKey;
import ro.ubb.iss.CMS.dto.ConferenceProposalDto;
import ro.ubb.iss.CMS.dto.PcMemberDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ConferenceProposalConverter
    implements BaseConverter<ConferenceProposal, ConferenceProposalDto> {
  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @Override
  public ConferenceProposal convertDtoToModel(ConferenceProposalDto conferenceProposalDto) {
    return ConferenceProposal.builder()
        .conferenceProposalKey(
            ConferenceProposalKey.builder()
                .conferenceID(conferenceProposalDto.getConferenceID())
                .proposalID(conferenceProposalDto.getProposalID())
                .build())
        .build();
  }

  @Override
  public ConferenceProposalDto convertModelToDto(ConferenceProposal conferenceProposal) {
    return ConferenceProposalDto.builder()
        .conferenceID(conferenceProposal.getConference().getConferenceID())
        .proposalID(conferenceProposal.getProposal().getProposalID())
        .build();
  }
}
