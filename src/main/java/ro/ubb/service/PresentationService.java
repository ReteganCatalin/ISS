package ro.ubb.service;

import ro.ubb.domain.Conference;
import ro.ubb.domain.Presentation;
import ro.ubb.domain.Section;
import ro.ubb.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PresentationService {
    Optional<Presentation> findPresentation(int presentationID);

    List<Presentation> findAll();

    Presentation updatePresentation(int presentationID, Section sectionID, Conference conferenceID, String format,String byteFileLocation);

    Presentation savePresentation(Section sectionID, Conference conferenceID, String format,String byteFileLocation);

    void deletePresentation(int presentationID);
}
