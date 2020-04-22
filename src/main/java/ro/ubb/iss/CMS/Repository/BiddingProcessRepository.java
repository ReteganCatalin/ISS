package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.BiddingProcess;

public interface BiddingProcessRepository extends JpaRepository<BiddingProcess,Integer> {
}
