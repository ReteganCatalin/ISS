package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permission")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Permission {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "permission_id")
  private int permission_id;

  @Column(name = "name", nullable = false)
  private String permissionName;

  @OneToMany(mappedBy = "permission",orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<PermissionForUser> permissionForUser = new HashSet<>();

  @OneToMany(mappedBy = "permission",orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Participation> participations = new HashSet<>();
}
