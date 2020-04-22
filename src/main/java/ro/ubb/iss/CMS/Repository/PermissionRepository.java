package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.Permission;

public interface PermissionRepository extends JpaRepository<Permission,Integer> {
}
