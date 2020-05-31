package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.MyTicket;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;

import java.util.List;
import java.util.Optional;

public interface MyTicketService {

  Optional<MyTicket> findMyTicket(int ticketID);

  List<MyTicket> findAllByUser(int userID);

  Optional<MyTicket> findMyTicketWithUserAndUserInfo(int ticketID);


  MyTicket updateMyTicket(int ticketID, User user, Section section, int price);

  MyTicket saveMyTicket(User user, Section section, int price);

  void deleteMyTicket(int ticketID);
}
