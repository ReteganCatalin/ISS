package ro.ubb.iss.CMS.domain;

import lombok.*;
import ro.ubb.iss.CMS.domain.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "section")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Section {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "section_id")
  private Integer sectionID;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "supervisor_id", referencedColumnName = "user_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private User supervisor;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "conference_id", nullable = false)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Conference conference;

  @Temporal(TemporalType.DATE)
  @Column(name = "date_of_presentation", nullable = false)
  java.util.Date dateOfPresentation;

  @Column(name = "price", nullable = false)
  private Integer price;

  @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Set<Participation> participations = new HashSet<>();

  @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Set<ProposalList> proposalLists = new HashSet<>();

  @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Set<Presentation> presentations = new HashSet<>();

  @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  Set<MyTicket> myTickets = new HashSet<>();
}
