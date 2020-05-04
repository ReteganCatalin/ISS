package ro.ubb.iss.CMS.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class ReviewDto {
    private Integer reviewID;
    private Integer proposalID;
    private Integer qualifierID;
    private Integer userID;
}
