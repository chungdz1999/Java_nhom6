package com.nhom06.webnuocuong.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.nhom06.webnuocuong.model.Admin;
import com.nhom06.webnuocuong.model.Role;
import com.nhom06.webnuocuong.model.User;
import com.nhom06.webnuocuong.model.khachhang;
import com.nhom06.webnuocuong.repository.AdminRepository;
import com.nhom06.webnuocuong.repository.UserRepository;
import com.nhom06.webnuocuong.repository.khachhangRepository;
import com.nhom06.webnuocuong.repository.roleRepository;
import com.nhom06.webnuocuong.web.dto.UserRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIpml implements UserService {

	@Autowired
	private khachhangRepository khRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private roleRepository roRepository;

	private UserRepository userRepository;

	private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	public UserServiceIpml(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;

	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {

		User ktuser = userRepository.findByUsername(registrationDto.getUsername());
		
		if (ktuser == null) {
			if (registrationDto.getQuyen().equals("ROLE_KHACHHANG")) {

				Role rolee = roRepository.findByName(registrationDto.getQuyen());

				User user = new User(registrationDto.getUsername(), encoder.encode(registrationDto.getPassword()),
						Arrays.asList(rolee), registrationDto.getEmail());

				userRepository.save(user);
				userRepository.flush();
				khachhang kh = new khachhang(user.getId(), 1);
				khRepository.save(kh);
				
				
			} else if (registrationDto.getQuyen().equals("ROLE_NHANVIEN")) {

				Role rolee = roRepository.findByName(registrationDto.getQuyen());

				User user = new User(registrationDto.getUsername(), encoder.encode(registrationDto.getPassword()),
						Arrays.asList(rolee),  registrationDto.getEmail()  );

				userRepository.save(user);
				userRepository.flush();
				Admin ad = new Admin(user.getId(), 1  );
				adminRepository.save(ad);
			}

		}

		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Khong tim thay user");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

	}

}
