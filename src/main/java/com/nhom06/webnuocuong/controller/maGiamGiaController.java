package com.nhom06.webnuocuong.controller;


import java.security.Principal;
import java.sql.Date;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhom06.webnuocuong.model.*;
import com.nhom06.webnuocuong.repository.*;


@Controller

public class maGiamGiaController {
	
	@Autowired
	private HomeRepository homeRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private khachhangRepository khRepository;
	@Autowired
	private giohangRepository ghRepository;
	@Autowired
	private KvghRepository kvRepository;
	@Autowired
	private donhangRepository dhRepository;
	@Autowired
	private chitietdonhangRepository ctdhRepository;
	@Autowired
	private maGiamGiaRepository maggRepository;
	
	
	 @GetMapping("/admin/ma-giam-gia") 
	 public String search( Model model, Principal principal ) {
		 
		 model.addAttribute("magg", new maGiamGia()  )  ; 
		 model.addAttribute("localDate", LocalDate.now());
		 model.addAttribute("magiamgia",   maggRepository.findAll() )  ;
	  return "admin/magiamgia";
	  
	  }
	 @PostMapping("/themMagg") 
	 public String themMagg(@ModelAttribute("magg") maGiamGia magg, Model model, Principal principal ) {
		 
		 maGiamGia ma	= maggRepository.findByMagiamgiacode( magg.getMagiamgiacode() );
		 if(ma != null) {
			 return "redirect:/admin/ma-giam-gia?daco";
		 }
		 maggRepository.save(magg) ;
		 
		 model.addAttribute("magg", new maGiamGia()  )  ; 
		 
		
		 return "redirect:/admin/ma-giam-gia?thanhcong";
	  
	  }
	 
	 @PostMapping("/xoaMagg") 
	 public String xoaMagg(@RequestParam("id") int id, Model model, Principal principal ) {
		 
		 maGiamGia ma	= maggRepository.findById( id );
		 if(ma != null) {
			 maggRepository.delete(ma) ;
			 return "redirect:/admin/ma-giam-gia?daxoa";
		 }
		 
		  
		 return "redirect:/admin/ma-giam-gia";
	  
	  }
	 @GetMapping("/suaMagg") 
	 public String suaMagg(@RequestParam("id") int id, Model model, Principal principal ) {
		 
		 maGiamGia ma	= maggRepository.findById( id );
		 if(ma != null) {
			 model.addAttribute("localDate", LocalDate.now());
			 model.addAttribute("magiamgia", ma  )  ; 
			 return "admin/suamagg";
		 }
		 
		  
		 return "redirect:/admin/ma-giam-gia";
	  
	  }
	 
	 @PostMapping("/suaMaggex") 
	 public String suaMaggex(@ModelAttribute("magiamgia") maGiamGia magg, Model model, Principal principal ) {
		 maggRepository.save(magg);
		 
		 return "redirect:/admin/ma-giam-gia?dasua";
	  
	  }
	
	
	
	
}
