package com.nhom06.webnuocuong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error403")
public class ErrorController {

	@GetMapping
	public String dangnhap() {
		return "error403";
	}
}
