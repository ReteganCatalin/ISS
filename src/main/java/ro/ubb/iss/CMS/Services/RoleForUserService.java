package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.*;

import java.util.List;
import java.util.Optional;

public interface RoleForUserService {
  Optional<RoleForUser> findRoleForUser(RoleForUserKey roleForUserKey);

  List<RoleForUser> findAll();

  // Role updateRoleForUser(RoleForUserKey roleID, String name);

  Optional<RoleForUser> saveRoleForUser(RoleForUserKey roleForUserKey);

  void deleteRoleForUser(RoleForUserKey roleForUserKey);
}
