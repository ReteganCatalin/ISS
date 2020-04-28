package ro.ubb.iss.CMS.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Converter.MetaInfoConverter;
import ro.ubb.iss.CMS.Services.MetaInformationService;
import ro.ubb.iss.CMS.domain.MetaInformation;
import ro.ubb.iss.CMS.dto.MetaInfoDto;
import ro.ubb.iss.CMS.dto.MetaInfosDto;

import java.util.Optional;

@RestController
public class MetaInformationController {

  public static final Logger log = LoggerFactory.getLogger(MetaInformationController.class);

  @Autowired private MetaInformationService service;

  @Autowired private MetaInfoConverter converter;

  @RequestMapping(value = "/meta_informations", method = RequestMethod.GET)
  public MetaInfosDto getAllMetaInformations() {
    log.trace("getAllMetaInformations - method entered");
    MetaInfosDto result = new MetaInfosDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllMetaInformations - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/meta_informations/{id}", method = RequestMethod.GET)
  public MetaInfoDto getMetaInformation(@PathVariable Integer id) {
    log.trace("getMetaInformation - method entered id={}", id);
    Optional<MetaInformation> metaInformation = service.findMetaInformation(id);
    MetaInfoDto result = null;
    if (metaInformation.isPresent()) result = converter.convertModelToDto(metaInformation.get());
    log.trace("getConference - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/meta_informations", method = RequestMethod.POST)
  public MetaInfoDto saveMetaInformation(@RequestBody MetaInfoDto metaInfoDto) {
    log.trace("saveMetaInformation - method entered metaInfoDto={}", metaInfoDto);
    MetaInformation result =
        service.saveMetaInformation(
            metaInfoDto.getName(), metaInfoDto.getKeywords(), metaInfoDto.getTopics());
    MetaInfoDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveMetaInformation - method finished: result={}", resultToReturn);
    return resultToReturn;
  }

  @RequestMapping(value = "/meta_informations", method = RequestMethod.PUT)
  public MetaInfoDto updateMetaInformation(@RequestBody MetaInfoDto metaInfoDto) {
    log.trace("updateMetaInformation - method entered: metaInfoDto={}", metaInfoDto);
    MetaInfoDto result =
        converter.convertModelToDto(
            service.updateMetaInformation(
                metaInfoDto.getMetaInfoId(),
                metaInfoDto.getName(),
                metaInfoDto.getKeywords(),
                metaInfoDto.getTopics()));
    log.trace("updateMetaInformation - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/meta_informations/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteMetaInformation(@PathVariable Integer id) {
    log.trace("deleteMetaInformation - method entered: id={}", id);

    try {
      service.deleteMetaInformation(id);
    } catch (RestClientException ex) {
      log.trace("deleteMetaInformation - exception caught ex={}", ex.getMessage());
      log.trace("deleteMetaInformation - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteMetaInformation - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
