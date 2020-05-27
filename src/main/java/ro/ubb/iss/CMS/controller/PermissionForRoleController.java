package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.converter.PermissionForRoleConverter;
import ro.ubb.iss.CMS.Services.PermissionForRoleService;
import ro.ubb.iss.CMS.domain.PermissionForRole;
import ro.ubb.iss.CMS.domain.PermissionForRoleKey;
import ro.ubb.iss.CMS.dto.PermissionForRoleDto;
import ro.ubb.iss.CMS.dto.PermissionForRolesDto;

import java.util.Optional;

@RestController
public class PermissionForRoleController {

  public static final Logger log = LoggerFactory.getLogger(PermissionForRoleController.class);

  @Autowired private PermissionForRoleService service;

  @Autowired private PermissionForRoleConverter converter;

  @RequestMapping(value = "/permissionsforroles", method = RequestMethod.GET)
  public PermissionForRolesDto getAllPermissionsForRoles() {
    log.trace("getAllPermissionForRoles - method entered");
    PermissionForRolesDto result =
        new PermissionForRolesDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllPermissionForRoles - method finished: result={}", result);
    return result;
  }

  @RequestMapping(
      value = "/permissionsforroles/{roleID}/{permissionID}",
      method = RequestMethod.GET)
  public PermissionForRoleDto getPermissionForRole(
      @PathVariable Integer roleID, @PathVariable Integer permissionID) {
    log.trace(
        "getPermissionForRole - method entered roleID={}, permissionID={}", roleID, permissionID);
    PermissionForRoleKey permissionForRoleKey = new PermissionForRoleKey(roleID, permissionID);
    Optional<PermissionForRole> analysis = service.findPermissionForRole(permissionForRoleKey);
    PermissionForRoleDto result = null;
    if (analysis.isPresent()) result = converter.convertModelToDto(analysis.get());
    log.trace("getPermissionForRole - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/permissionsforroles", method = RequestMethod.POST)
  public PermissionForRoleDto savePermissionForRole(
      @RequestBody PermissionForRoleDto permissionForRoleDto) {
    log.trace(
        "savePermissionForRole - method entered permissionForRoleDto={}", permissionForRoleDto);
    Optional<PermissionForRole> result =
        service.savePermissionForRole(
            new PermissionForRoleKey(
                permissionForRoleDto.getRoleID(), permissionForRoleDto.getPermissionID()));
    PermissionForRoleDto resultToReturn = null;
    if (result.isPresent()) resultToReturn = converter.convertModelToDto(result.get());
    log.trace("savePermissionForRole - method finished: result={}", resultToReturn);
    return resultToReturn;
  }

  @RequestMapping(
      value = "/permissionsforroles/{roleID}/{permissionID}",
      method = RequestMethod.DELETE)
  public ResponseEntity<?> deletePermissionForRole(
      @PathVariable Integer roleID, @PathVariable Integer permissionID) {
    log.trace(
        "deletePermissionForRole - method entered: roleID={}, permissionID={}",
        roleID,
        permissionID);
    PermissionForRoleKey permissionForRoleKey = new PermissionForRoleKey(roleID, permissionID);

    try {
      service.deletePermissionForRole(permissionForRoleKey);
    } catch (RestClientException ex) {
      log.trace("deletePermissionForRole - exception caught ex={}", ex.getMessage());
      log.trace("deletePermissionForRole - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deletePermissionForRole - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
