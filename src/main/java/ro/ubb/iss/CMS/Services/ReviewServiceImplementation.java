package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.iss.CMS.Repository.ReviewRepository;
import ro.ubb.iss.CMS.Repository.SectionRepository;
import ro.ubb.iss.CMS.domain.*;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImplementation implements ReviewService {
  private static final Logger log = LoggerFactory.getLogger(ReviewServiceImplementation.class);

  @Autowired private ReviewRepository reviewRepository;

  @Override
  public Optional<Review> findReview(int reviewID) {
    log.trace("findReview - method entered reviewID={}", reviewID);
    Optional<Review> result = reviewRepository.findById(reviewID);
    log.trace("findReview - method exit result={}", result);
    return result;
  }

  @Override
  public List<Review> findAll() {
    log.trace("findAll - method entered");
    List<Review> result = reviewRepository.findAll();
    log.trace("findAll - method exit result={}", result);
    return result;
  }

  @Override
  @Transactional
  public Review updateReview(
      int reviewID, Proposal proposalID, Qualifier qualifierID, User userID) {
    log.trace(
        "updateReview - method entered: reviewID={}, proposalID={}, qualifierID={}, userID={}",
        reviewID,
        proposalID,
        qualifierID,
        userID);

    Optional<Review> abstractOptional = reviewRepository.findById(reviewID);

    abstractOptional.ifPresent(
        newReview -> {
          newReview.setProposal(proposalID);
          newReview.setQualifier(qualifierID);
          newReview.setUser(userID);
          log.debug("updateReview - updated: newReview={}", newReview);
        });
    log.trace("updateReview - method finished result={}", abstractOptional);
    return abstractOptional.orElse(null);
  }

  @Override
  public Review saveReview(Proposal proposalID, Qualifier qualifierID, User userID) {
    log.trace(
        "saveReview - method entered: proposalID={}, qualifierID={}, userID={}",
        proposalID,
        qualifierID,
        userID);
    Review newReview =
        Review.builder().proposal(proposalID).qualifier(qualifierID).user(userID).build();

    reviewRepository.save(newReview);

    log.trace("saveReview - method finished result={}", newReview);
    return newReview;
  }

  @Override
  public void deleteReview(int reviewID) {
    log.trace("deleteReview - method entered: reviewID={}", reviewID);
    reviewRepository.deleteById(reviewID);
    log.trace("deleteReview - method finished");
  }
}
