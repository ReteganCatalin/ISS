package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.ubb.iss.CMS.domain.MyTicket;
import ro.ubb.iss.CMS.domain.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SElECT distinct user from  User user where  user.userID = :userID")
    @EntityGraph(value = "userWithUserInfo", type =
            EntityGraph.EntityGraphType.LOAD)
    Optional<User> findUserWithUserInfoById(@Param("userID") Integer userID);


}
