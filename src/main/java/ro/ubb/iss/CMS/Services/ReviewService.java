package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Proposal;
import ro.ubb.iss.CMS.domain.Qualifier;
import ro.ubb.iss.CMS.domain.Review;
import ro.ubb.iss.CMS.domain.User;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
  Optional<Review> findReview(int reviewID);

  List<Review> findAll();

  Review updateReview(int reviewID, Proposal proposalID, String qualifierID, User userID);

  Review saveReview(Proposal proposalID, String qualifierID, User userID);

  String getReviewStatus();
  void deleteReview(int reviewID);
}
