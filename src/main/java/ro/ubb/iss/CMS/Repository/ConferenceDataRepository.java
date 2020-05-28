package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.ConferenceData;
import ro.ubb.iss.CMS.domain.ConferenceProposal;
import ro.ubb.iss.CMS.domain.ConferenceProposalKey;

public interface ConferenceDataRepository
        extends JpaRepository<ConferenceData, Integer> {

}
