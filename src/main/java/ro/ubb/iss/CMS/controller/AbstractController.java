package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.MyExceptions.UnableToCreateStorageDirectoryException;
import ro.ubb.iss.CMS.MyExceptions.UnableToSaveFileToStorage;
import ro.ubb.iss.CMS.converter.AbstractConverter;
import ro.ubb.iss.CMS.Services.AbstractService;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.AbstractsDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AbstractController {

  public static final Logger log = LoggerFactory.getLogger(AbstractController.class);

  @Autowired private AbstractService service;

  @Autowired private AbstractConverter converter;

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

  @RequestMapping(value = "/abstracts", method = RequestMethod.POST)
  public ResponseEntity<AbstractDto> saveAbstract(@RequestBody AbstractDto abstractDto) {
    log.trace("saveAbstract - method entered abstractDto={}", abstractDto);
    Abstract result;
    try {
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

  @RequestMapping(value = "/abstracts", method = RequestMethod.PUT)
  public ResponseEntity<AbstractDto> updateAbstract(@RequestBody AbstractDto abstractDto) {
    log.trace("updateAbstract - method entered: abstractDto={}", abstractDto);
    Abstract anAbstract = converter.convertDtoToModel(abstractDto);
    AbstractDto result =
        converter.convertModelToDto(
            service.updateAbstract(
                anAbstract.getAbstractID(),
                anAbstract.getFormat(),
                anAbstract.getByteFileLocation()));
    log.trace("updateAbstract - method finished: result={}", result);
    if (result == null) return new ResponseEntity<>(AbstractDto.builder().build(),HttpStatus.BAD_REQUEST);

    return new ResponseEntity<>(result, HttpStatus.OK);
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
