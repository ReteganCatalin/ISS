package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Author;
import ro.ubb.iss.CMS.domain.Proposal;
import ro.ubb.iss.CMS.domain.UserInfo;
import ro.ubb.iss.CMS.dto.AuthorDto;
import ro.ubb.iss.CMS.dto.UserInfoDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class UserInfoConverter implements BaseConverter<UserInfo, UserInfoDto> {

    @PersistenceContext // or even @Autowired
    private EntityManager entityManager;

    @Override
    public UserInfo convertDtoToModel(UserInfoDto userInfoDto) {
        return UserInfo.builder()
                .userInfoId(userInfoDto.getUserInfoId())
                .name(userInfoDto.getName())
                .affiliation(userInfoDto.getAffiliation())
                .emailAddress(userInfoDto.getEmailAddress())
                .affiliationValidated(userInfoDto.getAffiliationValidated())
                .build();
    }

    @Override
    public UserInfoDto convertModelToDto(UserInfo userInfo) {
        return UserInfoDto.builder()
                .userInfoId(userInfo.getUserInfoId())
                .name(userInfo.getName())
                .affiliation(userInfo.getAffiliation())
                .emailAddress(userInfo.getEmailAddress())
                .affiliationValidated(userInfo.getAffiliationValidated())
                .build();
    }
}