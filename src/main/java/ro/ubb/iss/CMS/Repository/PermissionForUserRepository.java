package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.PermissionForUser;

public interface PermissionForUserRepository extends JpaRepository<PermissionForUser,Integer> {
}
