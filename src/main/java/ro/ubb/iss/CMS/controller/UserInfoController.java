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
import ro.ubb.iss.CMS.domain.UserInfo;
import ro.ubb.iss.CMS.dto.AuthorDto;
import ro.ubb.iss.CMS.dto.AuthorsDto;
import ro.ubb.iss.CMS.dto.UserInfoDto;

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



    @RequestMapping(value = "/user_info/{id}", method = RequestMethod.GET)
    public UserInfoDto getUserInfo(@PathVariable Integer id) {
        log.trace("getUserInfo - method entered id={}", id);
        Optional<UserInfo> userInfo = service.findUserInfo(id);
        UserInfoDto result = null;
        if (userInfo.isPresent()) result = converter.convertModelToDto(userInfo.get());
        log.trace("getUserInfo - method finished: result={}", result);
        return result;
    }

}