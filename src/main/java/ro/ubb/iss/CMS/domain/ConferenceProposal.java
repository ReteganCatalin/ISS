package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "conference_proposal")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class ConferenceProposal {

  @EmbeddedId ConferenceProposalKey conferenceProposalKey;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("proposal_id")
  @JoinColumn(name = "proposal_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Proposal proposal;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @MapsId("conference_id")
  @JoinColumn(name = "conference_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Conference conference;
}
