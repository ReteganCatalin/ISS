package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.Permission;

public interface PermissionRepository extends JpaRepository<Permission,Integer> {
}
