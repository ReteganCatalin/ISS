package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.Qualifier;

public interface QualifierRepository  extends JpaRepository<Qualifier,Integer> {
}
