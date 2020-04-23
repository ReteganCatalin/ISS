package ro.ubb.iss.CMS.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.iss.CMS.Converter.AbstractConverter;
import ro.ubb.iss.CMS.Services.AbstractService;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.AbstractsDto;

import java.util.Optional;

@RestController
public class AbstractController {

  public static final Logger log = LoggerFactory.getLogger(AbstractController.class);

  @Autowired private AbstractService service;

  @Autowired private AbstractConverter converter;

  @RequestMapping(value = "/abstracts", method = RequestMethod.GET)
  public AbstractsDto getAllAbstracts() {
    log.trace("getAllAbstracts - method entered");
    AbstractsDto result = new AbstractsDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllAbstracts - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/abstracts/{id}", method = RequestMethod.GET)
  public AbstractDto getAbstract(@PathVariable Integer id) {
    log.trace("getAbstract - method entered id={}", id);
    Optional<Abstract> anAbstract = service.findAbstract(id);
    AbstractDto result = null;
    if (anAbstract.isPresent()) result = converter.convertModelToDto(anAbstract.get());
    log.trace("getAbstract - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/abstracts", method = RequestMethod.POST)
  public AbstractDto saveAbstract(@RequestBody AbstractDto abstractDto) {
    log.trace("saveAbstract - method entered abstractDto={}", abstractDto);
    Abstract result =
        service.saveAbstract(abstractDto.getFormat(), abstractDto.getByteFileLocation());

    AbstractDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveAbstract - method finished: result={}", resultToReturn);
    return resultToReturn;
  }

  @RequestMapping(value = "/abstracts", method = RequestMethod.PUT)
  public AbstractDto updateAbstract(@RequestBody AbstractDto abstractDto) {
    log.trace("updateAbstract - method entered: abstractDto={}", abstractDto);
    Abstract anAbstract = converter.convertDtoToModel(abstractDto);
    AbstractDto result =
        converter.convertModelToDto(
            service.updateAbstract(
                anAbstract.getAbstractID(),
                anAbstract.getFormat(),
                anAbstract.getByteFileLocation()));
    log.trace("updateAbstract - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/abstracts/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteAbstract(@PathVariable Integer id) {
    log.trace("deleteAbstract - method entered: id={}", id);

    service.deleteAbstract(id);
    log.trace("deleteAbstract - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
