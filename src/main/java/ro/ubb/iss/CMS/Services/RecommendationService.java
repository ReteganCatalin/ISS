package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Recommendation;
import ro.ubb.iss.CMS.domain.Review;

import java.util.List;
import java.util.Optional;

public interface RecommendationService {
  Optional<Recommendation> findRecommendation(int recommendationID);

  List<Recommendation> findAll();

  Recommendation updateRecommendation(
      int recommendationID, Review reviewID, String recommendationMessage);

  Recommendation saveRecommendation(Review reviewID, String recommendationMessage);

  void deleteRecommendation(int recommendationID);
}
