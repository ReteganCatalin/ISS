package ro.ubb.iss.CMS.domain;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@Embeddable
public class ConferenceProposalKey implements Serializable {

    @Column(name = "proposal_id")
    private Integer proposalID;

    @Column(name = "conference_id")
    private Integer conferenceID;
}
