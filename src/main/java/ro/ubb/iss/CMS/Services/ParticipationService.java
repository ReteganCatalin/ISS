package ro.ubb.iss.CMS.Services;


import ro.ubb.iss.CMS.domain.Participation;
import ro.ubb.iss.CMS.domain.Role;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;

import java.util.List;
import java.util.Optional;

public interface ParticipationService {

    Optional<Participation> findParticipation(int participantListID);

    List<Participation> findAll();

    Participation updateParticipation(int participantListID, Section section, User user, Role role);

    Participation saveParticipation(Section section, User user, Role role);

    void deleteParticipation(int participantListID);

}
