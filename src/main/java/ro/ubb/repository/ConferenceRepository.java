package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.Conference;

public interface ConferenceRepository extends JpaRepository<Conference,Integer> {
}
