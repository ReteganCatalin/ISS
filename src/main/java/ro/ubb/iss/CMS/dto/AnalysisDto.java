package ro.ubb.iss.CMS.dto;

import lombok.*;
import ro.ubb.iss.CMS.domain.AnalysisKey;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class AnalysisDto {

    AnalysisKey analysisKey;
    String briefAnalysis;
    Boolean refuse;

}
