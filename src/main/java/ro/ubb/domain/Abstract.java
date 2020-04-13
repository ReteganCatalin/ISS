package ro.ubb.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "abstract")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Abstract {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "abstract_id")
  private Integer abstractID;

  @Column(name = "format", nullable = false, columnDefinition = "VARCHAR(24)")
  private String format;

  @Lob
  @Column(name = "byte_file", columnDefinition = "BLOB", nullable = false)
  private byte[] byteFile;

  @OneToOne(mappedBy = "anAbstract", fetch = FetchType.LAZY)
  private Proposal proposal;
}
