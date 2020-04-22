package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.PermissionListKey;

public interface PermissionListKeyRepository  extends JpaRepository<PermissionListKey,Integer> {
}
