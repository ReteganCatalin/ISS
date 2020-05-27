package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "qualifier")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Qualifier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "qualifier_id")
  private Integer qualifierID;

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany(mappedBy = "qualifier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Set<Review> reviews = new HashSet<>();
}
