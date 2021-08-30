package jp.co.internous.ecsite.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.internous.ecsite.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByUserNameAndPassword(String userName, String password);
	
	//userName を探すために追加する。
	@Query(value="SELECT count(id) FROM user WHERE user_name = :userName",
			nativeQuery=true)
	int findCountByUserName(@Param("userName") String userName);
}
