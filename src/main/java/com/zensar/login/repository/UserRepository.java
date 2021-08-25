package com.zensar.login.repository;

import com.zensar.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("Select case when count(u)> 0 then true else false end from User u where u.email like (:email)")
	boolean existsByEmail(@Param("email") String email);
	
	@Query("Select case when count(u)> 0 then true else false end from User u where u.phone like (:phone)")
	boolean existsByPhone(@Param("phone") String phone);
	
	@Query("Select case when count(u)> 0 then true else false end from User u where u.userName like (:username)")
	boolean existsByUserName(@Param("username") String username);
	
	@Query("Select u from User u where u.userName like (:username)")
	User findByUserName(@Param("username") String username);
}
