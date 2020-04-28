package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.converter.PermissionConverter;
import ro.ubb.iss.CMS.Services.PermissionService;
import ro.ubb.iss.CMS.domain.Permission;
import ro.ubb.iss.CMS.dto.PermissionDto;
import ro.ubb.iss.CMS.dto.PermissionsDto;

import java.util.Optional;

@RestController
public class PermissionController {

  public static final Logger log = LoggerFactory.getLogger(PermissionController.class);

  @Autowired private PermissionService service;

  @Autowired private PermissionConverter converter;

  @RequestMapping(value = "/permissions", method = RequestMethod.GET)
  public PermissionsDto getAllMetaInformations() {
    log.trace("getAllConferences - method entered");
    PermissionsDto result = new PermissionsDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllConferences - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/permissions/{id}", method = RequestMethod.GET)
  public PermissionDto getMetaInformation(@PathVariable Integer id) {
    log.trace("getConference - method entered id={}", id);
    Optional<Permission> metaInformation = service.findPermission(id);
    PermissionDto result = null;
    if (metaInformation.isPresent()) result = converter.convertModelToDto(metaInformation.get());
    log.trace("getConference - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/permissions", method = RequestMethod.POST)
  public PermissionDto saveMetaInformation(@RequestBody PermissionDto permissionDto) {
    log.trace("saveConference - method entered permissionDto={}", permissionDto);
    Permission result =
        service.savePermission(
            permissionDto.getPermissionName());
    PermissionDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveConference - method finished: result={}", resultToReturn);
    return resultToReturn;
  }

  @RequestMapping(value = "/permissions", method = RequestMethod.PUT)
  public PermissionDto updateMetaInformation(@RequestBody PermissionDto permissionDto) {
    log.trace("updateConference - method entered: permissionDto={}", permissionDto);
    PermissionDto result =
        converter.convertModelToDto(
            service.updatePermission(
                permissionDto.getPermissionID(),
                permissionDto.getPermissionName()));
    log.trace("updateConference - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/permissions/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteMetaInformation(@PathVariable Integer id) {
    log.trace("deleteConference - method entered: id={}", id);

    try {
      service.deletePermission(id);
    } catch (RestClientException ex) {
      log.trace("deleteConference - exception caught ex={}", ex.getMessage());
      log.trace("deleteConference - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteConference - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
