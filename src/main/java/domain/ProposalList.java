package domain;

import javax.persistence.*;

@Embeddable
class ProposalListKey{

    @Column(name = "section_id")
    private Integer sectionID;
    @Column(name = "proposal_id")
    private Integer proposalID;
    @Column(name = "conference_id")
    private Integer conferenceID;

}

@Entity
@Table(name="Proposal_list")
public class ProposalList {

    @EmbeddedId
    ProposalListKey proposalListKey;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @MapsId("section_id")
    @JoinColumn(name = "section_id")
    Section section;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @MapsId("proposal_id")
    @JoinColumn(name = "proposal_id")
    Proposal proposal;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @MapsId("conference_id")
    @JoinColumn(name = "conference_id")
    Conference conference;


}
