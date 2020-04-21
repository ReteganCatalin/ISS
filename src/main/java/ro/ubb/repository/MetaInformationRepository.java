package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.MetaInformation;

public interface MetaInformationRepository extends JpaRepository<MetaInformation,Integer> {
}
