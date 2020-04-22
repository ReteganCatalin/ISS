package ro.ubb.iss.CMS.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.iss.CMS.domain.BiddingProcess;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.Repository.BiddingProcessRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BiddingProcessServiceImplementation implements BiddingProcessService {
    private static final Logger log = LoggerFactory.getLogger(BiddingProcessServiceImplementation.class);

    @Autowired
    BiddingProcessRepository biddingProcessRepository;

    @Override
    public Optional<BiddingProcess> findBiddingProcess(int bidID) {
        log.trace("findBiddingProcess - method entered");
        Optional<BiddingProcess> result = biddingProcessRepository.findById(bidID);
        log.trace("findBiddingProcess - method exit result={}",result);
        return result;
    }

    @Override
    public List<BiddingProcess> findAll() {
        log.trace("findAll - method entered");
        List<BiddingProcess> result = biddingProcessRepository.findAll();
        log.trace("findAll - method exit result={}",result);
        return result;
    }

    @Override
    public BiddingProcess updateBiddingProcess(int bidID, Conference conferenceID, Date deadline) {
        log.trace("updateBiddingProcess - method entered: bidID={}, conferenceID={}, deadline={}", bidID,conferenceID,deadline);

        Optional<BiddingProcess> abstractOptional = biddingProcessRepository.findById(bidID);

        abstractOptional.ifPresent(
                newBid -> {
                    newBid.setConference(conferenceID);
                    newBid.setDeadline(deadline);
                    log.debug("updateBiddingProcess - updated: newBid={}", newBid);
                });
        log.trace("updateBiddingProcess - method finished result={}",abstractOptional);
        return abstractOptional.orElse(null);
    }

    @Override
    public BiddingProcess saveBiddingProcess(Conference conferenceID, Date deadline) {
        log.trace("saveBiddingProcess - method entered: conferenceID={}, deadline={}", conferenceID,deadline);
        BiddingProcess newBiddingProcess = BiddingProcess.builder().conference(conferenceID).deadline(deadline).build();

        biddingProcessRepository.save(newBiddingProcess);

        log.trace("saveBiddingProcess - method finished result={}",newBiddingProcess);
        return newBiddingProcess;
    }

    @Override
    public void deleteBiddingProcess(int bidID) {
        log.trace("deleteBiddingProcess - method entered: bidID={}", bidID);
        biddingProcessRepository.deleteById(bidID);
        log.trace("deleteBiddingProcess - method finished");

    }
}
