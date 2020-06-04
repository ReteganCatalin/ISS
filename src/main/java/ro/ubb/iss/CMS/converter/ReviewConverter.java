package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.ReviewDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ReviewConverter implements BaseConverter<Review, ReviewDto> {
  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @Override
  public Review convertDtoToModel(ReviewDto reviewDto) {
    return Review.builder()
        .reviewID(reviewDto.getReviewID())
        .proposal(entityManager.getReference(Proposal.class, reviewDto.getProposalID()))
        .qualifier(Qualifier.values()[reviewDto.getQualifierID()].getQualifier_value())
        .user(entityManager.getReference(User.class, reviewDto.getUserID()))
        .build();
  }

  @Override
  public ReviewDto convertModelToDto(Review review) {
    return ReviewDto.builder()
        .reviewID(review.getReviewID())
        .proposalID(review.getProposal().getProposalID())
        .qualifierID(
                getQualifierOrdinalByValue(review.getQualifier())
        )
        .userID(review.getUser().getUserID())
        .build();
  }

  private Integer getQualifierOrdinalByValue(String qualifier) {
    return switch (qualifier) {
      case "none" -> 0;
      case "strong reject" -> 1;
      case "reject" -> 2;
      case "weak reject" -> 3;
      case "borderline paper" -> 4;
      case "weak accept" -> 5;
      case "accept" -> 6;
      case "strong accept" -> 7;
      default -> throw new IllegalArgumentException("Invalid qualifier value");
    };
  }
}
