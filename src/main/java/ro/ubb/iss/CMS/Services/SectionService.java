package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SectionService {
  Optional<Section> findSection(int sectionID);

  List<Section> findAll();

  Section updateSection(
      int sectionID, User supervisorID, Conference conferenceID, Date dateOfPresentation);

  Section saveSection(User supervisorID, Conference conferenceID, Date dateOfPresentation);

  void deleteSection(int sectionID);
}
