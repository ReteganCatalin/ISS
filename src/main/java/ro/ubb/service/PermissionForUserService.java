package ro.ubb.service;

import ro.ubb.domain.PermissionForUser;

import java.util.List;
import java.util.Optional;

public interface PermissionForUserService {
    Optional<PermissionForUser> findPermissionForUser(int permissionForUserID);

    List<PermissionForUser> findAll();

    PermissionForUser updatePermissionForUser(int permissionForUserID, String format, String byteFileLocation);

    PermissionForUser savePermissionForUser(String format, String byteFileLocation);

    void deletePermissionForUser(int permissionForUserID);
}
