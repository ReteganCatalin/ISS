package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.ConferenceData;
import ro.ubb.iss.CMS.dto.ConferenceDto;

@Component
public class ConferenceConverter implements BaseConverter<Conference, ConferenceDto> {
  @Override
  public Conference convertDtoToModel(ConferenceDto conferenceDto) {

    Conference conference = Conference.builder()
        .conferenceID(conferenceDto.getConferenceID())
        .name(conferenceDto.getName())
        .endDate(conferenceDto.getEndDate())
        .startDate(conferenceDto.getStartDate())
        .paperDeadline(conferenceDto.getPaperDeadline())
        .proposalDeadline(conferenceDto.getProposalDeadline())
            .reviewDeadline(conferenceDto.getReviewDeadline())
            .chair(conferenceDto.getChair())
        .build();
    //conferenceData.setConference(conference);
    //conference.setConferenceData(conferenceData);
    return conference;
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
            .reviewDeadline(conference.getReviewDeadline())
            .chair(conference.getChair())
      ///  .about(conference.getConferenceData().getAbout())
      //  .byteFileLocation(conference.getConferenceData().getByteFileLocation())
       // .callForPaper(conference.getConferenceData().getCallForPaper())
       // .format(conference.getConferenceData().getFormat())
        .build();
  }
}
