package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;
import ro.ubb.iss.CMS.MyExceptions.UnableToCreateStorageDirectoryException;
import ro.ubb.iss.CMS.MyExceptions.UnableToSaveFileToStorage;
import ro.ubb.iss.CMS.Services.ConferenceDataService;
import ro.ubb.iss.CMS.Services.FileService;
import ro.ubb.iss.CMS.converter.ConferenceDataConverter;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.domain.ConferenceData;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.ConferenceDataDto;
import ro.ubb.iss.CMS.dto.ConferenceDatasDto;

import java.util.Optional;

@RestController
public class ConferenceDataController {

  public static final Logger log = LoggerFactory.getLogger(AbstractController.class);

  @Autowired private ConferenceDataService service;

  @Autowired private ConferenceDataConverter converter;
  @Autowired
  FileService fileService;
  //    @RequestMapping(value = "/conferenceDatas", method = RequestMethod.GET)
  //    public ResponseEntity<ConferenceDatasDto> getAllConferenceDatas() {
  //        log.trace("getAllConferenceDatas - method entered");
  //        ConferenceDatasDto result = new
  // ConferenceDatasDto(converter.convertModelsToDtos(service.findAll()));
  //        log.trace("getAllConferenceDatas - method finished: result={}", result);
  //        return new ResponseEntity<>(result, HttpStatus.OK);
  //    }

  @RequestMapping(value = "/conference_data/{id}", method = RequestMethod.GET)
  public ResponseEntity<ConferenceDataDto> getConferenceData(@PathVariable Integer id) {

    log.trace("getConferenceData - method entered id={}", id);
    Optional<ConferenceData> anAbstract = service.findConferenceData(id);
    ConferenceDataDto result = null;
    if (anAbstract.isPresent()) result = converter.convertModelToDto(anAbstract.get());
    log.trace("getConferenceData - method finished: result={}", result);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
  @RequestMapping(value = "/conference_data/{id}/file", method = RequestMethod.GET)
  public ResponseEntity<Resource> getAbstractFile(@PathVariable Integer id) {

    log.trace("getConferenceDataFile - method entered id={}", id);
    Optional<ConferenceData> conferenceDataResult = service.findConferenceData(id);
    ConferenceDataDto conferenceDataDto = null;
    if (conferenceDataResult.isPresent()) conferenceDataDto = converter.convertModelToDto(conferenceDataResult.get());
    Resource file = fileService.downloadFile(conferenceDataDto.getByteFileLocation());

    log.trace("getConferenceDataFile - method finished: result={}", file);
    return new ResponseEntity<>(file,HttpStatus.OK);
  }
  @RequestMapping(value = "/conference_data_file", method = RequestMethod.POST)
  public ResponseEntity<ConferenceDataDto> saveConferenceDataFile(@RequestParam("id") Integer conferenceDataID,@RequestParam("file") MultipartFile file) {

    ConferenceData conferenceData=service.findConferenceData(conferenceDataID).get();
    log.trace("saveConferenceDataFile - method entered conferenceData={}", conferenceData);
    ConferenceData result;
    String path = "";
    try {
      path=fileService.store(file);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    try {
      conferenceData.setByteFileLocation(path);
      conferenceData.setFormat("csp");
      result = service.updateConferenceData(conferenceData.getConferenceID(),conferenceData.getFormat(),conferenceData.getByteFileLocation(),conferenceData.getAbout(),conferenceData.getCallForPaper());
    } catch (UnableToCreateStorageDirectoryException | UnableToSaveFileToStorage ex) {
      log.trace("saveConferenceDataFile - exception occurred: ex={}", ex.getMessage());
      ex.printStackTrace();
      return new ResponseEntity<>(ConferenceDataDto.builder().build(),HttpStatus.BAD_REQUEST);
    }
    ConferenceDataDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveConferenceDataFile - method finished: result={}", resultToReturn);
    return new ResponseEntity<>(resultToReturn, HttpStatus.OK);
  }
  @RequestMapping(value = "/conference_data", method = RequestMethod.POST)
  public ResponseEntity<ConferenceDataDto> saveConferenceData(
      @RequestBody ConferenceDataDto conferenceDataDto) {
    log.trace("saveConferenceData - method entered conferenceDataDto={}", conferenceDataDto);
    ConferenceData result;
    try {
      result =
          service.saveConferenceData(
              conferenceDataDto.getFormat(),
              conferenceDataDto.getByteFileLocation(),
              conferenceDataDto.getAbout(),
              conferenceDataDto.getCallForPaper()
          );
    } catch (UnableToCreateStorageDirectoryException | UnableToSaveFileToStorage ex) {
      log.trace("saveConferenceData - exception occurred: ex={}", ex.getMessage());
      ex.printStackTrace();
      return new ResponseEntity<>(ConferenceDataDto.builder().build(), HttpStatus.BAD_REQUEST);
    }
    ConferenceDataDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveConferenceData - method finished: result={}", resultToReturn);
    return new ResponseEntity<>(resultToReturn, HttpStatus.OK);
  }

  @RequestMapping(value = "/conference_data", method = RequestMethod.PUT)
  public ResponseEntity<ConferenceDataDto> updateConferenceData(
      @RequestBody ConferenceDataDto conferenceDataDto) {
    log.trace("updateConferenceData - method entered: conferenceDataDto={}", conferenceDataDto);
    ConferenceData conferenceData = converter.convertDtoToModel(conferenceDataDto);
    ConferenceDataDto result =
        converter.convertModelToDto(
            service.updateConferenceData(
                conferenceData.getConferenceID(),
                conferenceDataDto.getFormat(),
                conferenceDataDto.getByteFileLocation(),
                conferenceDataDto.getAbout(),
                conferenceDataDto.getCallForPaper()));
    log.trace("updateAbstract - method finished: result={}", result);
    if (result == null) {
      log.trace("updateConferenceData - finished: result={}", result);
      return new ResponseEntity<>(ConferenceDataDto.builder().build(), HttpStatus.BAD_REQUEST);
    }

    log.trace("updateConferenceData - finished: result={}", result);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/conference_data/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteConferenceData(@PathVariable Integer id) {
    log.trace("deleteConferenceData - method entered: id={}", id);
    try {
      service.deleteConferenceData(id);
    } catch (RestClientException ex) {
      log.trace("deleteConferenceData - exception caught ex={}", ex.getMessage());
      log.trace("deleteConferenceData - method finished bad");
      return new ResponseEntity<>(ConferenceDataDto.builder().build(), HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteConferenceData - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
