package ro.ubb.iss.CMS.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Converter.BiddingProcessConverter;
import ro.ubb.iss.CMS.Services.BiddingProcessService;
import ro.ubb.iss.CMS.domain.BiddingProcess;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.dto.BiddingProcessDto;
import ro.ubb.iss.CMS.dto.BiddingProcessesDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
public class BiddingProcessController {

  public static final Logger log = LoggerFactory.getLogger(BiddingProcessController.class);

  @Autowired private BiddingProcessService service;

  @Autowired private BiddingProcessConverter converter;

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @RequestMapping(value = "/biddings", method = RequestMethod.GET)
  public BiddingProcessesDto getAllBiddingProcesses() {
    log.trace("getAllBiddingProcesses - method entered");
    BiddingProcessesDto result =
        new BiddingProcessesDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllBiddingProcesses - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/biddings/{id}", method = RequestMethod.GET)
  public BiddingProcessDto getBiddingProcess(@PathVariable Integer id) {
    log.trace("getBiddingProcess - method entered id={}", id);
    Optional<BiddingProcess> biddingProcess = service.findBiddingProcess(id);
    BiddingProcessDto result = null;
    if (biddingProcess.isPresent()) result = converter.convertModelToDto(biddingProcess.get());
    log.trace("getBiddingProcess - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/biddings", method = RequestMethod.POST)
  public BiddingProcessDto saveBiddingProcess(@RequestBody BiddingProcessDto biddingProcessDto) {
    log.trace("saveBiddingProcess - method entered biddingProcessDto={}", biddingProcessDto);

    BiddingProcess result =
        service.saveBiddingProcess(
            entityManager.getReference(Conference.class, biddingProcessDto.getConferenceID()),
            biddingProcessDto.getDeadline());

    BiddingProcessDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveBiddingProcess - method finished: result={}", resultToReturn);
    return resultToReturn;
  }

  @RequestMapping(value = "/biddings", method = RequestMethod.PUT)
  public BiddingProcessDto updateBidding(@RequestBody BiddingProcessDto biddingProcessDto) {
    log.trace("updateBidding - method entered: biddingProcessDto={}", biddingProcessDto);
    BiddingProcessDto result =
        converter.convertModelToDto(
            service.updateBiddingProcess(
                biddingProcessDto.getBidID(),
                entityManager.getReference(Conference.class, biddingProcessDto.getConferenceID()),
                biddingProcessDto.getDeadline()));
    log.trace("updateBidding - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/biddings/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteBidding(@PathVariable Integer id) {
    log.trace("deleteBidding - method entered: id={}", id);

    try {
      service.deleteBiddingProcess(id);
    } catch (RestClientException ex) {
      log.trace("deleteBidding - exception caught ex={}", ex.getMessage());
      log.trace("deleteBidding - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteBidding - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
