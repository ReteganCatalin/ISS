package ro.ubb.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permission")
public class Permission {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "permission_id")
  private int permission_id;

  @Column(name = "name", nullable = false)
  private String permissionName;

  @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<PermissionForUser> permissionForUser = new HashSet<>();

  @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Participation> participations = new HashSet<>();
}
