package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.Review;

public interface ReviewRepository  extends JpaRepository<Review,Integer> {
}
