package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.PcMember;
import ro.ubb.iss.CMS.domain.PcMemberKey;
import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.dto.PcMemberDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class PcMemberConverter implements BaseConverter<PcMember, PcMemberDto> {
  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @Override
  public PcMember convertDtoToModel(PcMemberDto pcMemberDto) {
    return PcMember.builder()
        .pcMemberKey(
            PcMemberKey.builder()
                .conferenceID(pcMemberDto.getConferenceID())
                .userID(pcMemberDto.getUserID())
                .build())
        .build();
  }

  @Override
  public PcMemberDto convertModelToDto(PcMember pcMember) {
    return PcMemberDto.builder()
        .conferenceID(pcMember.getConference().getConferenceID())
        .userID(pcMember.getUser().getUserID())
        .build();
  }
}
