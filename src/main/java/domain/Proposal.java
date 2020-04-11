package domain;

import javax.persistence.*;

@Entity
@Table(name="Proposal")
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="proposal_id")
    private Integer proposalID;

    @Column(name="user_info_id")
    private Integer userInfoID;

    @Column(name = "paper_id")
    private Integer paperID;

    @Column(name = "meta_inf_id")
    private Integer metaInfID;

    @Column(name = "abstract_id")
    private Integer abstractID;

}
