package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role_user_list")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class RoleForUser {

  @EmbeddedId RoleForUserKey roleForUserKey;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("user_id")
  @JoinColumn(name = "user_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private User user;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("role_id")
  @JoinColumn(name = "role_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Role role;
}
