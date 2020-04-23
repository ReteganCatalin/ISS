package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.Presentation;
import ro.ubb.iss.CMS.domain.Section;

import java.util.List;
import java.util.Optional;

public class PresentationServiceImplementation implements PresentationService {
  @Override
  public Optional<Presentation> findPresentation(int presentationID) {
    return Optional.empty();
  }

  @Override
  public List<Presentation> findAll() {
    return null;
  }

  @Override
  public Presentation updatePresentation(
      int presentationID,
      Section sectionID,
      Conference conferenceID,
      String format,
      String byteFileLocation) {
    return null;
  }

  @Override
  public Presentation savePresentation(
      Section sectionID, Conference conferenceID, String format, String byteFileLocation) {
    return null;
  }

  @Override
  public void deletePresentation(int presentationID) {}
}
