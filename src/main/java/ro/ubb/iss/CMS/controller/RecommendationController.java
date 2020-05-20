package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.AbstractService;
import ro.ubb.iss.CMS.Services.RecommendationService;
import ro.ubb.iss.CMS.converter.AbstractConverter;
import ro.ubb.iss.CMS.converter.RecommendationConverter;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.domain.Recommendation;
import ro.ubb.iss.CMS.domain.Review;
import ro.ubb.iss.CMS.domain.UserInfo;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.AbstractsDto;
import ro.ubb.iss.CMS.dto.RecommendationDto;
import ro.ubb.iss.CMS.dto.RecommendationsDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
public class RecommendationController {

  public static final Logger log = LoggerFactory.getLogger(RecommendationController.class);

  @Autowired private RecommendationService service;

  @Autowired private RecommendationConverter converter;

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @RequestMapping(value = "/recommendations", method = RequestMethod.GET)
  public RecommendationsDto getAllRecommendations() {
    log.trace("getAllRecommendations - method entered");
    RecommendationsDto result =
        new RecommendationsDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllRecommendations - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/recommendations/{id}", method = RequestMethod.GET)
  public RecommendationDto getRecommendation(@PathVariable Integer id) {
    log.trace("getRecommendation - method entered id={}", id);
    Optional<Recommendation> anAbstract = service.findRecommendation(id);
    RecommendationDto result = null;
    if (anAbstract.isPresent()) result = converter.convertModelToDto(anAbstract.get());
    log.trace("getRecommendation - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/recommendations", method = RequestMethod.POST)
  public RecommendationDto saveRecommendation(@RequestBody RecommendationDto recommendationDto) {
    log.trace("saveRecommendation - method entered recommendationDto={}", recommendationDto);
    Recommendation result =
        service.saveRecommendation(
            entityManager.getReference(Review.class, recommendationDto.getReviewID()),
            recommendationDto.getRecommendationMessage());

    RecommendationDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveRecommendation - method finished: result={}", resultToReturn);
    return resultToReturn;
  }

  @RequestMapping(value = "/recommendations", method = RequestMethod.PUT)
  public RecommendationDto updateRecommendation(@RequestBody RecommendationDto recommendationDto) {
    log.trace("updateRecommendation - method entered: recommendationDto={}", recommendationDto);
    RecommendationDto result =
        converter.convertModelToDto(
            service.updateRecommendation(
                recommendationDto.getRecommendationID(),
                entityManager.getReference(Review.class, recommendationDto.getReviewID()),
                recommendationDto.getRecommendationMessage()));
    log.trace("updateRecommendation - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/recommendations/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteRecommendation(@PathVariable Integer id) {
    log.trace("deleteRecommendation - method entered: id={}", id);
    try {
      service.deleteRecommendation(id);
    } catch (RestClientException ex) {
      log.trace("deleteRecommendation - exception caught ex={}", ex.getMessage());
      log.trace("deleteRecommendation - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteRecommendation - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
