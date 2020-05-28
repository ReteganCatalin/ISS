package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.converter.AuthorConverter;
import ro.ubb.iss.CMS.Services.AuthorService;
import ro.ubb.iss.CMS.domain.Author;
import ro.ubb.iss.CMS.domain.Proposal;
import ro.ubb.iss.CMS.dto.AuthorDto;
import ro.ubb.iss.CMS.dto.AuthorsDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
public class AuthorController {

  public static final Logger log = LoggerFactory.getLogger(AuthorController.class);

  @Autowired private AuthorService service;

  @Autowired private AuthorConverter converter;

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @RequestMapping(value = "/authors", method = RequestMethod.GET)
  public ResponseEntity<AuthorsDto> getAllAuthors() {
    log.trace("getAllAuthors - method entered");
    AuthorsDto result = new AuthorsDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllAuthors - method finished: result={}", result);
    return new ResponseEntity<>(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/authors/{id}", method = RequestMethod.GET)
  public ResponseEntity<AuthorDto> getAuthor(@PathVariable Integer id) {
    log.trace("getAuthor - method entered id={}", id);
    Optional<Author> author = service.findAuthor(id);
    AuthorDto result = null;
    if (author.isPresent()) result = converter.convertModelToDto(author.get());
    log.trace("getAuthor - method finished: result={}", result);
    return new ResponseEntity<>(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/authors", method = RequestMethod.POST)
  public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto authorDto) {
    log.trace("saveAuthor - method entered authorDto={}", authorDto);
    Author authorToAdd = converter.convertDtoToModel(authorDto);
    Author result =
        service.saveAuthor(
            authorDto.getName(),
            entityManager.getReference(Proposal.class, authorDto.getProposalId()));
    AuthorDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveAuthor - method finished: result={}", resultToReturn);
    return new ResponseEntity<>(resultToReturn,HttpStatus.OK);
  }

  @RequestMapping(value = "/authors", method = RequestMethod.PUT)
  public ResponseEntity<AuthorDto> updateAuthor(@RequestBody AuthorDto authorDto) {
    log.trace("updateAuthor - method entered: authorDto={}", authorDto);
    AuthorDto result =
        converter.convertModelToDto(
            service.updateAuthor(
                authorDto.getAuthorId(),
                authorDto.getName(),
                entityManager.getReference(Proposal.class, authorDto.getProposalId())));
    log.trace("updateAuthor - method finished: result={}", result);
    return new ResponseEntity<>(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/authors/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteAuthor(@PathVariable Integer id) {
    log.trace("deleteAuthor - method entered: id={}", id);

    try {
      service.deleteAuthor(id);
    } catch (RestClientException ex) {
      log.trace("deleteAuthor - exception caught ex={}", ex.getMessage());
      log.trace("deleteAuthor - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteAuthor - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
