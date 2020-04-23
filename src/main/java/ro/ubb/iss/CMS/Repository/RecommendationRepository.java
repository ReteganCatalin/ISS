package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {}
