package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.domain.UserInfo;
import ro.ubb.iss.CMS.dto.SectionDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class SectionConverter implements BaseConverter<Section, SectionDto> {
    @PersistenceContext // or even @Autowired
    private EntityManager entityManager;
    @Override
    public Section convertDtoToModel(SectionDto sectionDto) {
        return Section.builder()
                .sectionID(sectionDto.getSectionID())
                .supervisor(entityManager.getReference(User.class, sectionDto.getSupervisorID()))
                .conference(entityManager.getReference(Conference.class, sectionDto.getConferenceID()))
                .dateOfPresentation(sectionDto.DateOfPresentation())
                .build();
    }

    @Override
    public SectionDto convertModelToDto(Section section) {
        return SectionDto.builder()
                .sectionID(section.getSectionID())
                .supervisor(section.getSupervisorID())
                .conference(section.getConferenceID())
                .dateOfPresentation(section.DateOfPresentation())
                .build();
    }
}
