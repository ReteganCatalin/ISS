package ro.ubb.iss.CMS.domain;


import lombok.*;

import javax.persistence.*;

@Builder(access = AccessLevel.PUBLIC)
@Data
@Entity
@Table(name="person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer person_id;


    @Column(name = "name", nullable = false, length = 5)
    private String name;

    @Column(name = "height", nullable = false, length = 40)
    private String height;


}
