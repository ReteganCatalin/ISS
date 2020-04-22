package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.ProposalListKey;

public interface ProposalListKeyRepository  extends JpaRepository<ProposalListKey,Integer> {
}
