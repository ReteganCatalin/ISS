package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.ubb.iss.CMS.domain.MyTicket;

import java.util.List;

public interface MyTicketRepository extends JpaRepository<MyTicket, Integer> {

    @Query("SElECT distinct ticket From MyTicket ticket where ticket.user.userID=:user_id")
    @EntityGraph(value = "ticketWithUser", type =
            EntityGraph.EntityGraphType.LOAD)
    List<MyTicket> findAllByUser_ID(@Param("user_id") Integer user_id);



}
