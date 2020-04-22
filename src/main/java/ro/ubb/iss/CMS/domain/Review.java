package ro.ubb.iss.CMS.domain;

import lombok.*;
import ro.ubb.iss.CMS.domain.Proposal;
import ro.ubb.iss.CMS.domain.Recommendation;

import javax.persistence.*;
/*
@Embeddable
class ReviewListKey implements Serializable {
  @Column(name = "proposal_id")
  private Integer proposalID;

  @Column(name = "qualifier_id")
  private Integer qualifierID;

  public ReviewListKey() {
  }

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
}*/

@Entity
@Table(name = "review_list")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_id")
  private Integer reviewID;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "proposal_id")
  Proposal proposal;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "qualifier_id")
  Qualifier qualifier;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  User user;

  @OneToOne(orphanRemoval = true,cascade = CascadeType.ALL,optional = false,fetch = FetchType.LAZY)
  @JoinColumn(name="recommendation_id")
  Recommendation recommendation;
}
