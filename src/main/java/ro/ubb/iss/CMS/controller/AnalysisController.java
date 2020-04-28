package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.converter.AnalysisConverter;
import ro.ubb.iss.CMS.Services.AnalysisService;
import ro.ubb.iss.CMS.domain.Analysis;
import ro.ubb.iss.CMS.domain.AnalysisKey;
import ro.ubb.iss.CMS.dto.AnalysesDto;
import ro.ubb.iss.CMS.dto.AnalysisDto;

import java.util.Optional;

@RestController
public class AnalysisController {

  public static final Logger log = LoggerFactory.getLogger(AnalysisController.class);

  @Autowired private AnalysisService service;

  @Autowired private AnalysisConverter converter;

  @RequestMapping(value = "/analyses", method = RequestMethod.GET)
  public AnalysesDto getAllAnalyses() {
    log.trace("getAllAnalyses - method entered");
    AnalysesDto result = new AnalysesDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllAnalyses - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/analyses/{analysisKey}", method = RequestMethod.GET)
  public AnalysisDto getAnalysis(@PathVariable AnalysisKey analysisKey) {
    log.trace("getAnalysis - method entered analysisKey={}", analysisKey);
    Optional<Analysis> analysis = service.findAnalysis(analysisKey);
    AnalysisDto result = null;
    if (analysis.isPresent()) result = converter.convertModelToDto(analysis.get());
    log.trace("getAnalysis - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/analyses", method = RequestMethod.POST)
  public AnalysisDto saveAnalysis(@RequestBody AnalysisDto analysisDto) {
    log.trace("saveAnalysis - method entered analysisDto={}", analysisDto);
    Optional<Analysis> result =
        service.saveAnalysis(
            new AnalysisKey(
                analysisDto.getBidID(), analysisDto.getUserID(), analysisDto.getProposalID()),
            analysisDto.getBriefAnalysis(),
            analysisDto.getRefuse());
    AnalysisDto resultToReturn = null;
    if (result.isPresent()) resultToReturn = converter.convertModelToDto(result.get());
    log.trace("saveAnalysis - method finished: result={}", resultToReturn);
    return resultToReturn;
  }

  @RequestMapping(value = "/analyses", method = RequestMethod.PUT)
  public AnalysisDto updateAnalysis(@RequestBody AnalysisDto analysisDto) {
    log.trace("updateAnalysis - method entered: analysisDto={}", analysisDto);
    Analysis analysis = converter.convertDtoToModel(analysisDto);
    AnalysisDto result =
        converter.convertModelToDto(
            service.updateAnalysis(
                analysis.getAnalysisKey(), analysis.getBriefAnalysis(), analysis.getRefuse()));
    log.trace("updateAnalysis - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/analyses/{analysisKey}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteAnalysis(@PathVariable AnalysisKey analysisKey) {
    log.trace("deleteAnalysis - method entered: analysisKey={}", analysisKey);

    try {
      service.deleteAnalysis(analysisKey);
    } catch (RestClientException ex) {
      log.trace("deleteAnalysis - exception caught ex={}", ex.getMessage());
      log.trace("deleteAnalysis - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteAnalysis - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
