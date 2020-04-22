package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "permission_list")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class PermissionForUser {

  @EmbeddedId
  PermissionForUserKey permissionForUserKey;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("user_id")
  @JoinColumn(name = "user_id")
  User user;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("permission_id")
  @JoinColumn(name = "permission_id")
  Permission permission;
}
