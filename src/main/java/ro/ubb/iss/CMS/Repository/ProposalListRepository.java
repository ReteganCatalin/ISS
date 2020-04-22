package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.ProposalList;
import ro.ubb.iss.CMS.domain.ProposalListKey;

public interface ProposalListRepository  extends JpaRepository<ProposalList, ProposalListKey> {
}
