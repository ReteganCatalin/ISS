package ro.ubb.iss.CMS.dto;

import lombok.*;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class PermissionForUserDto {

  private Integer userID;

  private Integer permissionID;
}
