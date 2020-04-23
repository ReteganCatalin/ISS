package ro.ubb.iss.CMS.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.dto.ConferenceDto;

@Component
public class ConferenceConverter implements BaseConverter<Conference, ConferenceDto> {
    @Override
    public Conference convertDtoToModel(ConferenceDto conferenceDto) {
        return Conference.builder().conferenceID(conferenceDto.getConferenceID())
                .endDate(conferenceDto.getEndDate())
                .startDate(conferenceDto.getStartDate())
                .paperDeadline(conferenceDto.getPaperDeadline())
                .proposalDeadline(conferenceDto.getProposalDeadline())
                .build();
    }

    @Override
    public ConferenceDto convertModelToDto(Conference conference) {
        return ConferenceDto.builder()
                .conferenceID(conference.getConferenceID())
                .endDate(conference.getEndDate())
                .startDate(conference.getStartDate())
                .proposalDeadline(conference.getProposalDeadline())
                .paperDeadline(conference.getPaperDeadline())
                .build();
    }
}
