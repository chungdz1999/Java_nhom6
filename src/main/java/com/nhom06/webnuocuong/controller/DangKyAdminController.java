package com.nhom06.webnuocuong.controller;

import com.nhom06.webnuocuong.model.User;
import com.nhom06.webnuocuong.repository.UserRepository;
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
@RequestMapping("/dang-ky-admin")
public class DangKyAdminController {

	@Autowired
	private UserRepository userRepository;

	private UserService userService;

	public DangKyAdminController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping
	public String DangKyFormAdmin(Model model) {
		model.addAttribute("user", new UserRegistrationDto());

		return "/admin/DangKyAdmin";
	}

	@PostMapping
	public String DangKyTaiKhoanAdmin(@ModelAttribute("user") UserRegistrationDto registrationDto) {

		if ((registrationDto.getUsername() == "") || (registrationDto.getPassword() == "")) {
			return "redirect:/dang-ky-admin?TKMKTrong";
		}

		User ktuser = userRepository.findByUsername(registrationDto.getUsername());
		if (ktuser != null) {
			return "redirect:/dang-ky-admin?DaCoTK";
		}

		userService.save(registrationDto);

		return "redirect:/dang-ky-admin?DangKyThanhCong";
	}
}
