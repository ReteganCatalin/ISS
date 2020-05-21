package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;

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

  @EmbeddedId AnalysisKey analysisKey;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("user_id")
  @JoinColumn(name = "user_id")
  @EqualsAndHashCode.Exclude
  User user;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("proposal_id")
  @JoinColumn(name = "proposal_id")
  @EqualsAndHashCode.Exclude
  Proposal proposal;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("bid_id")
  @JoinColumn(name = "bid_id")
  @EqualsAndHashCode.Exclude
  BiddingProcess biddingProcess;

  @Column(name = "brief_analysis", nullable = false, columnDefinition = "TEXT")
  private String briefAnalysis;

  @Column(name = "refuse", nullable = false)
  private Boolean refuse;
}
