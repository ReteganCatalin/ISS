package ro.ubb.iss.CMS.dto;

import lombok.*;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class PermissionForRoleDto {

  private Integer roleID;

  private Integer permissionID;
}
