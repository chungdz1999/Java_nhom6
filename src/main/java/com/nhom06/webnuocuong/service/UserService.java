package com.nhom06.webnuocuong.service;

import com.nhom06.webnuocuong.model.User;
import com.nhom06.webnuocuong.web.dto.UserRegistrationDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto userRegistrationDto);
	Iterable<User> findAll();
}
