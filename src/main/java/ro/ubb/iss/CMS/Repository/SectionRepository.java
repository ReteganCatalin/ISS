package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.Section;

public interface SectionRepository extends JpaRepository<Section,Integer> {
}
