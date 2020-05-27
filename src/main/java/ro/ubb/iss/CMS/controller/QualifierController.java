package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.AbstractService;
import ro.ubb.iss.CMS.Services.QualifierService;
import ro.ubb.iss.CMS.converter.AbstractConverter;
import ro.ubb.iss.CMS.converter.QualifierConverter;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.domain.Qualifier;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.AbstractsDto;
import ro.ubb.iss.CMS.dto.QualifierDto;
import ro.ubb.iss.CMS.dto.QualifiersDto;

import java.util.Optional;

@RestController
public class QualifierController {

  public static final Logger log = LoggerFactory.getLogger(QualifierController.class);

  @Autowired private QualifierService service;

  @Autowired private QualifierConverter converter;

  @RequestMapping(value = "/qualifiers", method = RequestMethod.GET)
  public QualifiersDto getAllQualifiers() {
    log.trace("getAllQualifiers - method entered");
    QualifiersDto result = new QualifiersDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllQualifiers - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/qualifiers/{id}", method = RequestMethod.GET)
  public QualifierDto getQualifier(@PathVariable Integer id) {
    log.trace("getQualifier - method entered id={}", id);
    Optional<Qualifier> anAbstract = service.findQualifier(id);
    QualifierDto result = null;
    if (anAbstract.isPresent()) result = converter.convertModelToDto(anAbstract.get());
    log.trace("getQualifier - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/qualifiers", method = RequestMethod.POST)
  public QualifierDto saveQualifier(@RequestBody QualifierDto qualifierDto) {
    log.trace("saveQualifier - method entered qualifierDto={}", qualifierDto);
    Qualifier result = service.saveQualifier(qualifierDto.getName());

    QualifierDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveQualifier - method finished: result={}", resultToReturn);
    return resultToReturn;
  }

  @RequestMapping(value = "/qualifiers", method = RequestMethod.PUT)
  public QualifierDto updateQualifier(@RequestBody QualifierDto qualifierDto) {
    log.trace("updateQualifier - method entered: qualifierDto={}", qualifierDto);
    QualifierDto result =
        converter.convertModelToDto(
            service.updateQualifier(qualifierDto.getQualifierID(), qualifierDto.getName()));
    log.trace("updateQualifier - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/qualifiers/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteQualifier(@PathVariable Integer id) {
    log.trace("deleteQualifier - method entered: id={}", id);
    try {
      service.deleteQualifier(id);
    } catch (RestClientException ex) {
      log.trace("deleteQualifier - exception caught ex={}", ex.getMessage());
      log.trace("deleteQualifier - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteQualifier - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
