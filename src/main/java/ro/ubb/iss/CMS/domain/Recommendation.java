package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "recommendation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Recommendation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "recommendation_id")
  private Integer recommendationID;

  @OneToOne(optional = false,orphanRemoval = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "review_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Review review;

  @Column(name = "recommendation_message")
  String recommendationMessage;
}
