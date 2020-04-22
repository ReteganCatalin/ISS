package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.Participation;

public interface ParticipationRepository extends JpaRepository<Participation,Integer> {
}
