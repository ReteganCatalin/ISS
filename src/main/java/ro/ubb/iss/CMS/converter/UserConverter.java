package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.domain.Proposal;
import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.domain.UserInfo;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.UserDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class UserConverter implements BaseConverter<User, UserDto> {
    @PersistenceContext // or even @Autowired
    private EntityManager entityManager;
    @Override
    public User convertDtoToModel(UserDto userDto) {
        return User.builder()
                .userID(userDto.getUserID())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .isValidated(userDto.getIsValidated())
                .userInfo(entityManager.getReference(UserInfo.class, userDto.getUserInfoID()))
                .build();
    }

    @Override
    public UserDto convertModelToDto(User user) {
        return UserDto.builder()
                .userID(user.getUserID())
                .username(user.getUsername())
                .password(user.getPassword())
                .isValidated(user.getIsValidated())
                .userInfo(user.getUserInfoID())
                .build();
    }
}
