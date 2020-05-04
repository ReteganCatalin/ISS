package ro.ubb.iss.CMS.domain;

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
@Builder
public class Abstract {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "abstract_id")
  private Integer abstractID;

  @Column(name = "format", nullable = false, length = 5)
  private String format;

  @Column(name = "byte_file_location", nullable = false, length = 40)
  private String byteFileLocation;

  @OneToOne(mappedBy = "anAbstract", orphanRemoval = true, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude private Proposal proposal;
}
