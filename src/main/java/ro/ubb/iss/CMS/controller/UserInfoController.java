package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.AuthorService;
import ro.ubb.iss.CMS.Services.UserInfoService;
import ro.ubb.iss.CMS.converter.AuthorConverter;
import ro.ubb.iss.CMS.converter.UserInfoConverter;
import ro.ubb.iss.CMS.domain.Author;
import ro.ubb.iss.CMS.domain.Proposal;
import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.domain.UserInfo;
import ro.ubb.iss.CMS.dto.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
public class UserInfoController {

    public static final Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService service;

    @Autowired private UserInfoConverter converter;

    @PersistenceContext // or even @Autowired
    private EntityManager entityManager;

    @RequestMapping(value = "/user_info", method = RequestMethod.GET)
    public UserInfosDto getAllUserInfos() {
        log.trace("getAllUserInfos - method entered");
        UserInfosDto result = new UserInfosDto(converter.convertModelsToDtos(service.findAll()));
        log.trace("getAllUserInfos - method finished: result={}", result);
        return result;
    }

    @RequestMapping(value = "/user_info/{id}", method = RequestMethod.GET)
    public UserInfoDto getUserInfo(@PathVariable int id) {
        log.trace("getUserInfo - method entered id={}", id);
        Optional<UserInfo> userInfo = service.findUserInfo(id);
        UserInfoDto result = null;
        if (userInfo.isPresent()) result = converter.convertModelToDto(userInfo.get());
        log.trace("getUserInfo - method finished: result={}", result);
        return result;
    }

    @RequestMapping(value = "/user_info", method = RequestMethod.POST)
    public UserInfoDto saveUserInfo(@RequestBody UserInfoDto userInfoDto) {
        log.trace("saveUserInfo - method entered userInfoDto={}", userInfoDto);
        UserInfo result =
                service.saveUserInfo(userInfoDto.getName(),
                        userInfoDto.getAffiliation(),
                        userInfoDto.getEmailAddress(),
                        userInfoDto.getWebpageAddress());

        UserInfoDto resultToReturn = converter.convertModelToDto(result);
        log.trace("saveUserInfo - method finished: result={}", resultToReturn);
        return resultToReturn;
    }

    @RequestMapping(value = "/user_info", method = RequestMethod.PUT)
    public UserInfoDto updateUserInfo(@RequestBody UserInfoDto userInfoDto) {
        log.trace("updateUserInfo - method entered: userInfoDto={}", userInfoDto);
        UserInfoDto result =
                converter.convertModelToDto(
                        service.updateUserInfo(
                                userInfoDto.getUserInfoId(),
                                userInfoDto.getName(),
                                userInfoDto.getAffiliation(),
                                userInfoDto.getEmailAddress(),
                                userInfoDto.getWebpageAddress()
                        ));
        log.trace("updateUserInfo - method finished: result={}", result);
        return result;
    }

    @RequestMapping(value = "/user_info/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserInfo(@PathVariable Integer id) {
        log.trace("deleteUserInfo - method entered: id={}", id);
        try {
            service.deleteUserInfo(id);
        } catch (RestClientException ex) {
            log.trace("deleteUserInfo - exception caught ex={}", ex.getMessage());
            log.trace("deleteUserInfo - method finished bad");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.trace("deleteUserInfo - method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}