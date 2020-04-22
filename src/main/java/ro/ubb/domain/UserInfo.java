package ro.ubb.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_info_id")
  private Integer userInfoId;

  @Column(name = "name", nullable = false,length = 20)
  private String name;

  @Column(name = "affiliation", nullable = false,length = 20)
  private String affiliation;

  @Column(name = "email_address", length = 40)
  private String emailAddress;

  @Column(name = "webpage_address", length = 40)
  private String webPageAddress;

  @Column(name = "affiliation_validated")
  private Boolean affiliationValidated;

  @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private User user;

  @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Proposal proposal;

  @OneToOne(mappedBy = "supervisor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Section section;
}
