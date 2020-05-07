package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.RoleForUserService;
import ro.ubb.iss.CMS.converter.RoleForUserConverter;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.PermissionForRoleDto;
import ro.ubb.iss.CMS.dto.RoleForUserDto;
import ro.ubb.iss.CMS.dto.RolesForUserDto;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
public class RoleForUserController {

    public static final Logger log = LoggerFactory.getLogger(RoleForUserController.class);

    @Autowired
    private RoleForUserService service;

    @Autowired private RoleForUserConverter converter;

    @PersistenceContext // or even @Autowired
    private EntityManager entityManager;

    @RequestMapping(value = "/rolesForUser", method = RequestMethod.GET)
    public RolesForUserDto getAllRoleForUsers() {
        log.trace("getAllRolesForUser - method entered");
        RolesForUserDto result = new RolesForUserDto(converter.convertModelsToDtos(service.findAll()));
        log.trace("getAllRolesForUser - method finished: result={}", result);
        return result;
    }

    @RequestMapping(value = "/rolesForUser/{id}", method = RequestMethod.GET)
    public RoleForUserDto getRoleForUser(@PathVariable RoleForUserKey roleForUserKey) {
        log.trace("getRoleForUser - method entered roleForUserKey={}", roleForUserKey);
        Optional<RoleForUser> anAbstract = service.findRoleForUser(roleForUserKey);
        RoleForUserDto result = null;
        if (anAbstract.isPresent()) result = converter.convertModelToDto(anAbstract.get());
        log.trace("getRoleForUser - method finished: result={}", result);
        return result;
    }

    @RequestMapping(value = "/rolesForUser", method = RequestMethod.POST)
    public RoleForUserDto saveRoleForUser(@RequestBody RoleForUserKey roleForUserKey) {
        log.trace("saveRoleForUser - method entered roleForUserKey={}", roleForUserKey);
        Optional<RoleForUser> result =
                service.saveRoleForUser(new RoleForUserKey(
                        roleForUserKey.getUserID(), roleForUserKey.getRoleID()));
        RoleForUserDto resultToReturn = null;
        if (result.isPresent()) resultToReturn = converter.convertModelToDto(result.get());
        log.trace("saveRoleForUser - method finished: result={}", resultToReturn);
        return resultToReturn;
    }


    @RequestMapping(value = "/rolesForUser/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRoleForUser(@PathVariable RoleForUserKey roleForUserKey) {
        log.trace("deleteRoleForUser - method entered: roleForUserKey={}", roleForUserKey);
        try {
            service.deleteRoleForUser(roleForUserKey);
        } catch (RestClientException ex) {
            log.trace("deleteRoleForUser - exception caught ex={}", ex.getMessage());
            log.trace("deleteRoleForUser - method finished bad");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.trace("deleteRoleForUser - method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
