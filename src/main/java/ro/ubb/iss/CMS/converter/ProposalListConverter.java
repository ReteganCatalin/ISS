package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.ProposalListDto;
import ro.ubb.iss.CMS.dto.RoleForUserDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ProposalListConverter implements BaseConverter<ProposalList, ProposalListDto> {
  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @Override
  public ProposalList convertDtoToModel(ProposalListDto proposalListDto) {
    return ProposalList.builder()
        .proposalListKey(
            new ProposalListKey(proposalListDto.getSectionID(), proposalListDto.getProposalID()))
        .section(entityManager.getReference(Section.class, proposalListDto.getSectionID()))
        .proposal(entityManager.getReference(Proposal.class, proposalListDto.getProposalID()))
        .build();
  }

  @Override
  public ProposalListDto convertModelToDto(ProposalList proposalList) {
    return ProposalListDto.builder()
        .sectionID(proposalList.getSection().getSectionID())
        .proposalID(proposalList.getProposal().getProposalID())
        .build();
  }
}
