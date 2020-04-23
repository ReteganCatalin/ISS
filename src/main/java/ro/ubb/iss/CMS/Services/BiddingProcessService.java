package ro.ubb.iss.CMS.Services;


import ro.ubb.iss.CMS.domain.BiddingProcess;
import ro.ubb.iss.CMS.domain.Conference;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BiddingProcessService {
    Optional<BiddingProcess> findBiddingProcess(int bidID);

    List<BiddingProcess> findAll();

    BiddingProcess updateBiddingProcess(int bidID, Conference conferenceID, Date deadline);

    BiddingProcess saveBiddingProcess(Conference conferenceID, Date deadline);

    void deleteBiddingProcess(int bidID);

}
