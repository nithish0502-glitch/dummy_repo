package com.examly.springappfeedback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springappfeedback.model.User;

import org.springframework.data.repository.query.Param;
  
@Repository
public interface UserRepo  extends JpaRepository<User,Integer>{
    @Query("SELECT u FROM User u WHERE u.email = :email  or u.username = :email")
	    Optional<User> findByEmail(@Param("email") String email);
	 
	 User findByUsername(String username);
	 boolean existsByEmail(String string);


}
