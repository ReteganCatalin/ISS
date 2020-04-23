package ro.ubb.service;

import ro.ubb.domain.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Optional<Review> findReview(int reviewID);

    List<Review> findAll();

    Review updateReview(int reviewID, Proposal proposalID,Qualifier qualifierID,User userID);

    Review saveReview(Proposal proposalID, Qualifier qualifierID, User userID);

    void deleteReview(int reviewID);
}
