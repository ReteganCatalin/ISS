package ro.ubb.iss.CMS.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.iss.CMS.Repository.ParticipationRepository;
import ro.ubb.iss.CMS.domain.*;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipationServiceImplementation implements ParticipationService{

    private static final Logger log = LoggerFactory.getLogger(ParticipationServiceImplementation.class);

    @Autowired
    ParticipationRepository participationRepository;


    @Override
    public Optional<Participation> findParticipation(int participantListID) {
        log.trace("findParticipation - method entered participantListID={}",participantListID);
        Optional<Participation> result = participationRepository.findById(participantListID);
        log.trace("findParticipation - method exit result={}",result);
        return result;
    }

    @Override
    public List<Participation> findAll() {
        log.trace("findAll - method entered");
        List<Participation> result = participationRepository.findAll();
        log.trace("findAll - method exit result={}",result);
        return result;
    }

    @Override
    @Transactional
    public Participation updateParticipation(int participantListID, Section section, User user, Role role) {
        log.trace("updateParticipation - method entered: participantListID={}, section={}, user={}, role={}",participantListID,section,user,role);

        Optional<Participation> participationOptional = participationRepository.findById(participantListID);

        participationOptional.ifPresent(
                newParticipation -> {
                    newParticipation.setRole(role);
                    newParticipation.setSection(section);
                    newParticipation.setUser(user);
                    log.debug("updateParticipation - updated: newParticipation={}", newParticipation);
                });
        log.trace("updateParticipation - method finished result={}",participationOptional);
        return participationOptional.orElse(null);
    }

    @Override
    public Participation saveParticipation(Section section, User user, Role role) {
        log.trace("saveParticipation - method entered: section={}, user={}, role={}",section,user,role);
        Participation newParticipation = Participation.builder().section(section).user(user).role(role).build();

        participationRepository.save(newParticipation);

        log.trace("saveParticipation - method finished result={}",newParticipation);
        return newParticipation;
    }

    @Override
    public void deleteParticipation(int participantListID) {
        log.trace("deleteParticipation - method entered: participantListID={}", participantListID);
        participationRepository.deleteById(participantListID);
        log.trace("deleteParticipation - method finished");

    }
}
