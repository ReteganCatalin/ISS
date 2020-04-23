package ro.ubb.service;

import ro.ubb.domain.Conference;
import ro.ubb.domain.Section;
import ro.ubb.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.Date;

public interface SectionService {
    Optional<Section> findSection(int sectionID);

    List<Section> findAll();

    Section updateSection(int sectionID, User supervisorID, Conference conferenceID, Date dateOfPresentation);

    Section saveSection(User supervisorID, Conference conferenceID, Date dateOfPresentation);

    void deleteSection(int sectionID);
}
