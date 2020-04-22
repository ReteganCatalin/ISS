package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.PermissionForRole;
import ro.ubb.iss.CMS.domain.PermissionForRoleKey;

public interface PermissionForRoleRepository extends JpaRepository<PermissionForRole, PermissionForRoleKey> {
}
