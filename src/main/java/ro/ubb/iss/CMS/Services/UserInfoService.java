package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoService {
  Optional<UserInfo> findUserInfo(int userInfoID);

  List<UserInfo> findAll();

  UserInfo updateUserInfo(
      int userInfoID, String name, String affiliation, String emailAddress, String webpageAddress);

  UserInfo saveUserInfo(
      String name, String affiliation, String emailAddress, String webpageAddress);

  void deleteUserInfo(int userInfoID);
}
