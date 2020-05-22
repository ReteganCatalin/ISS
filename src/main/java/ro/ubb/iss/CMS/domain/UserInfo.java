package ro.ubb.iss.CMS.domain;

import lombok.*;
import ro.ubb.iss.CMS.domain.Proposal;
import ro.ubb.iss.CMS.domain.Section;

import javax.persistence.*;
import java.util.Set;

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
  public Integer userInfoId;

  @Column(name = "name", nullable = false, length = 20)
  public String name;

  @Column(name = "affiliation", nullable = false, length = 20)
  public String affiliation;

  @Column(name = "email_address", length = 40)
  public String emailAddress;

  @Column(name = "webpage_address", length = 40)
  public String webPageAddress;

  @Column(name = "affiliation_validated")
  public Boolean affiliationValidated;

  @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  public User user;

  @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  public Set<Proposal> proposals;

  //  @OneToOne(mappedBy = "supervisor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  //  @EqualsAndHashCode.Exclude
  //  public Section section;
}
