package ro.ubb.iss.CMS.domain;

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
@Builder
public class MetaInformation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "meta_info_id")
  private Integer metaInfoId;

  @Column(name = "name", nullable = false, length = 40)
  private String name;

  @Column(name = "keywords", nullable = false, length = 40)
  private String keywords;

  @Column(name = "topics", nullable = false, length = 40)
  private String topics;

  @OneToOne(mappedBy = "metaInformation", fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Proposal proposal;
}
