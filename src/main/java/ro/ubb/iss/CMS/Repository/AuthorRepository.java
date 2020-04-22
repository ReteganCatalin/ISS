package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
