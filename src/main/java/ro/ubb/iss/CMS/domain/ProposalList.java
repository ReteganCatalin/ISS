package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
class ProposalListKey implements Serializable {

  public ProposalListKey() {
  }
  public ProposalListKey(Integer sectionID,Integer proposalID,Integer conferenceID) {
    this.sectionID = sectionID;
    this.proposalID = proposalID;
    this.conferenceID = conferenceID;
  }

  @Override
  public String toString() {
    return "ProposalListKey{" +
            "sectionID=" + sectionID +
            ", proposalID=" + proposalID +
            ", conferenceID=" + conferenceID +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProposalListKey that = (ProposalListKey) o;
    return sectionID.equals(that.sectionID) &&
            proposalID.equals(that.proposalID) &&
            conferenceID.equals(that.conferenceID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sectionID, proposalID, conferenceID);
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

  public Integer getConferenceID() {
    return conferenceID;
  }

  public void setConferenceID(Integer conferenceID) {
    this.conferenceID = conferenceID;
  }

  @Column(name = "section_id")
  private Integer sectionID;

  @Column(name = "proposal_id")
  private Integer proposalID;

  @Column(name = "conference_id")
  private Integer conferenceID;
}

@Entity
@Table(name = "proposal_list")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProposalList {

  @EmbeddedId ProposalListKey proposalListKey;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("section_id")
  @JoinColumn(name = "section_id")
  Section section;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("proposal_id")
  @JoinColumn(name = "proposal_id")
  Proposal proposal;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("conference_id")
  @JoinColumn(name = "conference_id")
  Conference conference;
}
