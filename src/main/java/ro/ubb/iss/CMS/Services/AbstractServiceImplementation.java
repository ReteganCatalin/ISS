package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.Repository.AbstractRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AbstractServiceImplementation implements AbstractService {
  private static final Logger log = LoggerFactory.getLogger(AbstractServiceImplementation.class);

  @Autowired private AbstractRepository abstractRepository;

  @Override
  public Optional<Abstract> findAbstract(int abstractID) {
    log.trace("findAbstract - method entered abstractID={}", abstractID);
    Optional<Abstract> result = abstractRepository.findById(abstractID);
    log.trace("findAbstract - method exit result={}", result);
    return result;
  }

  @Override
  @GetMapping("abstract")
  public List<Abstract> findAll() {
    log.trace("findAbstract - method entered");
    List<Abstract> result = abstractRepository.findAll();
    log.trace("findAbstract - method exit result={}", result);
    return result;
  }

  @Override
  @Transactional
  public Abstract updateAbstract(int abstractID, String format, String byteFileLocation) {
    log.trace(
        "updateAbstract - method entered: abstractID={}, format={}, byteFileLocation={}",
        abstractID,
        format,
        byteFileLocation);

    Optional<Abstract> abstractOptional = abstractRepository.findById(abstractID);

    abstractOptional.ifPresent(
        newAbstract -> {
          newAbstract.setByteFileLocation(format);
          newAbstract.setFormat(byteFileLocation);
          log.debug("updateAbstract - updated: newAbstract={}", newAbstract);
        });
    log.trace("updateAbstract - method finished result={}", abstractOptional);
    return abstractOptional.orElse(null);
  }

  @Override
  public Abstract saveAbstract(String format, String byteFileLocation) {
    log.trace(
        "saveAbstract - method entered: format={}, byteFileLocation={}", format, byteFileLocation);
    Abstract newAbstract =
        Abstract.builder().format(format).byteFileLocation(byteFileLocation).build();

    abstractRepository.save(newAbstract);
    log.trace("saveAbstract - method finished result={}", newAbstract);
    return newAbstract;
  }

  @Override
  public void deleteAbstract(int abstractID) {
    log.trace("deleteAbstract - method entered: abstractID={}", abstractID);
    abstractRepository.deleteById(abstractID);
    log.trace("deleteAbstract - method finished");
  }
}
