package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.MyTicket;

public interface MyTicketRepository extends JpaRepository<MyTicket,Integer> {
}
