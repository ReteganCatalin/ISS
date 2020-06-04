package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.MyExceptions.AllAnalysesRefusedByUser;
import ro.ubb.iss.CMS.MyExceptions.AlreadyInTheReviewersException;
import ro.ubb.iss.CMS.MyExceptions.TooManyReviewersException;
import ro.ubb.iss.CMS.Services.AbstractService;
import ro.ubb.iss.CMS.Services.ReviewService;
import ro.ubb.iss.CMS.converter.AbstractConverter;
import ro.ubb.iss.CMS.converter.ReviewConverter;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.AbstractsDto;
import ro.ubb.iss.CMS.dto.ReviewDto;
import ro.ubb.iss.CMS.dto.ReviewsDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ReviewController {

  public static final Logger log = LoggerFactory.getLogger(ReviewController.class);

  @Autowired private ReviewService service;

  @Autowired private ReviewConverter converter;

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @RequestMapping(value = "/reviews", method = RequestMethod.GET)
  public ResponseEntity<ReviewsDto> getAllReviews() {
    log.trace("getAllReviews - method entered");
    ReviewsDto result = new ReviewsDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllReviews - method finished: result={}", result);
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/reviews/{id}", method = RequestMethod.GET)
  public ResponseEntity<ReviewDto> getReview(@PathVariable Integer id) {
    log.trace("getReview - method entered id={}", id);
    Optional<Review> anAbstract = service.findReview(id);
    ReviewDto result = null;
    if (anAbstract.isPresent()) result = converter.convertModelToDto(anAbstract.get());
    log.trace("getReview - method finished: result={}", result);
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/reviews", method = RequestMethod.POST)
  public ResponseEntity<ReviewDto> saveReview(@RequestBody ReviewDto reviewDto) {
    log.trace("saveReview - method entered reviewDto={}", reviewDto);
    Review result;
    try {
      result =
          service.saveReview(
              entityManager.getReference(Proposal.class, reviewDto.getProposalID()),
                  Qualifier.values()[reviewDto.getQualifierID()].getQualifier_value(),
              entityManager.getReference(User.class, reviewDto.getUserID()));
    } catch (TooManyReviewersException
        | AllAnalysesRefusedByUser
        | AlreadyInTheReviewersException ex) {
      log.trace("saveReview - exception occurred: ex={}", ex.getMessage());
      ex.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    ReviewDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveReview - method finished: result={}", resultToReturn);
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/reviews", method = RequestMethod.PUT)
  public ResponseEntity<ReviewDto> updateReview(@RequestBody ReviewDto reviewDto) {
    log.trace("updateReview - method entered: reviewDto={}", reviewDto);
    ReviewDto result =
        converter.convertModelToDto(
            service.updateReview(
                reviewDto.getReviewID(),
                entityManager.getReference(Proposal.class, reviewDto.getProposalID()),
                Qualifier.values()[reviewDto.getQualifierID()].getQualifier_value(),
                entityManager.getReference(User.class, reviewDto.getUserID())));



    log.trace("updateReview - method finished: result={}", result);
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/reviews/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteReview(@PathVariable Integer id) {
    log.trace("deleteReview - method entered: id={}", id);
    try {
      service.deleteReview(id);
    } catch (RestClientException ex) {
      log.trace("deleteReview - exception caught ex={}", ex.getMessage());
      log.trace("deleteReview - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteReview - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
