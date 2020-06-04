package ro.ubb.iss.CMS.dto;

import lombok.*;
import ro.ubb.iss.CMS.domain.ConferenceData;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class ConferenceDatasDto {
    private List<ConferenceDataDto> conferenceDataDtos;
}
