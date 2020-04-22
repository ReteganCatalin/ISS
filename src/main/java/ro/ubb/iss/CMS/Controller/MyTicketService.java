package ro.ubb.iss.CMS.Controller;


import ro.ubb.iss.CMS.domain.MyTicket;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;

import java.util.List;
import java.util.Optional;

public interface MyTicketService {

    Optional<MyTicket> findMyTicket(int ticketID);

    List<MyTicket> findAll();

    MyTicket updateMyTicket(int ticketID, User user, Section section, int price);

    MyTicket saveMyTicket(User user, Section section, int price);

    void deleteMyTicket(int ticketID);

}
