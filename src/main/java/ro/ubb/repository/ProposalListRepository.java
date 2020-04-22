package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.ProposalList;

public interface ProposalListRepository  extends JpaRepository<ProposalList,Integer> {
}
