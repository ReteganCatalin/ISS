package ro.ubb.iss.CMS.domain;

import lombok.*;
import ro.ubb.iss.CMS.domain.BiddingProcess;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conference")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Conference {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "conference_id")
  private Integer conferenceID;

  @Column(name = "name", nullable = false, length = 40)
  private String name;

  @Column(name = "start_date", nullable = false)
  @Temporal(TemporalType.DATE)
  java.util.Date startDate;

  @Column(name = "end_date", nullable = false)
  @Temporal(TemporalType.DATE)
  java.util.Date endDate;

  @Column(name = "proposal_deadline", nullable = false)
  @Temporal(TemporalType.DATE)
  java.util.Date proposalDeadline;

  @Column(name = "paper_deadline", nullable = false)
  @Temporal(TemporalType.DATE)
  java.util.Date paperDeadline;

  //  @OneToMany(mappedBy = "conference",orphanRemoval = true, cascade = CascadeType.ALL, fetch =
  // FetchType.LAZY)
  //  Set<Participation> participations = new HashSet<>();

  @OneToMany(
      mappedBy = "conference",
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Set<Section> sections = new HashSet<>();

  @OneToMany(
      mappedBy = "conference",
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Set<ConferenceProposal> proposalsForConference = new HashSet<>();

  @OneToMany(
      mappedBy = "conference",
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Set<PcMember> pcMembers = new HashSet<>();

  //  @OneToMany(
  //      mappedBy = "conference",
  //      orphanRemoval = true,
  //      cascade = CascadeType.ALL,
  //      fetch = FetchType.LAZY)
  //  @EqualsAndHashCode.Exclude
  //  Set<ProposalList> proposalLists = new HashSet<>();

  @OneToMany(
      mappedBy = "conference",
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Set<Presentation> presentations = new HashSet<>();

  @OneToOne(
      mappedBy = "conference",
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  BiddingProcess biddingProcess;

//  @OneToOne(
//          mappedBy = "conference",
//          orphanRemoval = true,
//          cascade = CascadeType.ALL,
//          fetch = FetchType.LAZY)
//  @EqualsAndHashCode.Exclude
//  @ToString.Exclude
//  ConferenceData conferenceData;
}
