package ro.ubb.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "author_list")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "author_id")
  private Integer authorId;

  @Column(name = "name", nullable = false,length = 40)
  private String name;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "proposal_id")
  private Proposal proposal;
}
