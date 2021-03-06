package ro.ubb.iss.CMS.domain;

import lombok.*;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.domain.Analysis;
import ro.ubb.iss.CMS.domain.Author;
import ro.ubb.iss.CMS.domain.MetaInformation;

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
@Builder
public class Proposal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "proposal_id")
  private Integer proposalID;

  @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Set<Review> reviews = new HashSet<>();

  @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Set<Author> authors = new HashSet<>();

  @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Set<Analysis> analyses = new HashSet<>();

  @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Set<ProposalList> proposalListsForSections = new HashSet<>();

  @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Set<ConferenceProposal> proposalAssignedForConference = new HashSet<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_info_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private UserInfo userInfo;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "meta_info_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private MetaInformation metaInformation;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "abstract_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Abstract anAbstract;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "paper_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Paper paper;
}
