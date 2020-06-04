package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.domain.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserService {
  Optional<User> findUser(int userID);

  List<User> findAll();

  User updateUser(
      int userID, String username, String password, boolean isValidated, UserInfo userInfoID);

  User saveUser(String username, String password, boolean isValidated, UserInfo userInfoID);

  void deleteUser(int userID);

    Optional<User> getUserWithUserInfo(Integer userID);
}
