package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.iss.CMS.Repository.MyTicketRepository;
import ro.ubb.iss.CMS.domain.MyTicket;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;

import java.util.List;
import java.util.Optional;

@Service
public class MyTicketServiceImplementation implements MyTicketService {

  private static final Logger log = LoggerFactory.getLogger(MyTicketServiceImplementation.class);

  @Autowired MyTicketRepository myTicketRepository;

  @Override
  public Optional<MyTicket> findMyTicket(int ticketID) {
    log.trace("findMyTicket - method entered ticketID={}", ticketID);
    Optional<MyTicket> result = myTicketRepository.findById(ticketID);
    log.trace("findMyTicket - method exit result={}", result);
    return result;
  }

  @Override
  public List<MyTicket> findAllByUser(int userID) {
    log.trace("findAll - method entered");
    List<MyTicket> result = myTicketRepository.findAllByUser_ID(userID);
    log.trace("findAll - method exit result={}", result);
    return result;
  }

  @Override
  @Transactional
  public MyTicket updateMyTicket(int ticketID, User user, Section section, int price) {
    log.trace(
        "updateMyTicket - method entered: ticketID={}, user={}, section={},price={}",
        ticketID,
        user,
        section,
        price);

    Optional<MyTicket> myTicketOptional = myTicketRepository.findById(ticketID);

    myTicketOptional.ifPresent(
        newMyTicket -> {
          newMyTicket.setUser(user);
          newMyTicket.setSection(section);
          newMyTicket.setPrice(price);
          log.debug("updateMyTicket - updated: newMyTicket={}", newMyTicket);
        });
    log.trace("updateMyTicket - method finished result={}", myTicketOptional);
    return myTicketOptional.orElse(null);
  }

  @Override
  public MyTicket saveMyTicket(User user, Section section, int price) {
    log.trace("saveMyTicket - method entered: user={}, section={},topics={}", user, section, price);
    MyTicket newMyTicket = MyTicket.builder().user(user).section(section).price(price).build();

    myTicketRepository.save(newMyTicket);

    log.trace("saveMyTicket - method finished result={}", newMyTicket);
    return newMyTicket;
  }

  @Override
  public void deleteMyTicket(int ticketID) {
    log.trace("deleteMyTicket - method entered: ticketID={}", ticketID);
    myTicketRepository.deleteById(ticketID);
    log.trace("deleteMyTicket - method finished");
  }
}
