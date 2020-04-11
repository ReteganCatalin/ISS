package domain;

import javax.persistence.*;

@Entity
@Table(name = "Participant_list")
public class ParticipantList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "praticipant_list_id")
    private Integer praticipantListID;

    @Column(name = "section_id",nullable = false)
    private Integer sectionID;

    @Column(name="user_id",nullable = false)
    private Integer userID;

    @Column(name = "permission_id",nullable = false)
    private Integer permissionID;

    @Column(name = "conference_id",nullable = false)
    private Integer conferenceID;

}
