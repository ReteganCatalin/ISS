package ro.ubb.service;

import ro.ubb.domain.Recommendation;
import ro.ubb.domain.Review;

import java.util.List;
import java.util.Optional;

public interface RecommendationService {
    Optional<Recommendation> findRecommendation(int recommendationID);

    List<Recommendation> findAll();

    Recommendation updateRecommendation(int recommendationID, Review reviewID, String recommendationMessage);

    Recommendation saveRecommendation(Review reviewID, String recommendationMessage);

    void deleteRecommendation(int recommendationID);
}
