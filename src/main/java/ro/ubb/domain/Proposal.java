package ro.ubb.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "proposal")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Proposal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "proposal_id")
  private Integer proposalID;

  @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  Set<Review> reviews = new HashSet<>();

  @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  Set<Author> authors = new HashSet<>();

  @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  Set<Analysis> analyses = new HashSet<>();

  @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  Set<ProposalList> proposalListsForSections = new HashSet<>();

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_info_id")
  private UserInfo userInfo;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "meta_info_id")
  private MetaInformation metaInformation;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "abstract_id")
  private Abstract anAbstract;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "paper_id")
  private Paper paper;
}
