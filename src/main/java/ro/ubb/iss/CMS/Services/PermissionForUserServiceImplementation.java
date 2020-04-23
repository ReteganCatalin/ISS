package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.iss.CMS.Repository.PermissionForUserRepository;
import ro.ubb.iss.CMS.domain.PermissionForUser;
import ro.ubb.iss.CMS.domain.PermissionForUserKey;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionForUserServiceImplementation implements PermissionForUserService {

  private static final Logger log =
      LoggerFactory.getLogger(PermissionForUserServiceImplementation.class);

  @Autowired PermissionForUserRepository permissionForUserRepository;

  @Override
  public Optional<PermissionForUser> findPermissionForUser(
      PermissionForUserKey permissionForUserKey) {
    log.trace(
        "findPermissionForRole - method entered permissionForUserKey={}", permissionForUserKey);
    Optional<PermissionForUser> result = permissionForUserRepository.findById(permissionForUserKey);
    log.trace("findPermissionForRole - method exit result={}", result);
    return result;
  }

  @Override
  public List<PermissionForUser> findAll() {
    log.trace("findAll - method entered");
    List<PermissionForUser> result = permissionForUserRepository.findAll();
    log.trace("findAll - method exit result={}", result);
    return result;
  }

  @Override
  public Optional<PermissionForUser> savePermissionForUser(
      PermissionForUserKey permissionForUserKey) {
    log.trace(
        "savePermissionForRole - method entered: permissionForUserKey={}", permissionForUserKey);
    PermissionForUser newPermissionForRole =
        PermissionForUser.builder().permissionForUserKey(permissionForUserKey).build();
    Optional<PermissionForUser> checkForPresence =
        permissionForUserRepository.findById(permissionForUserKey);

    checkForPresence.ifPresentOrElse(
        analysis -> {}, () -> permissionForUserRepository.save(newPermissionForRole));

    log.trace("savePermissionForRole - method finished result={}", checkForPresence);
    return checkForPresence;
  }

  @Override
  public void deletePermissionForUser(PermissionForUserKey permissionForUserKey) {
    log.trace(
        "deletePermissionForRole - method entered: permissionForUserKey={}", permissionForUserKey);
    permissionForUserRepository.deleteById(permissionForUserKey);
    log.trace("deletePermissionForRole - method finished");
  }
}
