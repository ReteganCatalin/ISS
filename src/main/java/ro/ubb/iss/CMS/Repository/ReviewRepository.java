package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {}
