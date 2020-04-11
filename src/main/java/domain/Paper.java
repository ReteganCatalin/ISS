package domain;

import javax.persistence.*;

@Entity
@Table(name = "Paper")
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paper_id",nullable = false)
    private Integer paperId;
    @Column(name = "format",nullable = false)
    private String format;
    @Lob
    @Column(name = "byte_file",columnDefinition = "BLOB",nullable = false)
    private byte[] byteFile;
}
