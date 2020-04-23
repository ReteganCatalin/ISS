package ro.ubb.iss.CMS.dto;

import lombok.*;
import ro.ubb.iss.CMS.domain.AnalysisKey;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class AnalysisDto {

    private Integer bidID;
    private Integer userID;
    private Integer proposalID;
    private String briefAnalysis;
    private Boolean refuse;

}
