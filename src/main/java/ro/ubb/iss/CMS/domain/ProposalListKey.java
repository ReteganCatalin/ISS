package ro.ubb.iss.CMS.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProposalListKey implements Serializable {

  public ProposalListKey() {}

  public ProposalListKey(Integer sectionID, Integer proposalID) {
    this.sectionID = sectionID;
    this.proposalID = proposalID;
  }

  @Override
  public String toString() {
    return "ProposalListKey{"
        + "sectionID="
        + sectionID
        + ", proposalID="
        + proposalID
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProposalListKey that = (ProposalListKey) o;
    return sectionID.equals(that.sectionID)
        && proposalID.equals(that.proposalID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sectionID, proposalID);
  }

  public Integer getSectionID() {
    return sectionID;
  }

  public void setSectionID(Integer sectionID) {
    this.sectionID = sectionID;
  }

  public Integer getProposalID() {
    return proposalID;
  }

  public void setProposalID(Integer proposalID) {
    this.proposalID = proposalID;
  }

  @Column(name = "section_id")
  private Integer sectionID;

  @Column(name = "proposal_id")
  private Integer proposalID;
}
