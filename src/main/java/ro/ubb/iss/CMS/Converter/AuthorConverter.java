package ro.ubb.iss.CMS.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Author;
import ro.ubb.iss.CMS.domain.Proposal;
import ro.ubb.iss.CMS.dto.AuthorDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class AuthorConverter implements BaseConverter<Author, AuthorDto> {

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @Override
  public Author convertDtoToModel(AuthorDto authorDto) {
    return Author.builder().authorId(authorDto.getAuthorId()).name(authorDto.getName())
            .proposal(entityManager.getReference(Proposal.class,authorDto.getProposalId())).build();
  }

  @Override
  public AuthorDto convertModelToDto(Author author) {
    return AuthorDto.builder()
        .authorId(author.getAuthorId())
        .name(author.getName())
        .proposalId(author.getProposal().getProposalID())
        .build();
  }
}
