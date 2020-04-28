package ro.ubb.iss.CMS.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Participation;
import ro.ubb.iss.CMS.domain.Role;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.dto.ParticipationDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ParticipationConverter implements BaseConverter<Participation, ParticipationDto> {

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @Override
  public Participation convertDtoToModel(ParticipationDto participationDto) {
    return Participation.builder()
        .participantListID(participationDto.getParticipantListID())
        .role(entityManager.getReference(Role.class, participationDto.getRoleID()))
        .user(entityManager.getReference(User.class, participationDto.getUserID()))
        .section(entityManager.getReference(Section.class, participationDto.getSectionID()))
        .build();
  }

  @Override
  public ParticipationDto convertModelToDto(Participation participation) {
    return null;
  }
}
