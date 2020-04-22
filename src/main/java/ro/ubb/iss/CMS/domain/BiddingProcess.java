package ro.ubb.iss.CMS.domain;

import lombok.*;
import ro.ubb.iss.CMS.domain.Analysis;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bidding_process")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class BiddingProcess {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bid_id")
  private Integer bidID;

  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "conference_id")
  private Conference conference;

  @Column(name = "deadline", nullable = false)
  @Temporal(TemporalType.DATE)
  private java.util.Date deadline;

  @OneToMany(mappedBy = "biddingProcess",orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Analysis> analyses = new HashSet<>();
}
