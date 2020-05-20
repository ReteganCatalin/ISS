package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.ProposalDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ProposalConverter implements BaseConverter<Proposal, ProposalDto> {
  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @Override
  public Proposal convertDtoToModel(ProposalDto proposalDto) {
    return Proposal.builder()
        .proposalID(proposalDto.getProposalID())
        .userInfo(entityManager.getReference(UserInfo.class, proposalDto.getUserInfoID()))
        .paper(entityManager.getReference(Paper.class, proposalDto.getPaperID()))
        .metaInformation(
            entityManager.getReference(MetaInformation.class, proposalDto.getMetaInfoID()))
        .anAbstract(entityManager.getReference(Abstract.class, proposalDto.getAbstractID()))
        .build();
  }

  @Override
  public ProposalDto convertModelToDto(Proposal proposal) {
    return ProposalDto.builder()
        .proposalID(proposal.getProposalID())
        .userInfoID(proposal.getUserInfo().getUserInfoId())
        .paperID(proposal.getPaper().getPaperId())
        .metaInfoID(proposal.getMetaInformation().getMetaInfoId())
        .abstractID(proposal.getAnAbstract().getAbstractID())
        .build();
  }
}
