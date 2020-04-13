package ro.ubb.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conference")
public class Conference {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "conference_id")
  private Integer conferenceID;

  @Column(name = "name", nullable = false)
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

  @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  Set<Participation> participations = new HashSet<>();

  @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  Set<Section> sections = new HashSet<>();

  @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  Set<ProposalList> proposalLists = new HashSet<>();

  @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  Set<Presentation> presentations = new HashSet<>();

  @OneToOne(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  BiddingProcess biddingProcess;
}
