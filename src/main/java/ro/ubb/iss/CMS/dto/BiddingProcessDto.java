package ro.ubb.iss.CMS.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class BiddingProcessDto {

  private Integer bidID;
  private Integer conferenceID;
  private Date deadline;
}
