package ro.ubb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
class AnalysisKey implements Serializable {
  @Column(name = "bid_id")
  private Integer bidID;

  @Column(name = "pc_member_id")
  private Integer userID;

  @Column(name = "proposal_id")
  private Integer proposalID;

  @Override
  public String toString() {
    return "AnalyseKey{"
        + "bidID="
        + bidID
        + ", userID="
        + userID
        + ", proposalID="
        + proposalID
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AnalysisKey that = (AnalysisKey) o;
    return bidID.equals(that.bidID)
        && userID.equals(that.userID)
        && proposalID.equals(that.proposalID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bidID, userID, proposalID);
  }

  public AnalysisKey() {}

  public Integer getBidID() {
    return bidID;
  }

  public void setBidID(Integer bidID) {
    this.bidID = bidID;
  }

  public Integer getUserID() {
    return userID;
  }

  public void setUserID(Integer userID) {
    this.userID = userID;
  }

  public Integer getProposalID() {
    return proposalID;
  }

  public void setProposalID(Integer proposalID) {
    this.proposalID = proposalID;
  }

  public AnalysisKey(Integer bidID, Integer userID, Integer proposalID) {
    this.bidID = bidID;
    this.userID = userID;
    this.proposalID = proposalID;
  }
}

@Entity
@Table(name = "analysis")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
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
