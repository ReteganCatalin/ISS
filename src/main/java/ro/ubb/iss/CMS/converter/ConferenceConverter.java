package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.ConferenceData;
import ro.ubb.iss.CMS.dto.ConferenceDto;

@Component
public class ConferenceConverter implements BaseConverter<Conference, ConferenceDto> {
  @Override
  public Conference convertDtoToModel(ConferenceDto conferenceDto) {
    return Conference.builder()
        .conferenceID(conferenceDto.getConferenceID())
        .name(conferenceDto.getName())
        .endDate(conferenceDto.getEndDate())
        .startDate(conferenceDto.getStartDate())
        .paperDeadline(conferenceDto.getPaperDeadline())
        .proposalDeadline(conferenceDto.getProposalDeadline())
        .conferenceData(
            ConferenceData.builder()
                .conferenceID(conferenceDto.getConferenceID())
                .about(conferenceDto.getAbout())
                .byteFileLocation(conferenceDto.getByteFileLocation())
                .callForPaper(conferenceDto.getCallForPaper())
                .format(conferenceDto.getFormat())
                .build())
        .build();
  }

  @Override
  public ConferenceDto convertModelToDto(Conference conference) {
    return ConferenceDto.builder()
        .conferenceID(conference.getConferenceID())
        .name(conference.getName())
        .endDate(conference.getEndDate())
        .startDate(conference.getStartDate())
        .proposalDeadline(conference.getProposalDeadline())
        .paperDeadline(conference.getPaperDeadline())
        .about(conference.getConferenceData().getAbout())
        .byteFileLocation(conference.getConferenceData().getByteFileLocation())
        .callForPaper(conference.getConferenceData().getCallForPaper())
        .format(conference.getConferenceData().getFormat())
        .build();
  }
}
