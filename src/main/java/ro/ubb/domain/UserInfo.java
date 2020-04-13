package ro.ubb.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_info_id")
  private Integer userInfoId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "affiliation", nullable = false)
  private String affiliation;

  @Column(name = "email_address", length = 320)
  private String emailAddress;

  @Column(name = "webpage_address", length = 320)
  private String webPageAddress;

  @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private User user;

  @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Proposal proposal;

  @OneToOne(mappedBy = "supervisor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Section section;
}
