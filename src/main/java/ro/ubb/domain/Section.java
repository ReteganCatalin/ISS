package ro.ubb.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sections")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Section {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sectiond_id")
  private Integer sectionID;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "supervisor_id", referencedColumnName = "user_info_id")
  private User supervisor;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "conference_id", nullable = false)
  Conference conference;

  @Temporal(TemporalType.DATE)
  @Column(name = "date_of_presentation", nullable = false)
  java.util.Date dateOfPresentation;

  @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  Set<Participation> participations = new HashSet<>();

  @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  Set<ProposalList> proposalLists = new HashSet<>();

  @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  Set<Presentation> presentations = new HashSet<>();
}
