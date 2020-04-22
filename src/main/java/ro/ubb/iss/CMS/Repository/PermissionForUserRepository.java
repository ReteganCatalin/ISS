package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.PermissionForUser;
import ro.ubb.iss.CMS.domain.PermissionForUserKey;
import ro.ubb.iss.CMS.domain.Role;

public interface PermissionForUserRepository extends JpaRepository<PermissionForUser, PermissionForUserKey> {
}
