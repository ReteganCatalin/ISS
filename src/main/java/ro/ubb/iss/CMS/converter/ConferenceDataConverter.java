package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.ConferenceData;
import ro.ubb.iss.CMS.dto.ConferenceDataDto;
import ro.ubb.iss.CMS.dto.ConferenceDto;

@Component
public class ConferenceDataConverter implements BaseConverter<ConferenceData, ConferenceDataDto> {
    @Override
    public ConferenceData convertDtoToModel(ConferenceDataDto conferenceDataDto){

        ConferenceData conferenceData=ConferenceData.builder()
        .conferenceID(conferenceDataDto.getConferenceID())
        .about(conferenceDataDto.getAbout())
        .byteFileLocation(conferenceDataDto.getByteFileLocation())
        .callForPaper(conferenceDataDto.getCallForPaper())
        .format(conferenceDataDto.getFormat())
        .build();
        return conferenceData;
        }
    @Override
    public ConferenceDataDto convertModelToDto(ConferenceData conferenceData) {
        return ConferenceDataDto.builder()
                .conferenceID(conferenceData.getConferenceID())
                .about(conferenceData.getAbout())
                .byteFileLocation(conferenceData.getByteFileLocation())
                .callForPaper(conferenceData.getCallForPaper())
                .format(conferenceData.getFormat())
                .build();
    }
}
