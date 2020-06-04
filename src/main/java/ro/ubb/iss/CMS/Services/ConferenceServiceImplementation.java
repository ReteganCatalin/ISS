package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.Repository.ConferenceRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ConferenceServiceImplementation implements ConferenceService {

  private static final Logger log = LoggerFactory.getLogger(ConferenceServiceImplementation.class);

  @Autowired ConferenceRepository conferenceRepository;

  @Override
  public Optional<Conference> findConference(int conferenceID) {
    log.trace("findConference - method entered conferenceID={}", conferenceID);
    Optional<Conference> result = conferenceRepository.findById(conferenceID);
    log.trace("findConference - method exit result={}", result);
    return result;
  }

  @Override
  public List<Conference> findAll() {
    log.trace("findAll - method entered");
    List<Conference> result = conferenceRepository.findAll();
    log.trace("findAll - method exit result={}", result);
    return result;
  }

  @Override
  @Transactional
  public Conference updateConference(
      int conferenceID,
      String name,
      Date startDate,
      Date endDate,
      Date proposalDeadline,
      Date paperDeadline,Date reviewDeadline) {
    log.trace(
        "updateConference - method entered: conferenceID={}, name={}, startDate={},endDate={},proposalDeadline={},paperDeadline={}",
        conferenceID,
        name,
        startDate,
        endDate,
        proposalDeadline,
        paperDeadline,reviewDeadline);

    Optional<Conference> abstractOptional = conferenceRepository.findById(conferenceID);

    abstractOptional.ifPresent(
        newConference -> {
          newConference.setName(name);
          newConference.setStartDate(startDate);
          newConference.setEndDate(endDate);
          newConference.setProposalDeadline(proposalDeadline);
          newConference.setPaperDeadline(paperDeadline);
          log.debug("updateConference - updated: newConference={}", newConference);
        });
    log.trace("updateConference - method finished result={}", abstractOptional);
    return abstractOptional.orElse(null);
  }

  @Override
  public Conference saveConference(
      String name, Date startDate, Date endDate, Date proposalDeadline, Date paperDeadline,Date reviewDeadline,int chair) {
    log.trace(
        "saveConference - method entered: name={}, startDate={},endDate={},proposalDeadline={},paperDeadline={}",
        name,
        startDate,
        endDate,
        proposalDeadline,
        paperDeadline);
    Conference newConference =
        Conference.builder()
            .name(name)
            .startDate(startDate)
            .endDate(endDate)
            .proposalDeadline(proposalDeadline)
            .paperDeadline(paperDeadline)
                .reviewDeadline(reviewDeadline)
                .chair(chair)
            .build();

    conferenceRepository.save(newConference);

    log.trace("saveConference - method finished result={}", newConference);
    return newConference;
  }

  @Override
  public void deleteConference(int conferenceID) {
    log.trace("deleteConference - method entered: conferenceID={}", conferenceID);
    conferenceRepository.deleteById(conferenceID);
    log.trace("deleteConference - method finished");
  }
}
