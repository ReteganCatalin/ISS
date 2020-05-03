package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.iss.CMS.Services.ProposalService;
import ro.ubb.iss.CMS.Services.UserService;
import ro.ubb.iss.CMS.converter.ProposalConverter;
import ro.ubb.iss.CMS.converter.UserConverter;
import ro.ubb.iss.CMS.dto.ProposalDto;
import ro.ubb.iss.CMS.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProposalController {

    public static final Logger log = LoggerFactory.getLogger(ProposalController.class);


    @Autowired
    private ProposalService service;

    @Autowired private ProposalConverter converter;

    @RequestMapping(value = "/proposals", method = RequestMethod.GET)
    @Transactional
    public List<ProposalDto> getAllUsers() {
        log.trace("getAllUserInfos - method entered");
        List<ProposalDto> userInfoDtos =
                service.findAll().stream()
                        .map(elem -> converter.convertModelToDto(elem))
                        .collect(Collectors.toList());
        log.trace("getAllUserInfos - method finished");
        return userInfoDtos;
    }

}
