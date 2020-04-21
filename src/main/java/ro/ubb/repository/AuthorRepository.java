package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
