package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.iss.CMS.Services.UserInfoService;
import ro.ubb.iss.CMS.Services.UserService;
import ro.ubb.iss.CMS.converter.UserConverter;
import ro.ubb.iss.CMS.converter.UserInfoConverter;
import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.dto.UserDto;
import ro.ubb.iss.CMS.dto.UserInfoDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    public static final Logger log = LoggerFactory.getLogger(UserInfoController.class);


    @Autowired
    private UserService service;

    @Autowired private UserConverter converter;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserDto> getAllUserInfos() {
        log.trace("getAllUserInfos - method entered");
        List<UserDto> userInfoDtos =
                service.findAll().stream()
                        .map(elem -> converter.convertModelToDto(elem))
                        .collect(Collectors.toList());
        log.trace("getAllUserInfos - method finished");
        return userInfoDtos;
    }


}
