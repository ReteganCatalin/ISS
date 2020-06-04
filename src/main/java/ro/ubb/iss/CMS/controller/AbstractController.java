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
import ro.ubb.iss.CMS.Services.FileService;
import ro.ubb.iss.CMS.converter.AbstractConverter;
import ro.ubb.iss.CMS.Services.AbstractService;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.domain.Paper;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.AbstractsDto;
import ro.ubb.iss.CMS.dto.PaperDto;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AbstractController {

  public static final Logger log = LoggerFactory.getLogger(AbstractController.class);

  @Autowired private AbstractService service;

  @Autowired private AbstractConverter converter;
  @Autowired
  FileService fileService;
  @RequestMapping(value = "/abstracts", method = RequestMethod.GET)
  public ResponseEntity<AbstractsDto> getAllAbstracts() {
    log.trace("getAllAbstracts - method entered");
    AbstractsDto result = new AbstractsDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllAbstracts - method finished: result={}", result);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/abstracts/{id}", method = RequestMethod.GET)
  public ResponseEntity<AbstractDto> getAbstract(@PathVariable Integer id) {

    log.trace("getAbstract - method entered id={}", id);
    Optional<Abstract> anAbstract = service.findAbstract(id);
    AbstractDto result = null;
    if (anAbstract.isPresent()) result = converter.convertModelToDto(anAbstract.get());
    log.trace("getAbstract - method finished: result={}", result);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
  @RequestMapping(value = "/abstracts/{id}/file", method = RequestMethod.GET)
  public ResponseEntity<Resource> getAbstractFile(@PathVariable Integer id) {

    log.trace("getAbstractFile - method entered id={}", id);
    Optional<Abstract> abstractResult = service.findAbstract(id);
    AbstractDto abstractDto = null;
    if (abstractResult.isPresent()) abstractDto = converter.convertModelToDto(abstractResult.get());
    Resource file = fileService.downloadFile(abstractDto.getByteFileLocation());

    log.trace("getAbstractFile - method finished: result={}", file);
    return new ResponseEntity<>(file,HttpStatus.OK);
  }

  @RequestMapping(value = "/abstracts", method = RequestMethod.POST)
  public ResponseEntity<AbstractDto> saveAbstract(@RequestParam("file") MultipartFile file) {

    AbstractDto abstractDto=new AbstractDto(0,"","");
    log.trace("saveAbstract - method entered abstractDto={}", abstractDto);
    Abstract result;
    String path = "";
    try {
      path=fileService.store(file);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    try {
      abstractDto.setByteFileLocation(path);
      abstractDto.setFormat("csp");
      result = service.saveAbstract(abstractDto.getFormat(), abstractDto.getByteFileLocation());
    } catch (UnableToCreateStorageDirectoryException | UnableToSaveFileToStorage ex) {
      log.trace("saveAbstract - exception occurred: ex={}", ex.getMessage());
      ex.printStackTrace();
      return new ResponseEntity<>(AbstractDto.builder().build(),HttpStatus.BAD_REQUEST);
    }
    AbstractDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveAbstract - method finished: result={}", resultToReturn);
    return new ResponseEntity<>(resultToReturn, HttpStatus.OK);
  }

  @RequestMapping(value = "/abstracts/{abstractID}", method = RequestMethod.PUT)
  public ResponseEntity<AbstractDto> updateAbstract(@RequestParam("file") MultipartFile file, @PathVariable Integer abstractID) {
    /*log.trace("updateAbstract - method entered: abstractDto={}", abstractDto);
    Abstract anAbstract = converter.convertDtoToModel(abstractDto);
    AbstractDto result =
        converter.convertModelToDto(
            service.updateAbstract(
                anAbstract.getAbstractID(),
                anAbstract.getFormat(),
                anAbstract.getByteFileLocation()));
    log.trace("updateAbstract - method finished: result={}", result);
    if (result == null) return new ResponseEntity<>(AbstractDto.builder().build(),HttpStatus.BAD_REQUEST);

    return new ResponseEntity<>(result, HttpStatus.OK);*/

    AbstractDto abstractDto=new AbstractDto(abstractID,"","");
    log.trace("updateAbstract - method entered abstractDto={}", abstractDto);
    Abstract result;
    String path = "";
    try {
      path=fileService.store(file);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    try {
      abstractDto.setByteFileLocation(path);
      abstractDto.setFormat("csp");
      System.out.println(abstractDto.getFormat());
      result = service.updateAbstract(abstractDto.getAbstractID(), abstractDto.getFormat(), abstractDto.getByteFileLocation());
    } catch (UnableToCreateStorageDirectoryException | UnableToSaveFileToStorage ex) {
      log.trace("updateAbstract - exception occurred: ex={}", ex.getMessage());
      ex.printStackTrace();
      return new ResponseEntity<>(AbstractDto.builder().build(),HttpStatus.BAD_REQUEST);
    }
    AbstractDto resultToReturn = converter.convertModelToDto(result);
    log.trace("updateAbstract - method finished: result={}", resultToReturn);
    return new ResponseEntity<>(resultToReturn, HttpStatus.OK);

  }

  @RequestMapping(value = "/abstracts/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteAbstract(@PathVariable Integer id) {
    log.trace("deleteAbstract - method entered: id={}", id);
    try {
      service.deleteAbstract(id);
    } catch (RestClientException ex) {
      log.trace("deleteAbstract - exception caught ex={}", ex.getMessage());
      log.trace("deleteAbstract - method finished bad");
      return new ResponseEntity<>(AbstractDto.builder().build(),HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteAbstract - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
