package domain;

import javax.persistence.*;

@Entity
@Table(name="Conferences")
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conference_id")

    private Integer conferenceID;
    @Column(name = "name",nullable = false)
    private String name;

    @Column(name="start_date",nullable = false)
    @Temporal(TemporalType.DATE)
    java.util.Date startDate;

    @Column(name="end_date",nullable = false)
    @Temporal(TemporalType.DATE)
    java.util.Date endDate;

    @Column(name="proposal_deadline",nullable = false)
    @Temporal(TemporalType.DATE)
    java.util.Date proposalDeadline;

    @Column(name="paper_deadline",nullable = false)
    @Temporal(TemporalType.DATE)
    java.util.Date paperDeadline;

}
