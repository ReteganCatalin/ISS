package ro.ubb.iss.CMS.domain;

import lombok.*;
import ro.ubb.iss.CMS.domain.Conference;

import javax.persistence.*;

@Entity
@Table(name = "presentation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
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

  @Column(name = "byte_file_location", nullable = false, length = 40)
  private String byteFileLocation;
}
