package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.Presentation;
import ro.ubb.iss.CMS.domain.Section;

import java.util.List;
import java.util.Optional;

public interface PresentationService {
  Optional<Presentation> findPresentation(int presentationID);

  List<Presentation> findAll();

  Presentation updatePresentation(
      int presentationID,
      Section sectionID,
      Conference conferenceID,
      String format,
      String byteFileLocation);

  Presentation savePresentation(
      Section sectionID, Conference conferenceID, String format, String byteFileLocation);

  void deletePresentation(int presentationID);
}
