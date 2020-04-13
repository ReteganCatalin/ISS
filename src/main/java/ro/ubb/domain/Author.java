package ro.ubb.domain;

import javax.persistence.*;

@Entity
@Table(name = "author_list")
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "author_id")
  private Integer authorId;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "proposal_id")
  private Proposal proposal;
}
