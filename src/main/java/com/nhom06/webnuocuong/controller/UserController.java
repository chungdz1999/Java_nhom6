package com.nhom06.webnuocuong.controller;

import com.nhom06.webnuocuong.model.User;
import com.nhom06.webnuocuong.repository.UserRepository;
import com.nhom06.webnuocuong.repository.khachhangRepository;
import com.nhom06.webnuocuong.service.UserService;
import com.nhom06.webnuocuong.web.dto.UserRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dang-ky-khach-hang")
public class UserController {

	@Autowired
	private khachhangRepository khRepository;
	@Autowired
	private UserRepository userRepository;

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

//	@ModelAttribute("user")
//	public UserRegistrationDto userRegistrationDto() {
//		return new UserRegistrationDto();
//	}

	@GetMapping
	public String DangKyForm(Model model) {
		model.addAttribute("user", new UserRegistrationDto());

		return "DangKyKhachHang";
	}

	@PostMapping
	public String DangKyTaiKhoan(@ModelAttribute("user") UserRegistrationDto registrationDto) {

		if ((registrationDto.getUsername() == "") || (registrationDto.getPassword() == "")) {
			return "redirect:/dang-ky-khach-hang?TKMKTrong";
		}

		User ktuser = userRepository.findByUsername(registrationDto.getUsername());
		User ktemail = userRepository.findByEmail(registrationDto.getEmail()) ;
		
		if (ktuser != null) {
			return "redirect:/dang-ky-khach-hang?DaCoTK";
		}
		if ( ktemail != null) {
			return "redirect:/dang-ky-khach-hang?DaCoEmail";
		}

		userService.save(registrationDto);

		return "redirect:/dang-nhap-khach-hang?DangKyThanhCong";
	}
}
