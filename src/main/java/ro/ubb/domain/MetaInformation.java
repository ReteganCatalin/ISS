package ro.ubb.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "meta_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MetaInformation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "meta_info_id")
  private Integer metaInfoId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "keywords", nullable = false)
  private String keywords;

  @Column(name = "topics", nullable = false)
  private String topics;

  @OneToOne(mappedBy = "metaInformation", fetch = FetchType.LAZY)
  private Proposal proposal;
}
