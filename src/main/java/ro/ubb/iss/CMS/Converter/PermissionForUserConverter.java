package ro.ubb.iss.CMS.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.PermissionForRoleDto;
import ro.ubb.iss.CMS.dto.PermissionForUserDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class PermissionForUserConverter
    implements BaseConverter<PermissionForUser, PermissionForUserDto> {

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @Override
  public PermissionForUser convertDtoToModel(PermissionForUserDto permissionForRoleDto) {
    return PermissionForUser.builder()
        .permissionForUserKey(
            new PermissionForUserKey(
                permissionForRoleDto.getUserID(), permissionForRoleDto.getPermissionID()))
        .user(entityManager.getReference(User.class, permissionForRoleDto.getUserID()))
        .permission(
            entityManager.getReference(Permission.class, permissionForRoleDto.getPermissionID()))
        .build();
  }

  @Override
  public PermissionForUserDto convertModelToDto(PermissionForUser permissionForRole) {
    return PermissionForUserDto.builder()
        .permissionID(permissionForRole.getPermission().getPermissionID())
        .userID(permissionForRole.getUser().getUserID())
        .build();
  }
}
