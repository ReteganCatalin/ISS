package ro.ubb.iss.CMS.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class ConferenceDto {

  private Integer conferenceID;
  private String name;
  private Date startDate;
  private Date endDate;
  private Date proposalDeadline;
  private Date paperDeadline;
}
