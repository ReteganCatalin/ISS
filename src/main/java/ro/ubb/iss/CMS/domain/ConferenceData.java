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
public class ConferenceData implements Serializable {


    @Id
    @Column(name = "id")
    private Integer conferenceID;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conference_id")
    @MapsId
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
