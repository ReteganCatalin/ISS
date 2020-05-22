package ro.ubb.iss.CMS.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class ConferenceProposalDtos {

  List<ConferenceProposalDto> conferenceProposalDtos;
}
