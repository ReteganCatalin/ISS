package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.Participation;

public interface ParticipationRepository extends JpaRepository<Participation,Integer> {
}
