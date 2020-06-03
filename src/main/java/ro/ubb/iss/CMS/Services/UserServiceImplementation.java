package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.iss.CMS.Repository.UserRepository;
import ro.ubb.iss.CMS.domain.User;
import ro.ubb.iss.CMS.domain.UserInfo;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
  private static final Logger log = LoggerFactory.getLogger(UserServiceImplementation.class);

  @Autowired UserRepository userRepository;

  @Override
  public Optional<User> findUser(int userID) {
    log.trace("findUser - method entered");
    Optional<User> result = userRepository.findById(userID);
    log.trace("findUser - method exit result={}", result);
    return result;
  }

  @Override
  public List<User> findAll() {
    log.trace("findAll - method entered");
    List<User> result = userRepository.findAll();
    log.trace("findAll - method exit result={}", result);
    return result;
  }

  @Override
  @Transactional
  public User updateUser(
      int userID, String username, String password, boolean isValidated, UserInfo userInfoID) {
    log.trace(
        "updateUser - method entered: userID={}, username={}, password={}, isValidated={}, userInfoID={}",
        userID,
        username,
        password,
        isValidated,
        userInfoID);

    Optional<User> abstractOptional = userRepository.findById(userID);

    abstractOptional.ifPresent(
        newUser -> {
          newUser.setUsername(username);
          newUser.setPassword(password);
          newUser.setIsValidated(isValidated);
          newUser.setUserInfo(userInfoID);
          log.debug("updateUser - updated: newUser={}", newUser);
        });
    log.trace("updateUser - method finished result={}", abstractOptional);
    return abstractOptional.orElse(null);
  }

  @Override
  public User saveUser(String username, String password, boolean isValidated, UserInfo userInfoID) {
    log.trace(
        "saveUser - method entered: username={}, password={}, isValidated={}, userInfoID={}",
        username,
        password,
        isValidated,
        userInfoID);
    User newUser =
        User.builder()
            .username(username)
            .password(password)
            .isValidated(isValidated)
            .userInfo(userInfoID)
            .build();

    userRepository.save(newUser);

    log.trace("saveUser - method finished result={}", newUser);
    return newUser;
  }

  @Override
  public void deleteUser(int userID) {
    log.trace("deleteUser - method entered: userID={}", userID);
    userRepository.deleteById(userID);
    log.trace("deleteUser - method finished");
  }

  @Override
  public Optional<User> getUserWithUserInfo(Integer userID) {
    return userRepository.findUserWithUserInfoById(userID);
  }
}
