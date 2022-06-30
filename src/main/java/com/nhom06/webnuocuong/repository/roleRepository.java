package com.nhom06.webnuocuong.repository;

import com.nhom06.webnuocuong.model.Role;
import com.nhom06.webnuocuong.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface roleRepository extends JpaRepository<Role, Long> {

	Role findByName(String username);
	
	

}
