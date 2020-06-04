package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "chair")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Chair {
    @Id
    @Column(name = "conference_id")
    private Integer conferenceID;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "conference_id",referencedColumnName = "conference_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Conference conference;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;




}
