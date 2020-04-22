package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.PermissionForUser;

public interface PermissionForUserRepository extends JpaRepository<PermissionForUser,Integer> {
}
