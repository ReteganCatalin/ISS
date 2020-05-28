package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.AbstractService;
import ro.ubb.iss.CMS.Services.UserService;
import ro.ubb.iss.CMS.converter.AbstractConverter;
import ro.ubb.iss.CMS.converter.ProposalConverter;
import ro.ubb.iss.CMS.converter.UserConverter;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.domain.UserInfo;
import ro.ubb.iss.CMS.dto.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
public class UserController {

  public static final Logger log = LoggerFactory.getLogger(UserController.class);

  @Autowired private UserService service;

  @Autowired private UserConverter converter;
  @Autowired private ProposalConverter proposalConverter;

  @PersistenceContext // or even @Autowired
  private EntityManager entityManager;

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public ResponseEntity<UsersDto> getAllUsers() {
    log.trace("getAllUsers - method entered");
    UsersDto result = new UsersDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllUsers - method finished: result={}", result);
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
  public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
    log.trace("getUser - method entered id={}", id);
    Optional<User> user = service.findUser(id);
    UserDto result = null;
    if (user.isPresent()) result = converter.convertModelToDto(user.get());
    log.trace("getUser - method finished: result={}", result);
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{id}/proposals", method = RequestMethod.GET)
  @Transactional
  public ResponseEntity<ProposalsDto> getUserProposals(@PathVariable Integer id) {
    log.trace("getUserProposals - method entered id={}", id);
    Optional<User> user = service.findUser(id);
    ProposalsDto result = null;
    if (user.isPresent())
      result =
          ProposalsDto.builder()
              .proposalDtoList(
                  proposalConverter.convertModelsToDtos(user.get().getUserInfo().getProposals()))
              .build();
    log.trace("getUserProposals - method finished: result={}", result);
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/users", method = RequestMethod.POST)
  public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
    log.trace("saveUser - method entered userDto={}", userDto);
    User result =
        service.saveUser(
            userDto.getUsername(),
            userDto.getPassword(),
            userDto.getIsValidated(),
            entityManager.getReference(UserInfo.class, userDto.getUserInfoID()));

    UserDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveUser - method finished: result={}", resultToReturn);
    return new ResponseEntity(resultToReturn,HttpStatus.OK);
  }

  @RequestMapping(value = "/users", method = RequestMethod.PUT)
  public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
    log.trace("updateUser - method entered: userDto={}", userDto);
    UserDto result =
        converter.convertModelToDto(
            service.updateUser(
                userDto.getUserID(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getIsValidated(),
                entityManager.getReference(UserInfo.class, userDto.getUserInfoID())));
    log.trace("updateUser - method finished: result={}", result);
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
    log.trace("deleteUser - method entered: id={}", id);
    try {
      service.deleteUser(id);
    } catch (RestClientException ex) {
      log.trace("deleteUser - exception caught ex={}", ex.getMessage());
      log.trace("deleteUser - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteUser - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
