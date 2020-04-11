package domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Bidding_process")
public class BiddingProcess {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bid_id")
  private Integer bidID;

  @Column(name = "conference_id", nullable = false)
  private Integer conferenceId;

  @Column(name = "deadline",nullable = false)
  @Temporal(TemporalType.DATE)
  private java.util.Date deadine;
}
