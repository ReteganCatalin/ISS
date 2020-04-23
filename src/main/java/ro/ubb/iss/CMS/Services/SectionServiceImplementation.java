package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SectionServiceImplementation implements SectionService {
  @Override
  public Optional<Section> findSection(int sectionID) {
    return Optional.empty();
  }

  @Override
  public List<Section> findAll() {
    return null;
  }

  @Override
  public Section updateSection(
      int sectionID, User supervisorID, Conference conferenceID, Date dateOfPresentation) {
    return null;
  }

  @Override
  public Section saveSection(User supervisorID, Conference conferenceID, Date dateOfPresentation) {
    return null;
  }

  @Override
  public void deleteSection(int sectionID) {}
}
