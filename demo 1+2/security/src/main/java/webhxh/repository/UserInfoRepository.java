package webhxh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import webhxh.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	
	Optional<UserInfo> findByName(String name);

}
