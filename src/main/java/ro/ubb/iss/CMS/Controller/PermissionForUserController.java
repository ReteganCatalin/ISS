package ro.ubb.iss.CMS.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Converter.PermissionForUserConverter;
import ro.ubb.iss.CMS.Services.PermissionForUserService;
import ro.ubb.iss.CMS.domain.PermissionForUser;
import ro.ubb.iss.CMS.domain.PermissionForUserKey;
import ro.ubb.iss.CMS.dto.PermissionForUserDto;
import ro.ubb.iss.CMS.dto.PermissionForUsersDto;

import java.util.Optional;

@RestController
public class PermissionForUserController {

  public static final Logger log = LoggerFactory.getLogger(PermissionForUserController.class);

  @Autowired private PermissionForUserService service;

  @Autowired private PermissionForUserConverter converter;

  @RequestMapping(value = "/permissionsforusers", method = RequestMethod.GET)
  public PermissionForUsersDto getAllPermissionsForUsers() {
    log.trace("getAllPermissionsForUsers - method entered");
    PermissionForUsersDto result = new PermissionForUsersDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllPermissionsForUsers - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/permissionsforusers/{permissionForUserKey}", method = RequestMethod.GET)
  public PermissionForUserDto getPermissionForUser(@PathVariable PermissionForUserKey permissionForUserKey) {
    log.trace("getPermissionForUser - method entered permissionForUserKey={}", permissionForUserKey);
    Optional<PermissionForUser> analysis = service.findPermissionForUser(permissionForUserKey);
    PermissionForUserDto result = null;
    if (analysis.isPresent()) result = converter.convertModelToDto(analysis.get());
    log.trace("getPermissionForUser - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/permissionsforusers", method = RequestMethod.POST)
  public PermissionForUserDto savePermissionForUser(@RequestBody PermissionForUserKey permissionForUserKey) {
    log.trace("saveAnalysis - method entered analysisDto={}", permissionForUserKey);
    Optional<PermissionForUser> result =
        service.savePermissionForUser(
            new PermissionForUserKey(
                permissionForUserKey.getUserID(), permissionForUserKey.getPermissionID()));
    PermissionForUserDto resultToReturn = null;
    if (result.isPresent()) resultToReturn = converter.convertModelToDto(result.get());
    log.trace("saveAnalysis - method finished: result={}", resultToReturn);
    return resultToReturn;
  }


  @RequestMapping(value = "/permissionsforusers/{permissionForUserKey}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deletePermissionForUser(@PathVariable PermissionForUserKey permissionForUserKey) {
    log.trace("deletePermissionForUser - method entered: analysisKey={}", permissionForUserKey);

    try {
      service.deletePermissionForUser(permissionForUserKey);
    } catch (RestClientException ex) {
      log.trace("deletePermissionForUser - exception caught ex={}", ex.getMessage());
      log.trace("deletePermissionForUser - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deletePermissionForUser - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
