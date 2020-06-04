package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import ro.ubb.iss.CMS.MyExceptions.UnableToSaveFileToStorage;
import ro.ubb.iss.CMS.Repository.AbstractRepository;
import ro.ubb.iss.CMS.Repository.ConferenceDataRepository;
import ro.ubb.iss.CMS.Repository.ConferenceRepository;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.ConferenceData;
import ro.ubb.iss.CMS.utils.SaveToStorageUtility;

import javax.persistence.EntityManager;
import java.io.File;
import java.util.List;
import java.util.Optional;
@Service
public class ConferenceDataServiceImplementation implements  ConferenceDataService{

    private static final Logger log = LoggerFactory.getLogger(ConferenceDataServiceImplementation.class);

    private static final String MAIN_STORAGE =
            "." + File.separator + "storage" + File.separator + "conferenceData";

    @Autowired
    private ConferenceDataRepository conferenceDataRepository;

//    @Autowired
//    private EntityManager m

    @Override
    public Optional<ConferenceData> findConferenceData(int conferenceID) {
        log.trace("findConferenceData - method entered conferenceID={}", conferenceID);
        Optional<ConferenceData> result = conferenceDataRepository.findById(conferenceID);
        log.trace("findConferenceData - method exit result={}", result);
        return result;
    }

    @Override
    @Transactional
    public ConferenceData updateConferenceData(int conferenceID, String format, String byteFileLocation,String about,String callForPaper) {
        log.trace(
                "updateConferenceData - method entered: conferenceID={}, format={}, byteFileLocation={},about={}, callForPaper={}",
                conferenceID,
                format,
                about,
                byteFileLocation,callForPaper);

        Optional<ConferenceData> conferenceDataOptional = conferenceDataRepository.findById(conferenceID);

        conferenceDataOptional.ifPresent(
                newConferenceData -> {
                    newConferenceData.setByteFileLocation(format);
                    newConferenceData.setFormat(byteFileLocation);
                    newConferenceData.setAbout(about);
                    newConferenceData.setAbout(callForPaper);
                    log.debug("updateConferenceData - updated: newConferenceData={}", newConferenceData);
                });
        log.trace("updateConferenceData - method finished result={}", conferenceDataOptional);
        return conferenceDataOptional.orElse(null);
    }

    @Override
    public ConferenceData saveConferenceData(int conferenceID,String format, String byteFileLocation,String about, String callForPaper) {
        log.trace(
                "saveConferenceData - method entered: conferenceID={}, format={}, byteFileLocation={}, about={}, callForPaper={}",conferenceID, format, byteFileLocation, about, callForPaper);

        ConferenceData newConferenceData =
                ConferenceData.builder().conferenceID(conferenceID).format(format).byteFileLocation(byteFileLocation).about(about).callForPaper(callForPaper).build();
        conferenceDataRepository.save(newConferenceData);
        log.trace("saveConferenceData - method finished result={}", newConferenceData);
        return newConferenceData;
    }

    @Override
    public void deleteConferenceData(int conferenceID) {
        log.trace("deleteConferenceData - method entered: conferenceID={}", conferenceID);
        conferenceDataRepository.deleteById(conferenceID);
        log.trace("deleteConferenceData - method finished");
    }
}
