package ro.ubb.iss.CMS.Controller;



import ro.ubb.iss.CMS.domain.PermissionForRole;
import ro.ubb.iss.CMS.domain.PermissionForRoleKey;

import java.util.List;
import java.util.Optional;

public interface PermissionForRoleService {

    Optional<PermissionForRole> findPermissionForRole(PermissionForRoleKey permissionForRoleKey);

    List<PermissionForRole> findAll();

    //PermissionForRole updatePermissionForRole(PermissionForRoleKey permissionForRoleKey); No update needed

    Optional<PermissionForRole> savePermissionForRole(PermissionForRoleKey permissionForRoleKey);

    void deletePermissionForRole(PermissionForRoleKey permissionForRoleKey);

}
