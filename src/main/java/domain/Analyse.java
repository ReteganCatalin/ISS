package domain;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name="Analyse")
public class Analyse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "analyse_id")
    private Integer analyse_id;

    @Column(name = "bid_id",nullable = false)
    private Integer bidId;

    @Column(name = "user_id",nullable = false)
    private Integer userID;

    @Column(name = "proposal_id",nullable = false)
    private Integer proposalId;

    @Column(name="brief_analyse",nullable = false,columnDefinition = "TEXT")
    private String briefAnalyse;

    @Column(name="refuse",nullable = false)
    private Boolean refuse;

}
