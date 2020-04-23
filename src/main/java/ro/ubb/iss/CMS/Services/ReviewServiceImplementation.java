package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Proposal;
import ro.ubb.iss.CMS.domain.Qualifier;
import ro.ubb.iss.CMS.domain.Review;
import ro.ubb.iss.CMS.domain.User;

import java.util.List;
import java.util.Optional;

public class ReviewServiceImplementation implements ReviewService {
  @Override
  public Optional<Review> findReview(int reviewID) {
    return Optional.empty();
  }

  @Override
  public List<Review> findAll() {
    return null;
  }

  @Override
  public Review updateReview(
      int reviewID, Proposal proposalID, Qualifier qualifierID, User userID) {
    return null;
  }

  @Override
  public Review saveReview(Proposal proposalID, Qualifier qualifierID, User userID) {
    return null;
  }

  @Override
  public void deleteReview(int reviewID) {}
}
