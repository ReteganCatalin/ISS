package ro.ubb.iss.CMS.domain;

import lombok.*;
import ro.ubb.iss.CMS.domain.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer userID;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "is_validated", nullable = false)
  private Boolean isValidated;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_info_id")
  private UserInfo userInfo;

  @OneToMany(
      mappedBy = "user",
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude Set<PermissionForUser> permissionForUser = new HashSet<>();

  @OneToMany(
      mappedBy = "user",
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude Set<Review> reviewsForUser = new HashSet<>();

  @OneToMany(
      mappedBy = "user",
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude private Set<Analysis> analyses = new HashSet<>();

  @OneToMany(
      mappedBy = "user",
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude private Set<Participation> participations = new HashSet<>();

  @OneToMany(
      mappedBy = "user",
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude private Set<MyTicket> myTickets = new HashSet<>();
}
