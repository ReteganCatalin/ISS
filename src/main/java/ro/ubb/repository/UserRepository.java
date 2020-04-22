package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.ubb.domain.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
