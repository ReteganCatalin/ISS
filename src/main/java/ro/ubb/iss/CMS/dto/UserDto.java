package ro.ubb.iss.CMS.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class UserDto {
    private Integer userID;
    private String username;
    private String password;
    private Boolean isValidated;
    private Integer userInfoID;
}
