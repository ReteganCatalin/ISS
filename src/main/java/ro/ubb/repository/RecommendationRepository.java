package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.Recommendation;

public interface RecommendationRepository  extends JpaRepository<Recommendation,Integer> {
}
