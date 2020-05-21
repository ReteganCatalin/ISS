package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.iss.CMS.Repository.ConferenceProposalRepository;
import ro.ubb.iss.CMS.Repository.ConferenceProposalRepository;
import ro.ubb.iss.CMS.domain.ConferenceProposalKey;
import ro.ubb.iss.CMS.domain.ConferenceProposal;
import ro.ubb.iss.CMS.domain.ConferenceProposalKey;

import java.util.List;
import java.util.Optional;
@Service
public class ConferenceProposalServiceImplementation implements  ConferenceProposalService {
    private static final Logger log =
            LoggerFactory.getLogger(PermissionForRoleServiceImplementation.class);

    @Autowired
    ConferenceProposalRepository conferenceProposalRepository;

    @Override
    public Optional<ConferenceProposal> findConferenceProposal(ConferenceProposalKey conferenceProposalKey) {
        log.trace("findConferenceProposal - method entered conferenceProposalKey={}", conferenceProposalKey);
        Optional<ConferenceProposal> result = conferenceProposalRepository.findById(conferenceProposalKey);
        log.trace("findConferenceProposal - method exit result={}", result);
        return result;
    }

    @Override
    public List<ConferenceProposal> findAll() {
        log.trace("findAll - method entered");
        List<ConferenceProposal> result = conferenceProposalRepository.findAll();
        log.trace("findAll - method exit result={}", result);
        return result;
    }

    @Override
    public Optional<ConferenceProposal> saveConferenceProposal(ConferenceProposalKey conferenceProposalKey) {
        log.trace("saveConferenceProposal - method entered: conferenceProposalKey={}", conferenceProposalKey);
        ConferenceProposal newConferenceProposal = ConferenceProposal.builder().conferenceProposalKey(conferenceProposalKey).build();
        Optional<ConferenceProposal> checkForPresence = conferenceProposalRepository.findById(conferenceProposalKey);

        checkForPresence.ifPresentOrElse(analysis -> {}, () -> conferenceProposalRepository.save(newConferenceProposal));

        log.trace("saveConferenceProposal - method finished result={}", checkForPresence);
        return checkForPresence;
    }

    @Override
    public void deleteConferenceProposal(ConferenceProposalKey conferenceProposalKey) {
        log.trace("deleteConferenceProposal - method entered: conferenceProposalKey={}", conferenceProposalKey);
        conferenceProposalRepository.deleteById(conferenceProposalKey);
        log.trace("deleteConferenceProposal - method finished");
    }
}
