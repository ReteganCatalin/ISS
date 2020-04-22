package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.Proposal;

public interface ProposalRepository  extends JpaRepository<Proposal,Integer> {
}
