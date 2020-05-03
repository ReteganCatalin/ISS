package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.iss.CMS.Repository.RecommendationRepository;
import ro.ubb.iss.CMS.domain.*;

import java.util.List;
import java.util.Optional;

public class RecommendationServiceImplementation implements RecommendationService {
  private static final Logger log = LoggerFactory.getLogger(RecommendationServiceImplementation.class);

  @Autowired
  private RecommendationRepository recommendationRepository;
  @Override
  public Optional<Recommendation> findRecommendation(int recommendationID) {
    log.trace("findRecommendation - method entered recommendationID={}", recommendationID);
    Optional<Recommendation> result = recommendationRepository.findById(recommendationID);
    log.trace("findRecommendation - method exit result={}", result);
    return result;
  }

  @Override
  public List<Recommendation> findAll() {
    log.trace("findAll - method entered");
    List<Recommendation> result = recommendationRepository.findAll();
    log.trace("findAll - method exit result={}", result);
    return result;
  }

  @Override
  public Recommendation updateRecommendation(
      int recommendationID, Review reviewID, String recommendationMessage) {
    log.trace(
            "updateRecommendation - method entered: recommendationID={}, reviewID={}, recommendationMessage={}",
            recommendationID,
            reviewID,
            recommendationMessage);

    Optional<Recommendation> abstractOptional = recommendationRepository.findById(recommendationID);

    abstractOptional.ifPresent(
            newRecommendation -> {
              newRecommendation.setReviewID(reviewID);
              newRecommendation.setRecommendationMessage(recommendationMessage);
              log.debug("updateRecommendation - updated: newRecommendation={}", newRecommendation);
            });
    log.trace("updateRecommendation - method finished result={}", abstractOptional);
    return abstractOptional.orElse(null);
  }

  @Override
  public Recommendation saveRecommendation(Review reviewID, String recommendationMessage) {
    log.trace(
            "saveRecommendation - method entered: reviewID={}, recommendationMessage={}",
            reviewID,
            recommendationMessage);
    Recommendation newRecommendation =
            Recommendation.builder()
                    .reviewID(reviewID)
                    .recommendationMessage(recommendationMessage)
                    .build();

    recommendationRepository.save(newRecommendation);

    log.trace("saveRecommendation - method finished result={}", newRecommendation);
    return newRecommendation;
  }

  @Override
  public void deleteRecommendation(int recommendationID) {
    log.trace("deleteRecommendation - method entered: recommendationID={}", recommendationID);
    recommendationRepository.deleteById(recommendationID);
    log.trace("deleteRecommendation - method finished");
  }
}
