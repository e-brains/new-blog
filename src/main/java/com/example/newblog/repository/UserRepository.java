package com.example.newblog.repository;

import com.example.newblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// DAO
// 자동으로 bean등록이 된다.
// @Repository // 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer>{

	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);

	// 로그인을 위한 함수를 만든다.
	// JPA Naming 쿼리
	// select * from user where username=?1 and password =?2;
	User findByUsernameAndPassword(String username, String password);


}

/*********************************
새로운 함수를 만드는 2가지 방법
********************************/
// JPA Naming 쿼리
// SELECT * FROM user WHERE username = ?1 AND password = ?2;
//User findByUsernameAndPassword(String username, String password);

//	@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);
