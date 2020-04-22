package ro.ubb.iss.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.iss.CMS.domain.UserInfo;

public interface UserInfoRepository  extends JpaRepository<UserInfo,Integer> {
}
