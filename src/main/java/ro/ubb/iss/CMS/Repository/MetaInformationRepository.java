package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.MetaInformation;

public interface MetaInformationRepository extends JpaRepository<MetaInformation,Integer> {
}
