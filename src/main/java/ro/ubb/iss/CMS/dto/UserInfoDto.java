package ro.ubb.iss.CMS.dto;

import lombok.*;
import ro.ubb.iss.CMS.domain.Proposal;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class UserInfoDto {

  private Integer userInfoId;
  private String name;
  private String affiliation;
  private String emailAddress;
  private String webPageAddress;
  private Boolean affiliationValidated;
}
