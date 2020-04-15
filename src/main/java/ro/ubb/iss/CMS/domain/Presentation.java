package ro.ubb.iss.CMS.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "presentation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Presentation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "presentation_id")
  private Integer presentationID;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "conference_id")
  Conference conference;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "section_id")
  Section section;

  @Column(name = "format", nullable = false)
  private String format;

  @Column(name = "byte_file", nullable = false)
  private byte[] byteFile;
}
