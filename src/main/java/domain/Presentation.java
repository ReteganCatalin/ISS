package domain;

import javax.persistence.*;

@Entity
@Table(name = "Presentations")
public class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "presentation_id")
    private Integer presentationID;

    @Column(name = "conference_id",nullable = false)
    private Integer conferenceID;

    @Column(name = "format",nullable = false)
    private String formar;

    @Column(name = "byte_file",nullable = false)
    private byte[] byteFile;
}
