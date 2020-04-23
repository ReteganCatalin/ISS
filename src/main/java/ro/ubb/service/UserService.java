package ro.ubb.service;

import ro.ubb.domain.User;
import ro.ubb.domain.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUser(int userID);

    List<User> findAll();

    User updateUser(int userID, String username, String password, boolean isValidated, UserInfo userInfoID);

    User saveUser(String username, String password, boolean isValidated, UserInfo userInfoID);

    void deleteUser(int userID);
}
