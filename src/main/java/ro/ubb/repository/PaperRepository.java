package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.Paper;

public interface PaperRepository extends JpaRepository<Paper,Integer> {
}
