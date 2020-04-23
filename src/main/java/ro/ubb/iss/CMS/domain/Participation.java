package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "participant_list")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
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
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;
}
