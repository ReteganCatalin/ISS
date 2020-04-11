package domain;

import javax.persistence.*;

@Entity
@Table(name = "Author_list")
public class AuthorList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "proposal_id",nullable = false)
    private Integer proposalId;
}
