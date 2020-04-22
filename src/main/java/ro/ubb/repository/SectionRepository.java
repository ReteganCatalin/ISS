package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.Section;

public interface SectionRepository extends JpaRepository<Section,Integer> {
}
