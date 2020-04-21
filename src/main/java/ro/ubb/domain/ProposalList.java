package ro.ubb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "proposal_list")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
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
