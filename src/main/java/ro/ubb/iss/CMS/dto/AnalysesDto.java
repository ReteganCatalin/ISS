package ro.ubb.iss.CMS.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class AnalysesDto {

  private List<AnalysisDto> analysisDtoSet;
}
