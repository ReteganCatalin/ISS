package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.PermissionForUser;
import ro.ubb.iss.CMS.domain.PermissionForUserKey;

import java.util.List;
import java.util.Optional;

public interface PermissionForUserService {

    Optional<PermissionForUser> findPermissionForUser(PermissionForUserKey permissionForUserKey);

    List<PermissionForUser> findAll();

    //PermissionForRole updatePermissionForUser(PermissionForRoleKey permissionForRoleKey); No update needed

    Optional<PermissionForUser> savePermissionForUser(PermissionForUserKey permissionForUserKey);

    void deletePermissionForUser(PermissionForUserKey permissionForUserKey);

}
