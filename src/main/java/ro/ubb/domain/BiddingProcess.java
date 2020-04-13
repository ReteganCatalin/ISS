package ro.ubb.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bidding_process")
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

  @OneToMany(mappedBy = "biddingProcess", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Analyse> analyses = new HashSet<>();
}
