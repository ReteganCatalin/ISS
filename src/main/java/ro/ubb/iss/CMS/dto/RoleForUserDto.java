package ro.ubb.iss.CMS.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class RoleForUserDto {
    private Integer userID;
    private Integer roleID;
}
