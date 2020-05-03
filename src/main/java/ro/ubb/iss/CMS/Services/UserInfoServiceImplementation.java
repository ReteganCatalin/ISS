package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.iss.CMS.Repository.AbstractRepository;
import ro.ubb.iss.CMS.Repository.UserInfoRepository;
import ro.ubb.iss.CMS.domain.Analysis;
import ro.ubb.iss.CMS.domain.User;
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
    log.trace("findAll - method entered");
    List<UserInfo> result = userInfoRepository.findAll();
    log.trace("findAll - method exit result={}", result);
    return result;
  }

  @Override
  public UserInfo updateUserInfo(
      int userInfoID, String name, String affiliation, String emailAddress, String webpageAddress) {
    log.trace(
            "updateUserInfo - method entered: userInfoID={}, name={}, affiliation={}, emailAddress={}, webpageAddress={}",
            userInfoID,
            name,
            affiliation,
            emailAddress,
            webpageAddress);

    Optional<UserInfo> abstractOptional = userInfoRepository.findById(userInfoID);

    abstractOptional.ifPresent(
            newUserInfo -> {
              newUserInfo.setName(name);
              newUserInfo.setAffiliation(affiliation);
              newUserInfo.setEmailAddress(emailAddress);
              newUserInfo.setWebPageAddress(webpageAddress);
              log.debug("updateUserInfo - updated: newUserInfo={}", newUserInfo);
            });
    log.trace("updateUserInfo - method finished result={}", abstractOptional);
    return abstractOptional.orElse(null);
  }

  @Override
  public UserInfo saveUserInfo(
      String name, String affiliation, String emailAddress, String webpageAddress) {
    log.trace(
            "saveUserInfo - method entered: name={}, emailAddress={}, webpageAddress={}",
            name,
            emailAddress,
            webpageAddress);
    UserInfo newUserInfo =
            UserInfo.builder()
                    .name(name)
                    .emailAddress(emailAddress)
                    .webPageAddress(webpageAddress)
                    .build();

    userInfoRepository.save(newUserInfo);

    log.trace("saveUserInfo - method finished result={}", newUserInfo);
    return newUserInfo;
  }

  @Override
  public void deleteUserInfo(int userInfoID) {
    log.trace("deleteUserInfo - method entered: userInfoID={}", userInfoID);
    userInfoRepository.deleteById(userInfoID);
    log.trace("deleteUserInfo - method finished");
  }
}
