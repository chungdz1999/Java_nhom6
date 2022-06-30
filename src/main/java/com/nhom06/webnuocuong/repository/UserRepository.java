package com.nhom06.webnuocuong.repository;

import com.nhom06.webnuocuong.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	User findById(long id);
	
	public User findByEmail(String email ); 
    
    public User findByResetPasswordToken(String token);

}
