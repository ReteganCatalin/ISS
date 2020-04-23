package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.UserInfo;

import java.util.List;
import java.util.Optional;

public class UserInfoServiceImplementation implements UserInfoService {
  @Override
  public Optional<UserInfo> findUserInfo(int userInfoID) {
    return Optional.empty();
  }

  @Override
  public List<UserInfo> findAll() {
    return null;
  }

  @Override
  public UserInfo updateUserInfo(
      int userInfoID, String name, String affiliation, String emailAddress, String webpageAddress) {
    return null;
  }

  @Override
  public UserInfo saveUserInfo(
      String name, String affiliation, String emailAddress, String webpageAddress) {
    return null;
  }

  @Override
  public void deleteUserInfo(int userInfoID) {}
}
