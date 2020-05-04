package ro.ubb.iss.CMS.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class RecommendationDto {
    private Integer recommendationID;
    private Integer reviewID;
    private String recommendationMessage;
}
