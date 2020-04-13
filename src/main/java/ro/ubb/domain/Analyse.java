package ro.ubb.domain;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
class AnalyseKey {
  @Column(name = "bid_id")
  private Integer bidID;

  @Column(name = "user_id")
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
    AnalyseKey that = (AnalyseKey) o;
    return bidID.equals(that.bidID)
        && userID.equals(that.userID)
        && proposalID.equals(that.proposalID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bidID, userID, proposalID);
  }

  public AnalyseKey() {}

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

  public AnalyseKey(Integer bidID, Integer userID, Integer proposalID) {
    this.bidID = bidID;
    this.userID = userID;
    this.proposalID = proposalID;
  }
}

@Entity
@Table(name = "analyse")
public class Analyse {

  @EmbeddedId private AnalyseKey analyseKey;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("bid_id")
  @JoinColumn(name = "bid_id")
  BiddingProcess biddingProcess;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("user_id")
  @JoinColumn(name = "user_id")
  User user;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("proposal_id")
  @JoinColumn(name = "proposal_id")
  Proposal proposal;

  @Column(name = "brief_analyse", nullable = false, columnDefinition = "TEXT")
  private String briefAnalyse;

  @Column(name = "refuse", nullable = false)
  private Boolean refuse;
}
