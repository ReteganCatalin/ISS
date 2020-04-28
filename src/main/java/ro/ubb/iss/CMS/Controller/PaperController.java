package ro.ubb.iss.CMS.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Converter.PaperConverter;
import ro.ubb.iss.CMS.Services.PaperService;
import ro.ubb.iss.CMS.domain.Paper;
import ro.ubb.iss.CMS.dto.PaperDto;
import ro.ubb.iss.CMS.dto.PapersDto;

import java.util.Optional;

@RestController
public class PaperController {

  public static final Logger log = LoggerFactory.getLogger(PaperController.class);

  @Autowired private PaperService service;

  @Autowired private PaperConverter converter;

  @RequestMapping(value = "/papers", method = RequestMethod.GET)
  public PapersDto getAllPapers() {
    log.trace("getAllPapers - method entered");
    PapersDto result = new PapersDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllPapers - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/papers/{id}", method = RequestMethod.GET)
  public PaperDto getPaper(@PathVariable Integer id) {
    log.trace("getPaper - method entered id={}", id);
    Optional<Paper> metaInformation = service.findPaper(id);
    PaperDto result = null;
    if (metaInformation.isPresent()) result = converter.convertModelToDto(metaInformation.get());
    log.trace("getPaper - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/papers", method = RequestMethod.POST)
  public PaperDto savePaper(@RequestBody PaperDto paperDto) {
    log.trace("savePaper - method entered paperDto={}", paperDto);
    Paper result =
        service.savePaper(
            paperDto.getFormat(), paperDto.getByteFileLocation());
    PaperDto resultToReturn = converter.convertModelToDto(result);
    log.trace("savePaper - method finished: result={}", resultToReturn);
    return resultToReturn;
  }

  @RequestMapping(value = "/papers", method = RequestMethod.PUT)
  public PaperDto updatePaper(@RequestBody PaperDto paperDto) {
    log.trace("updatePaper - method entered: paperDto={}", paperDto);
    PaperDto result =
        converter.convertModelToDto(
            service.updatePaper(
                paperDto.getPaperId(),
                    paperDto.getFormat(), paperDto.getByteFileLocation()));
    log.trace("updatePaper - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/papers/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deletePaper(@PathVariable Integer id) {
    log.trace("deletePaper - method entered: id={}", id);

    try {
      service.deletePaper(id);
    } catch (RestClientException ex) {
      log.trace("deletePaper - exception caught ex={}", ex.getMessage());
      log.trace("deletePaper - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deletePaper - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
