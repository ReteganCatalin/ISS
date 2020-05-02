package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.iss.CMS.Repository.AbstractRepository;
import ro.ubb.iss.CMS.Repository.UserInfoRepository;
import ro.ubb.iss.CMS.domain.Analysis;
import ro.ubb.iss.CMS.domain.UserInfo;

import java.util.List;
import java.util.Optional;
@Service
public class UserInfoServiceImplementation implements UserInfoService {
  private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImplementation.class);

  @Autowired
  private UserInfoRepository userInfoRepository;
  @Override
  public Optional<UserInfo> findUserInfo(int userInfoID) {
    log.trace("findUserInfo - method entered userInfoID={}", userInfoID);
    Optional<UserInfo> result = userInfoRepository.findById(userInfoID);
    log.trace("findUserInfo - method exit result={}", result);
    return result;
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
