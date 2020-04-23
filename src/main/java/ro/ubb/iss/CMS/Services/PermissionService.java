package ro.ubb.iss.CMS.Services;



import ro.ubb.iss.CMS.domain.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionService {

    Optional<Permission> findPermission(int permissionID);

    List<Permission> findAll();

    Permission updatePermission(int permissionID, String name);

    Permission savePermission(String name);

    void deletePermission(int permissionID);

}
