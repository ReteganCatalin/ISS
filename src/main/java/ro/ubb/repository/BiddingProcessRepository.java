package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.BiddingProcess;

public interface BiddingProcessRepository extends JpaRepository<BiddingProcess,Integer> {
}
