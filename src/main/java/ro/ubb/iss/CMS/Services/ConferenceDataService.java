package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.domain.ConferenceData;

import java.util.List;
import java.util.Optional;

public interface ConferenceDataService {

    Optional<ConferenceData> findConferenceData(int conferenceID);


    ConferenceData updateConferenceData(int conferenceID, String format, String byteFileLocation,String about,String callForPaper);

    ConferenceData saveConferenceData(String format, String byteFileLocation,String about,String callForPaper);

    void deleteConferenceData(int conferenceDataID);
}
