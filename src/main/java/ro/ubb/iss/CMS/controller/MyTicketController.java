package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.converter.MyTicketConverter;
import ro.ubb.iss.CMS.Services.MyTicketService;
import ro.ubb.iss.CMS.domain.MyTicket;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.dto.MyTicketDto;
import ro.ubb.iss.CMS.dto.MyTicketsDto;
import ro.ubb.iss.CMS.utils.EmailSender;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RestController
public class MyTicketController {

  public static final Logger log = LoggerFactory.getLogger(MyTicketController.class);

  @Autowired private MyTicketService service;

  @Autowired private MyTicketConverter converter;

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @RequestMapping(value = "/mytickets/user={id}", method = RequestMethod.GET)
  public ResponseEntity<MyTicketsDto> getAllMyTickets(@PathVariable Integer id) {
    log.trace("getAllMyTickets - method entered");
    MyTicketsDto result = new MyTicketsDto(converter.convertModelsToDtos(service.findAllByUser(id)));

    log.trace("getAllMyTickets - method finished: result={}", result);
    return new ResponseEntity<>(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/mytickets/{id}", method = RequestMethod.GET)
  public ResponseEntity<MyTicketDto> getMyTicket(@PathVariable Integer id) {
    log.trace("getMyTicket - method entered id={}", id);
    Optional<MyTicket> metaInformation = service.findMyTicket(id);
    MyTicketDto result = null;
    if (metaInformation.isPresent()) result = converter.convertModelToDto(metaInformation.get());
    log.trace("getMyTicket - method finished: result={}", result);
    return new ResponseEntity<>(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/mytickets", method = RequestMethod.POST)
  public ResponseEntity<MyTicketDto> saveMyTicket(@RequestBody MyTicketDto myTicketDto) {
    log.trace("saveMyTicket - method entered myTicketDto={}", myTicketDto);
    MyTicket result =
        service.saveMyTicket(
            entityManager.getReference(User.class, myTicketDto.getUserID()),
            entityManager.getReference(Section.class, myTicketDto.getSectionID()),
            myTicketDto.getPrice());
    EmailSender.send(EmailSender.ORIGIN_EMAIL, result.getUser().getUserInfo().emailAddress, EmailSender.PURCHASE_SUBJECT,EmailSender.TICKETS_MSG  + "\n" + myTicketDto.getSectionID().toString());

    MyTicketDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveMyTicket - method finished: result={}", resultToReturn);
    return new ResponseEntity<>(resultToReturn,HttpStatus.OK);
  }

  @RequestMapping(value = "/mytickets", method = RequestMethod.PUT)
  public ResponseEntity<MyTicketDto> updateMyTicket(@RequestBody MyTicketDto myTicketDto) {
    log.trace("updateMyTicket - method entered: myTicketDto={}", myTicketDto);
    MyTicketDto result =
        converter.convertModelToDto(
            service.updateMyTicket(
                myTicketDto.getTicketID(),
                entityManager.getReference(User.class, myTicketDto.getUserID()),
                entityManager.getReference(Section.class, myTicketDto.getSectionID()),
                myTicketDto.getPrice()));
    log.trace("updateMyTicket - method finished: result={}", result);
    return new ResponseEntity<>(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/mytickets/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteMyTicket(@PathVariable Integer id) {
    log.trace("deleteMyTicket - method entered: id={}", id);

    try {
      service.deleteMyTicket(id);
    } catch (RestClientException ex) {
      log.trace("deleteMyTicket - exception caught ex={}", ex.getMessage());
      log.trace("deleteMyTicket - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteMyTicket - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
