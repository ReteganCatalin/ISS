package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Recommendation;
import ro.ubb.iss.CMS.domain.Review;
import ro.ubb.iss.CMS.domain.UserInfo;
import ro.ubb.iss.CMS.dto.RecommendationDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class RecommendationConverter implements BaseConverter<Recommendation, RecommendationDto> {
    @PersistenceContext // or even @Autowired
    private EntityManager entityManager;
    @Override
    public Recommendation convertDtoToModel(RecommendationDto recommendationDto) {
        return Recommendation.builder()
                .recommendation(recommendationDto.getRecommendationID)
                .review(entityManager.getReference(Review.class, recommendationDto.getReviewID()))
                .recommendationMessage(recommendationDto.getRecommendationMessage())
                .build();
    }

    @Override
    public RecommendationDto convertModelToDto(Recommendation recommendation) {
        return Recommendation.builder()
                .recommendation(recommendation.getRecommendationID)
                .review(recommendation.getReviewID())
                .recommendationMessage(recommendation.getRecommendationMessage())
                .build();
    }
}
