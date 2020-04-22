package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.RoleForUser;
import ro.ubb.iss.CMS.domain.RoleForUserKey;

public interface RoleForUserRepository extends JpaRepository<RoleForUser, RoleForUserKey> {
}
