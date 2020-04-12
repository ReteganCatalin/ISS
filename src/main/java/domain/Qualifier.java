package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Qualifier")
public class Qualifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualifier_id")
    private Integer qualifierID;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "qualifier",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Review> reviews = new HashSet<>();
}
