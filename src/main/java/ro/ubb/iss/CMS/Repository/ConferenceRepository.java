package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.Conference;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {}
