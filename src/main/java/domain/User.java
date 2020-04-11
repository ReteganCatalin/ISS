package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @OneToMany(mappedBy = "permission",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Permission> permissions = new HashSet<>();
}
