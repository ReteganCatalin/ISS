package ro.ubb.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
class ReviewListKey implements Serializable {
  @Column(name = "proposal_id")
  private Integer proposalID;

  @Column(name = "qualifier_id")
  private Integer qualifierID;

  @Column(name = "user_id")
  private Integer userId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ReviewListKey that = (ReviewListKey) o;
    return proposalID.equals(that.proposalID)
        && qualifierID.equals(that.qualifierID)
        && userId.equals(that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(proposalID, qualifierID, userId);
  }

  @Override
  public String toString() {
    return "ReviewListKey{"
        + "proposalID="
        + proposalID
        + ", qualifierID="
        + qualifierID
        + ", userId="
        + userId
        + '}';
  }

  public Integer getProposalID() {
    return proposalID;
  }

  public void setProposalID(Integer proposalID) {
    this.proposalID = proposalID;
  }

  public Integer getQualifierID() {
    return qualifierID;
  }

  public void setQualifierID(Integer qualifierID) {
    this.qualifierID = qualifierID;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public ReviewListKey(Integer proposalID, Integer qualifierID, Integer userId) {
    this.proposalID = proposalID;
    this.qualifierID = qualifierID;
    this.userId = userId;
  }
}

@Entity
@Table(name = "review_list")
public class Review {

  @EmbeddedId ReviewListKey reviewListKey;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("proposal_id")
  @JoinColumn(name = "proposal_id")
  Proposal proposal;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("qualifier_id")
  @JoinColumn(name = "qualifier_id")
  Qualifier qualifier;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("user_id")
  @JoinColumn(name = "user_id")
  User user;
}
