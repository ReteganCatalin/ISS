package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Permission;
import ro.ubb.iss.CMS.domain.PermissionForRole;
import ro.ubb.iss.CMS.domain.PermissionForRoleKey;
import ro.ubb.iss.CMS.domain.Role;
import ro.ubb.iss.CMS.dto.PermissionForRoleDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class PermissionForRoleConverter
    implements BaseConverter<PermissionForRole, PermissionForRoleDto> {

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @Override
  public PermissionForRole convertDtoToModel(PermissionForRoleDto permissionForRoleDto) {
    return PermissionForRole.builder()
        .permissionForRoleKey(
            new PermissionForRoleKey(
                permissionForRoleDto.getRoleID(), permissionForRoleDto.getPermissionID()))
        .role(entityManager.getReference(Role.class, permissionForRoleDto.getRoleID()))
        .permission(
            entityManager.getReference(Permission.class, permissionForRoleDto.getPermissionID()))
        .build();
  }

  @Override
  public PermissionForRoleDto convertModelToDto(PermissionForRole permissionForRole) {
    return PermissionForRoleDto.builder()
        .permissionID(permissionForRole.getPermission().getPermissionID())
        .roleID(permissionForRole.getRole().getRoleID())
        .build();
  }
}
