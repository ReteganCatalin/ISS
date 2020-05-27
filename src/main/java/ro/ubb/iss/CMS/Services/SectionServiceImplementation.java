package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.iss.CMS.Repository.SectionRepository;
import ro.ubb.iss.CMS.Repository.UserInfoRepository;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.domain.UserInfo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImplementation implements SectionService {
  private static final Logger log = LoggerFactory.getLogger(SectionServiceImplementation.class);

  @Autowired private SectionRepository sectionRepository;

  @Override
  public Optional<Section> findSection(int sectionID) {
    log.trace("findSection - method entered sectionID={}", sectionID);
    Optional<Section> result = sectionRepository.findById(sectionID);
    log.trace("findSection - method exit result={}", result);
    return result;
  }

  @Override
  public List<Section> findAll() {
    log.trace("findAll - method entered");
    List<Section> result = sectionRepository.findAll();
    log.trace("findAll - method exit result={}", result);
    return result;
  }

  @Override
  @Transactional
  public Section updateSection(
      int sectionID, User supervisorID, Conference conferenceID, Date dateOfPresentation) {
    log.trace(
        "updateSection - method entered: sectionID={}, supervisorID={}, conferenceID={}, dateOfPresentation={}",
        sectionID,
        supervisorID,
        conferenceID,
        dateOfPresentation);

    Optional<Section> abstractOptional = sectionRepository.findById(sectionID);

    abstractOptional.ifPresent(
        newSection -> {
          newSection.setSupervisor(supervisorID);
          newSection.setConference(conferenceID);
          newSection.setDateOfPresentation(dateOfPresentation);
          log.debug("updateSection - updated: newSection={}", newSection);
        });
    log.trace("updateSection - method finished result={}", abstractOptional);
    return abstractOptional.orElse(null);
  }

  @Override
  public Section saveSection(User supervisorID, Conference conferenceID, Date dateOfPresentation) {
    log.trace(
        "saveSection - method entered: supervisorID={}, conferenceID={}, dateOfPresentation={}",
        supervisorID,
        conferenceID,
        dateOfPresentation);
    Section newSection =
        Section.builder()
            .supervisor(supervisorID)
            .conference(conferenceID)
            .dateOfPresentation(dateOfPresentation)
            .build();

    sectionRepository.save(newSection);

    log.trace("saveSection - method finished result={}", newSection);
    return newSection;
  }

  @Override
  public void deleteSection(int sectionID) {
    log.trace("deleteSection - method entered: sectionID={}", sectionID);
    sectionRepository.deleteById(sectionID);
    log.trace("deleteSection - method finished");
  }
}
