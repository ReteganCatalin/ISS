package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "conference_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class ConferenceData  {


    @Id
    @Column(name = "conference_id")
    private Integer conferenceID;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "conference_id",referencedColumnName = "conference_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Conference conference;

    @Column(name = "call_for_paper")
    private String callForPaper;

    @Column(name="about")
    private String About;

    @Column(name = "format", nullable = false, length = 5)
    private String format;

    @Column(name = "byte_file_location", nullable = false, length = 40)
    private String byteFileLocation;


}
