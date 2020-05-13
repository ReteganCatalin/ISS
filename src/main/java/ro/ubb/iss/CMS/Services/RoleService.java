package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
  Optional<Role> findRole(int roleID);

  List<Role> findAll();

  Role updateRole(int roleID, String name);

  Role saveRole(String name);

  void deleteRole(int roleID);
}
