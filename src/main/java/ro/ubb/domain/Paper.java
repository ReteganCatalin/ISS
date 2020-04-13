package ro.ubb.domain;

import javax.persistence.*;

@Entity
@Table(name = "paper")
public class Paper {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "paper_id", nullable = false)
  private Integer paperId;

  @Column(name = "format", nullable = false)
  private String format;

  @Lob
  @Column(name = "byte_file", columnDefinition = "BLOB", nullable = false)
  private byte[] byteFile;

  @OneToOne(mappedBy = "paper", fetch = FetchType.LAZY)
  private Proposal proposal;
}
