package ro.ubb.iss.CMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import ro.ubb.iss.CMS.dto.MyTicketDto;

@SpringBootApplication
public class CmsApplication {

  public static void main(String[] args) {
    SpringApplication.run(CmsApplication.class, args);
    RestTemplate restTemplate = new RestTemplate();

    restTemplate.postForObject("http://localhost:8081/mytickets",MyTicketDto.builder().sectionID(1).userID(1).price(20).ticketID(null).build(),MyTicketDto.class);


  }
}
