package ro.ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.domain.UserInfo;

public interface UserInfoRepository  extends JpaRepository<UserInfo,Integer> {
}
