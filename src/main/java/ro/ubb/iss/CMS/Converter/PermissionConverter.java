package ro.ubb.iss.CMS.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Permission;
import ro.ubb.iss.CMS.dto.PermissionDto;

@Component
public class PermissionConverter implements BaseConverter<Permission, PermissionDto> {
  @Override
  public Permission convertDtoToModel(PermissionDto permissionDto) {
    return Permission.builder()
        .permissionID(permissionDto.getPermissionID())
        .permissionName(permissionDto.getPermissionName())
        .build();
  }

  @Override
  public PermissionDto convertModelToDto(Permission permission) {
    return PermissionDto.builder()
        .permissionID(permission.getPermissionID())
        .permissionName(permission.getPermissionName())
        .build();
  }
}
