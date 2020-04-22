package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.Presentation;

public interface PresentationRepository extends JpaRepository<Presentation,Integer> {
}
