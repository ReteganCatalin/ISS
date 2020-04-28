package ro.ubb.iss.CMS.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.MyTicket;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.dto.MyTicketDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class MyTicketConverter implements BaseConverter<MyTicket, MyTicketDto> {

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @Override
  public MyTicket convertDtoToModel(MyTicketDto myTicketDto) {

    return MyTicket.builder()
        .price(myTicketDto.getPrice())
        .section(entityManager.getReference(Section.class, myTicketDto.getSectionID()))
        .ticketID(myTicketDto.getTicketID())
        .user(entityManager.getReference(User.class, myTicketDto.getUserID()))
        .build();
  }

  @Override
  public MyTicketDto convertModelToDto(MyTicket myTicket) {
    return MyTicketDto.builder()
        .sectionID(myTicket.getSection().getSectionID())
        .userID(myTicket.getUser().getUserID())
        .ticketID(myTicket.getTicketID())
        .price(myTicket.getPrice())
        .build();
  }
}
