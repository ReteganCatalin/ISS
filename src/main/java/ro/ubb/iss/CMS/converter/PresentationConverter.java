package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.Presentation;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.UserInfo;
import ro.ubb.iss.CMS.dto.PresentationDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class PresentationConverter implements BaseConverter<Presentation, PresentationDto> {
  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @Override
  public Presentation convertDtoToModel(PresentationDto presentationDto) {
    return Presentation.builder()
        .presentationID(presentationDto.getPresentationID())
        .section(entityManager.getReference(Section.class, presentationDto.getSectionID()))
       // .conference(entityManager.getReference(Conference.class, presentationDto.getConferenceID()))
        .format(presentationDto.getFormat())
        .byteFileLocation(presentationDto.getByteFileLocation())
        .build();
  }

  @Override
  public PresentationDto convertModelToDto(Presentation presentation) {
    return PresentationDto.builder()
        .presentationID(presentation.getPresentationID())
        .sectionID(presentation.getSection().getSectionID())
        //.conferenceID(presentation.getConference().getConferenceID())
        .format(presentation.getFormat())
        .byteFileLocation(presentation.getByteFileLocation())
        .build();
  }
}
