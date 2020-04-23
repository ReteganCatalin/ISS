package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.Presentation;

public interface PresentationRepository extends JpaRepository<Presentation, Integer> {}
