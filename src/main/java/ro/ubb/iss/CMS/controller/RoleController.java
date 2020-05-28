package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.AbstractService;
import ro.ubb.iss.CMS.Services.RoleService;
import ro.ubb.iss.CMS.converter.AbstractConverter;
import ro.ubb.iss.CMS.converter.RoleConverter;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.domain.Role;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.AbstractsDto;
import ro.ubb.iss.CMS.dto.RoleDto;
import ro.ubb.iss.CMS.dto.RolesDto;

import java.util.Optional;

@RestController
public class RoleController {

  public static final Logger log = LoggerFactory.getLogger(RoleController.class);

  @Autowired private RoleService service;

  @Autowired private RoleConverter converter;

  @RequestMapping(value = "/roles", method = RequestMethod.GET)
  public ResponseEntity<RolesDto> getAllRoles() {
    log.trace("getAllRoles - method entered");
    RolesDto result = new RolesDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllRoles - method finished: result={}", result);
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
  public ResponseEntity<RoleDto> getRole(@PathVariable Integer id) {
    log.trace("getRole - method entered id={}", id);
    Optional<Role> anAbstract = service.findRole(id);
    RoleDto result = null;
    if (anAbstract.isPresent()) result = converter.convertModelToDto(anAbstract.get());
    log.trace("getRole - method finished: result={}", result);
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/roles", method = RequestMethod.POST)
  public ResponseEntity<RoleDto> saveRole(@RequestBody RoleDto roleDto) {
    log.trace("saveRole - method entered roleDto={}", roleDto);
    Role result = service.saveRole(roleDto.getName());

    RoleDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveRole - method finished: result={}", resultToReturn);
    return new ResponseEntity(resultToReturn,HttpStatus.OK);
  }

  @RequestMapping(value = "/roles", method = RequestMethod.PUT)
  public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto roleDto) {
    log.trace("updateRole - method entered: abstractDto={}", roleDto);
    RoleDto result =
        converter.convertModelToDto(service.updateRole(roleDto.getRoleID(), roleDto.getName()));
    log.trace("updateRole - method finished: result={}", result);
    return new ResponseEntity(result,HttpStatus.OK);
  }

  @RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteRole(@PathVariable Integer id) {
    log.trace("deleteRole - method entered: id={}", id);
    try {
      service.deleteRole(id);
    } catch (RestClientException ex) {
      log.trace("deleteRole - exception caught ex={}", ex.getMessage());
      log.trace("deleteRole - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteRole - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
