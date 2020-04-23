package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.iss.CMS.Repository.MetaInformationRepository;
import ro.ubb.iss.CMS.domain.MetaInformation;

import java.util.List;
import java.util.Optional;

@Service
public class MetaInformationServiceImplementation implements MetaInformationService {

  private static final Logger log =
      LoggerFactory.getLogger(MetaInformationServiceImplementation.class);

  @Autowired MetaInformationRepository metaInformationRepository;

  @Override
  public Optional<MetaInformation> findMetaInformation(int metaInfoID) {
    log.trace("findMetaInformation - method entered metaInfoID={}", metaInfoID);
    Optional<MetaInformation> result = metaInformationRepository.findById(metaInfoID);
    log.trace("findMetaInformation - method exit result={}", result);
    return result;
  }

  @Override
  public List<MetaInformation> findAll() {
    log.trace("findAll - method entered");
    List<MetaInformation> result = metaInformationRepository.findAll();
    log.trace("findAll - method exit result={}", result);
    return result;
  }

  @Override
  @Transactional
  public MetaInformation updateMetaInformation(
      int metaInfoID, String name, String keywords, String topics) {
    log.trace(
        "updateMetaInformation - method entered: metaInfoID={}, name={}, keywords={},topics={}",
        metaInfoID,
        name,
        keywords,
        topics);

    Optional<MetaInformation> metaInformationOptional =
        metaInformationRepository.findById(metaInfoID);

    metaInformationOptional.ifPresent(
        newMetaInformation -> {
          newMetaInformation.setName(name);
          newMetaInformation.setKeywords(keywords);
          newMetaInformation.setTopics(topics);
          log.debug("updateMetaInformation - updated: newMetaInformation={}", newMetaInformation);
        });
    log.trace("updateMetaInformation - method finished result={}", metaInformationOptional);
    return metaInformationOptional.orElse(null);
  }

  @Override
  public MetaInformation saveMetaInformation(String name, String keywords, String topics) {
    log.trace(
        "saveMetaInformation - method entered: name={}, keywords={},topics={}",
        name,
        keywords,
        topics);
    MetaInformation newMetaInformation =
        MetaInformation.builder().name(name).keywords(keywords).topics(topics).build();

    metaInformationRepository.save(newMetaInformation);

    log.trace("saveMetaInformation - method finished result={}", newMetaInformation);
    return newMetaInformation;
  }

  @Override
  public void deleteMetaInformation(int metaInfoID) {
    log.trace("deleteConference - method entered: metaInfoID={}", metaInfoID);
    metaInformationRepository.deleteById(metaInfoID);
    log.trace("deleteConference - method finished");
  }
}
