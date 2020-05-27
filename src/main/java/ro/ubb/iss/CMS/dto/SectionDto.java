package ro.ubb.iss.CMS.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class SectionDto {
  private Integer sectionID;
  private Integer supervisorID;
  private Integer conferenceID;
  private Integer price;
  private Date dateOfPresentation;
}
