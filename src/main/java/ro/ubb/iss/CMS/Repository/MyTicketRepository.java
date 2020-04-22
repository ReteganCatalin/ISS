package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.MyTicket;

public interface MyTicketRepository extends JpaRepository<MyTicket,Integer> {
}
