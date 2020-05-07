package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.AbstractService;
import ro.ubb.iss.CMS.Services.UserService;
import ro.ubb.iss.CMS.converter.AbstractConverter;
import ro.ubb.iss.CMS.converter.UserConverter;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.domain.UserInfo;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.AbstractsDto;
import ro.ubb.iss.CMS.dto.UserDto;
import ro.ubb.iss.CMS.dto.UsersDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
public class UserController {

    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @Autowired private UserConverter converter;

    @PersistenceContext // or even @Autowired
    private EntityManager entityManager;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public UsersDto getAllUsers() {
        log.trace("getAllUsers - method entered");
        UsersDto result = new UsersDto(converter.convertModelsToDtos(service.findAll()));
        log.trace("getAllUsers - method finished: result={}", result);
        return result;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UserDto getUser(@PathVariable Integer id) {
        log.trace("getUser - method entered id={}", id);
        Optional<User> anAbstract = service.findUser(id);
        UserDto result = null;
        if (anAbstract.isPresent()) result = converter.convertModelToDto(anAbstract.get());
        log.trace("getUser - method finished: result={}", result);
        return result;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public UserDto saveUser(@RequestBody UserDto userDto) {
        log.trace("saveUser - method entered userDto={}", userDto);
        User result =
                service.saveUser(userDto.getUsername(),
                        userDto.getPassword(),
                        userDto.getIsValidated(),
                        entityManager.getReference(UserInfo.class,userDto.getUserInfoId()));

        UserDto resultToReturn = converter.convertModelToDto(result);
        log.trace("saveUser - method finished: result={}", resultToReturn);
        return resultToReturn;
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        log.trace("updateUser - method entered: userDto={}", userDto);
        UserDto result =
                converter.convertModelToDto(
                        service.updateUser(
                                userDto.getUserId(),
                                userDto.getUsername(),
                                userDto.getPassword(),
                                userDto.getIsValidated(),
                                entityManager.getReference(UserInfo.class,userDto.getUserInfoId())
                        ));
        log.trace("updateUser - method finished: result={}", result);
        return result;
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
