package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Recommendation;
import ro.ubb.iss.CMS.domain.Review;

import java.util.List;
import java.util.Optional;

public class RecommendationServiceImplementation implements RecommendationService {
  @Override
  public Optional<Recommendation> findRecommendation(int recommendationID) {
    return Optional.empty();
  }

  @Override
  public List<Recommendation> findAll() {
    return null;
  }

  @Override
  public Recommendation updateRecommendation(
      int recommendationID, Review reviewID, String recommendationMessage) {
    return null;
  }

  @Override
  public Recommendation saveRecommendation(Review reviewID, String recommendationMessage) {
    return null;
  }

  @Override
  public void deleteRecommendation(int recommendationID) {}
}
