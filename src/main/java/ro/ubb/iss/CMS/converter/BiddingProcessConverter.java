package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.BiddingProcess;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.dto.BiddingProcessDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class BiddingProcessConverter implements BaseConverter<BiddingProcess, BiddingProcessDto> {

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @Override
  public BiddingProcess convertDtoToModel(BiddingProcessDto biddingProcessDto) {
    return BiddingProcess.builder()
        .bidID(biddingProcessDto.getBidID())
        .deadline(biddingProcessDto.getDeadline())
        .conference(
            entityManager.getReference(Conference.class, biddingProcessDto.getConferenceID()))
        .build();
  }

  @Override
  public BiddingProcessDto convertModelToDto(BiddingProcess biddingProcess) {
    return BiddingProcessDto.builder()
        .conferenceID(biddingProcess.getConference().getConferenceID())
        .bidID(biddingProcess.getBidID())
        .deadline(biddingProcess.getDeadline())
        .build();
  }
}
