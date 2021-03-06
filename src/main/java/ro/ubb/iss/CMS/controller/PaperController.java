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
import ro.ubb.iss.CMS.converter.PaperConverter;
import ro.ubb.iss.CMS.Services.PaperService;
import ro.ubb.iss.CMS.domain.Paper;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.PaperDto;
import ro.ubb.iss.CMS.dto.PapersDto;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PaperController {

  public static final Logger log = LoggerFactory.getLogger(PaperController.class);

  @Autowired private PaperService service;

  @Autowired private PaperConverter converter;

  @Autowired
  FileService fileService;


  @RequestMapping(value = "/papers", method = RequestMethod.GET)
  public ResponseEntity<PapersDto> getAllPapers() {
    log.trace("getAllPapers - method entered");
    PapersDto result = new PapersDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllPapers - method finished: result={}", result);
    return new ResponseEntity<>(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/papers/{id}", method = RequestMethod.GET)
  public ResponseEntity<PaperDto> getPaper(@PathVariable Integer id) {
    log.trace("getPaper - method entered id={}", id);
    Optional<Paper> metaInformation = service.findPaper(id);
    PaperDto result = null;
    if (metaInformation.isPresent()) result = converter.convertModelToDto(metaInformation.get());
    log.trace("getPaper - method finished: result={}", result);
    return new ResponseEntity<>(result,HttpStatus.OK);
  }
  @RequestMapping(value = "/papers/{id}/file", method = RequestMethod.GET)
  public ResponseEntity<Resource> getPaperFile(@PathVariable Integer id) {
    log.trace("getPaperFile - method entered id={}", id);
    Optional<Paper> paper = service.findPaper(id);
    PaperDto paperResult = null;
    if (paper.isPresent()) paperResult = converter.convertModelToDto(paper.get());
    Resource file = fileService.downloadFile(paperResult.getByteFileLocation());

    log.trace("getPaperFile - method finished: result={}", file);
    return new ResponseEntity<>(file,HttpStatus.OK);
  }
  @RequestMapping(value = "/papers", method = RequestMethod.POST)
  public ResponseEntity<PaperDto> savePaper(@RequestParam("file") MultipartFile file) {
    PaperDto paperDto=new PaperDto(0,"","");
    log.trace("savePaper - method entered paperDto={}", paperDto);
    Paper result;
    String path = "";
    try {
      path=fileService.store(file);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    try {
      paperDto.setByteFileLocation(path);
      paperDto.setFormat("csp");
      result = service.savePaper(paperDto.getFormat(), path);

    } catch (UnableToCreateStorageDirectoryException | UnableToSaveFileToStorage ex) {
      log.trace("savePaper - exception occurred: ex={}", ex.getMessage());
      ex.printStackTrace();
      return null;
    }

    PaperDto resultToReturn = converter.convertModelToDto(result);
    log.trace("savePaper - method finished: result={}", resultToReturn);
    return new ResponseEntity<>(resultToReturn,HttpStatus.OK);
  }

  @RequestMapping(value = "/papers/{paperID}", method = RequestMethod.PUT)
  public ResponseEntity<PaperDto> updatePaper(@RequestParam("file") MultipartFile file, @PathVariable Integer paperID) {
    /*log.trace("updatePaper - method entered: paperDto={}", paperDto);
    PaperDto result =
        converter.convertModelToDto(
            service.updatePaper(
                paperDto.getPaperId(), paperDto.getFormat(), paperDto.getByteFileLocation()));
    log.trace("updatePaper - method finished: result={}", result);
    return new ResponseEntity<>(result,HttpStatus.OK);*/

    PaperDto paperDto = new PaperDto(paperID, "", "");
    log.trace("updatePaper - method entered paperDto={}", paperDto);

    Paper result;
    String path = "";
    try {
      path=fileService.store(file);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    try {
      paperDto.setByteFileLocation(path);
      paperDto.setFormat("csp");
      result = service.updatePaper(paperDto.getPaperId(), paperDto.getFormat(), path);

    } catch (UnableToCreateStorageDirectoryException | UnableToSaveFileToStorage ex) {
      log.trace("updatePaper - exception occurred: ex={}", ex.getMessage());
      ex.printStackTrace();
      return null;
    }

    PaperDto resultToReturn = converter.convertModelToDto(result);
    log.trace("updatePaper - method finished: result={}", resultToReturn);
    return new ResponseEntity<>(resultToReturn,HttpStatus.OK);
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
