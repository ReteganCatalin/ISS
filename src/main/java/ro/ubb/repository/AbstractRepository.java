package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.Abstract;

public interface AbstractRepository extends JpaRepository<Abstract, Integer> {
}
