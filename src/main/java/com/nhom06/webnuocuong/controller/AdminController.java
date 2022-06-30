package com.nhom06.webnuocuong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AdminController {

	@GetMapping("/ThongTinCNDH") // Nếu người dùng request tới địa chỉ "/"
	public String ThongTinCNDH() {
		return "admin/ThongTinCNDH"; // Trả về file index.html
	}

	@GetMapping("/ThongTinCNSP") // Nếu người dùng request tới địa chỉ "/"
	public String ThongTinCNSP() {
		return "admin/ThongTinCNSP"; // Trả về file index.html
	}
	@GetMapping("/QuanLy") // Nếu người dùng request tới địa chỉ "/"
	public String quanly() {
		return "admin/quanly"; // Trả về file index.html
	}
	
	/*
	 * @GetMapping("/dangnhapkhachha") // Nếu người dùng request tới địa chỉ "/"
	 * public String dangnhapkhachha() { return "DangNhapKhachHa"; // Trả về file
	 * index.html }
	 */
	

	

	@GetMapping("/CTDH_Admin") // Nếu người dùng request tới địa chỉ "/"
	public String CTDH_Admin() {
		return "admin/CTDH_Admin"; // Trả về file index.html
	}

}
