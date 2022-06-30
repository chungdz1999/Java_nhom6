package com.nhom06.webnuocuong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class LoginController {

	@RequestMapping("/dang-nhap-khach-hang")
	public String dangnhap() {
		return "DangNhapKhachHang";
	}
	
	@RequestMapping("/loginAdmin")
	public String dangnhapAdmin() {
		return "DangNhap";
	}
	
	
	
}
