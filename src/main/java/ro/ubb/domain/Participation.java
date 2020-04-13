package ro.ubb.domain;

import javax.persistence.*;

@Entity
@Table(name = "participant_list")
public class Participation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "participant_list_id")
  private Integer participantListID;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "section_id", nullable = false)
  private Section section;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "permission_id", nullable = false)
  private Permission permission;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "conference_id", nullable = false)
  private Conference conference;
}
