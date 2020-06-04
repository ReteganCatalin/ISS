package ro.ubb.iss.CMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import ro.ubb.iss.CMS.Repository.ConferenceDataRepository;
import ro.ubb.iss.CMS.Repository.ConferenceRepository;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.ConferenceData;
import ro.ubb.iss.CMS.dto.MyTicketDto;

import javax.persistence.EntityManager;

@SpringBootApplication
public class CmsApplication {

  public static void main(String[] args) {
    SpringApplication.run(CmsApplication.class, args);
  }

//    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
//    ConferenceData conferenceData = ConferenceData.builder().conferenceID(1).format("asd").callForPaper("asd").byteFileLocation("asd").about("asd").build();
//
//    ConferenceDataRepository conferenceRepository = annotationConfigApplicationContext.getBean(ConferenceDataRepository.class);
//
//    conferenceRepository.save(conferenceData);


      }
    };
  }
}
