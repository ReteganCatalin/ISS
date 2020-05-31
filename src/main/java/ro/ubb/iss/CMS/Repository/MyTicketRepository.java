package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.ubb.iss.CMS.domain.MyTicket;

import java.util.List;

public interface MyTicketRepository extends JpaRepository<MyTicket, Integer> {

    @Query("SElECT distinct ticket From ticketing ticket where ticket.user_id=:user_id")
    @EntityGraph(value = "ticketWithUser", type =
            EntityGraph.EntityGraphType.LOAD)
    List<MyTicket> findAllByUser_ID(@Param("user_id") Integer user_id);



}
