package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.iss.CMS.Repository.QualifierRepository;
import ro.ubb.iss.CMS.domain.Qualifier;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.domain.UserInfo;

import java.util.List;
import java.util.Optional;

public class QualifierServiceImplementation implements QualifierService {
  private static final Logger log = LoggerFactory.getLogger(QualifierServiceImplementation.class);

  @Autowired
  private QualifierRepository qualifierRepository;
  @Override
  public Optional<Qualifier> findQualifier(int qualifierID) {
    log.trace("findQualifier - method entered qualifierID={}", qualifierID);
    Optional<Qualifier> result = qualifierRepository.findById(qualifierID);
    log.trace("findQualifier - method exit result={}", result);
    return result;
  }

  @Override
  public List<Qualifier> findAll() {
    log.trace("findAll - method entered");
    List<Qualifier> result = qualifierRepository.findAll();
    log.trace("findAll - method exit result={}", result);
    return result;
  }

  @Override
  @Transactional
  public Qualifier updateQualifier(int qualifierID, String name) {
    log.trace(
            "updateQualifier - method entered: qualifierID={}, name={}",
            qualifierID,
            name);

    Optional<Qualifier> abstractOptional = qualifierRepository.findById(qualifierID);

    abstractOptional.ifPresent(
            newQualifier -> {
              newQualifier.setName(name);

              log.debug("updateQualifier - updated: newQualifier={}", newQualifier);
            });
    log.trace("updateQualifier - method finished result={}", abstractOptional);
    return abstractOptional.orElse(null);
  }

  @Override
  public Qualifier saveQualifier(String name) {
    log.trace(
            "saveQualifier - method entered: name={}",
            name);
    Qualifier newQualifier =
            Qualifier.builder()
                    .name(name)
                    .build();

    qualifierRepository.save(newQualifier);

    log.trace("saveQualifier - method finished result={}", newQualifier);
    return newQualifier;
  }

  @Override
  public void deleteQualifier(int qualifierID) {
    log.trace("deleteQualifier - method entered: qualifierID={}", qualifierID);
    qualifierRepository.deleteById(qualifierID);
    log.trace("deleteQualifier - method finished");
  }
}
