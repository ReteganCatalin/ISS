package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "paper")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Paper {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "paper_id", nullable = false)
  private Integer paperId;

  @Column(name = "format", nullable = false, length = 5)
  private String format;

  @Column(name = "byte_file_location", nullable = false, length = 40)
  private String byteFileLocation;

  @OneToOne(mappedBy = "paper", fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude private Proposal proposal;
}
