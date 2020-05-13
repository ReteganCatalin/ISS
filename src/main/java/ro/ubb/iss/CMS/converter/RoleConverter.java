package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Role;
import ro.ubb.iss.CMS.dto.RoleDto;

@Component
public class RoleConverter implements BaseConverter<Role, RoleDto> {
  @Override
  public Role convertDtoToModel(RoleDto roleDto) {
    return Role.builder().roleID(roleDto.getRoleID()).name(roleDto.getName()).build();
  }

  @Override
  public RoleDto convertModelToDto(Role role) {
    return RoleDto.builder().roleID(role.getRoleID()).name(role.getName()).build();
  }
}
