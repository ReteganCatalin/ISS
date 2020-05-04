package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.ReviewDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ReviewConverter implements BaseConverter<Review, ReviewDto>{
    @PersistenceContext // or even @Autowired
    private EntityManager entityManager;
    @Override
    public Review convertDtoToModel(ReviewDto reviewDto) {
        return Review.builder()
                .reviewID(reviewDto.getReviewID())
                .proposal(entityManager.getReference(Proposal.class, reviewDto.getProposalID()))
                .qualifier(entityManager.getReference(Qualifier.class, reviewDto.getQualifierID()))
                .user(entityManager.getReference(User.class, reviewDto.getUserID()))
                .build();
    }

    @Override
    public ReviewDto convertModelToDto(Review review) {
        return Review.builder()
                .reviewID(review.getReviewID())
                .proposal(review.getProposalID())
                .qualifier(review.getQualifierID())
                .user(review.getUserID())
                .build();
    }
}
