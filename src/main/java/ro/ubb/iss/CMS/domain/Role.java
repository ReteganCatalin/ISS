package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  Integer roleID;

  @Column(name = "name")
  String name;

  @OneToMany(
      mappedBy = "role",
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  private Set<Participation> participations = new HashSet<>();
}
