package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Conference;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ConferenceService {

  Optional<Conference> findConference(int conferenceID);

  List<Conference> findAll();

  Conference updateConference(
      int conferenceID,
      String name,
      Date startDate,
      Date endDate,
      Date proposalDeadline,
      Date paperDeadline);

  Conference saveConference(
      String name, Date startDate, Date endDate, Date proposalDeadline, Date paperDeadline);

  void deleteConference(int conferenceID);
}
