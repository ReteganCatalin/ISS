package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.iss.CMS.MyExceptions.UnableToSaveFileToStorage;
import ro.ubb.iss.CMS.Repository.PresentationRepository;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.utils.SaveToStorageUtility;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class PresentationServiceImplementation implements PresentationService {
  private static final Logger log =
      LoggerFactory.getLogger(PresentationServiceImplementation.class);

  @Autowired private PresentationRepository presentationRepository;

  private static final String MAIN_STORAGE =
      "." + File.separator + "storage" + File.separator + "presentations";

  @Override
  public Optional<Presentation> findPresentation(int presentationID) {
    log.trace("findPresentation - method entered presentationID={}", presentationID);
    Optional<Presentation> result = presentationRepository.findById(presentationID);
    log.trace("findPresentation - method exit result={}", result);
    return result;
  }

  @Override
  public List<Presentation> findAll() {
    log.trace("findAll - method entered");
    List<Presentation> result = presentationRepository.findAll();
    log.trace("findAll - method exit result={}", result);
    return result;
  }

  @Override
  @Transactional
  public Presentation updatePresentation(
      int presentationID,
      Section sectionID,
      Conference conferenceID,
      String format,
      String byteFileLocation) {
    log.trace(
        "updatePresentation - method entered: presentationID={}, sectionID={}, conferenceID={}, format={}, byteFileLocation={}",
        presentationID,
        sectionID,
        conferenceID,
        format,
        byteFileLocation);

    Optional<Presentation> abstractOptional = presentationRepository.findById(presentationID);

    abstractOptional.ifPresent(
        newPresentation -> {
          newPresentation.setSection(sectionID);
          //newPresentation.setConference(conferenceID);
          newPresentation.setFormat(format);
          newPresentation.setByteFileLocation(byteFileLocation);
          log.debug("updatePresentation - updated: newPresentation={}", newPresentation);
        });
    log.trace("updatePresentation - method finished result={}", abstractOptional);
    return abstractOptional.orElse(null);
  }

  @Override
  public Presentation savePresentation(
      Section sectionID, Conference conferenceID, String format, String byteFileLocation) {
    log.trace(
        "savePresentation - method entered: sectionID={}, conferenceID={}, format={}, byteFileLocation={}",
        sectionID,
        conferenceID,
        format,
        byteFileLocation);

    String newFileLocation = SaveToStorageUtility.saveFileToStorage(MAIN_STORAGE, byteFileLocation);
    if (newFileLocation == null)
      throw new UnableToSaveFileToStorage("Was not able to save the file to storage");

    Presentation newPresentation =
        Presentation.builder()
            .section(sectionID)
            //.conference(conferenceID)
            .format(format)
            .byteFileLocation(byteFileLocation)
            .build();

    presentationRepository.save(newPresentation);

    log.trace("savePresentation - method finished result={}", newPresentation);
    return newPresentation;
  }

  @Override
  public void deletePresentation(int presentationID) {
    log.trace("deletePresentation - method entered: presentationID={}", presentationID);
    presentationRepository.deleteById(presentationID);
    log.trace("deletePresentation - method finished");
  }
}
