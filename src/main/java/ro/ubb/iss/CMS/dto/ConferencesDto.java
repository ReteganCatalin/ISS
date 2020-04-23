package ro.ubb.iss.CMS.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class ConferencesDto {

    Set<ConferenceDto> conferenceDtos;

}
