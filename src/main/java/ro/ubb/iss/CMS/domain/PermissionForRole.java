package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role_permission_id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class PermissionForRole {

  @EmbeddedId PermissionForRoleKey PermissionForRoleKey;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("role_id")
  @JoinColumn(name = "role_id")
  Role role;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("permission_id")
  @JoinColumn(name = "permission_id")
  Permission permission;
}
