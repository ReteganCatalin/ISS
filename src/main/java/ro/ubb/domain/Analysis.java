package ro.ubb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "analysis")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Analysis {

  @EmbeddedId
  AnalysisKey analysisKey;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("user_id")
  @JoinColumn(name = "pc_member_id",referencedColumnName = "user_id")
  User user;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("proposal_id")
  @JoinColumn(name = "proposal_id")
  Proposal proposal;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("bid_id")
  @JoinColumn(name = "bid_id")
  BiddingProcess biddingProcess;


  @Column(name = "brief_analysis", nullable = false, columnDefinition = "TEXT")
  private String briefAnalysis;

  @Column(name = "refuse", nullable = false)
  private Boolean refuse;
}
