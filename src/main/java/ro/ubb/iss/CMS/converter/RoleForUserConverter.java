package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.PermissionForRoleDto;
import ro.ubb.iss.CMS.dto.RoleForUserDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class RoleForUserConverter implements BaseConverter<RoleForUser, RoleForUserDto> {
    @PersistenceContext // or even @Autowired
    private EntityManager entityManager;
    @Override
    public RoleForUser convertDtoToModel(RoleForUserDto roleForUserDto) {
        return RoleForUser.builder()
                .roleForUserKey(
                        new RoleForUserKey(
                                roleForUserDto.getUserID(),
                                roleForUserDto.getRoleID()))
                .role(entityManager.getReference(Role.class, roleForUserDto.getRoleID()))
                .user(entityManager.getReference(User.class, roleForUserDto.getUserID()))
                .build();
    }

    @Override
    public RoleForUserDto convertModelToDto(RoleForUser roleForUser) {
        return RoleForUserDto.builder()
                .userID(roleForUserDto.getUser().getUserID())
                .roleID(roleForUserDto.getRole().getRoleID())
                .build();
    }
}
