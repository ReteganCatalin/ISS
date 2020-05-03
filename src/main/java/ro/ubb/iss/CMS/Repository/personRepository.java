package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.iss.CMS.domain.Person;

@Repository
public interface personRepository extends JpaRepository<Person, Integer> {
  @Query("SELECT name FROM Person p WHERE p.name LIKE %:personName%")
  String findByName(String personName);
}
