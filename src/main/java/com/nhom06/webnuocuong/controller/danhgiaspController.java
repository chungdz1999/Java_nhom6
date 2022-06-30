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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhom06.webnuocuong.model.*;
import com.nhom06.webnuocuong.repository.*;
import com.nhom06.webnuocuong.uploadimage.FileUploadUtil;


@Controller

public class danhgiaspController {

	@Autowired
	private danhgiaspRepository danhgiaspRepository;
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
	
	
	@GetMapping("/danhgiasp") 
	 public String danhgiasp(Principal principal,@RequestParam("khachhangid") int khid,@RequestParam("ctdhid") int ctdhid, Model model ) {
		 
		 chitietdonhang ct = ctdhRepository.findByChitietdonhangid(ctdhid);
		 
		 
		 model.addAttribute("danhgiasp", new danhgiasp(khid, ct.getChitietdonhangid() , ct.getCtdhidsp() , 5 )  )  ; 
		 
		 String a = "";
			if (principal == null) {
				
			}
			
				 a = principal.getName();
				 User user = userRepository.findByUsername(a);
				 khachhang kh = khRepository.findByTaikhoan(user.getId() );
				 
				 if(kh ==null ) {
					 
				 }
				 model.addAttribute("kh",kh); 
				 
				
				 	return "danhgiasp";
	  
	  }
	
	@PostMapping("/danhgiasp_ex") 
	 public String danhgiasp_ex(HttpServletRequest request,@ModelAttribute("danhgiasp") danhgiasp dgsp, Kvgh Kvgh, Model model,@RequestParam("file") MultipartFile imgFile ) {
		 
		String referer = request.getHeader("Referer");
		
		 dgsp.setNgaytao( java.time.LocalDate.now() );
		 dgsp.setThoigiantao( java.time.LocalTime.now() );
		 
		 if(imgFile.isEmpty()==false) {
				String path = "src/main/resources/static/pictures/danhgiasp";
				String url = "/pictures/danhgiasp/";
				
				try {
					String fileName = StringUtils.cleanPath(imgFile.getOriginalFilename()) ;
					String filePath = path + "/";
					
					dgsp.setAnh1(url+fileName);
					
					FileUploadUtil.saveFile(filePath, fileName, imgFile);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		 
		
		
		 
		 // chitietdonhang cột đánh giá=0 - chưa đánh giá, =1 đã đánh giá
		chitietdonhang ctdh_capnhat_dg = ctdhRepository.findByChitietdonhangid( dgsp.getChitietdonhangid() );
		if(  ctdh_capnhat_dg==null  || (ctdh_capnhat_dg.getDanhgia()  != 0  )   ) {
			return "redirect:/";
		}
		// đánh giá sp xong chuyển cột đánh giá của ctdh =1
		ctdh_capnhat_dg.setDanhgia(1) ;
		ctdhRepository.save( ctdh_capnhat_dg ) ;
		//ket thuc ctdh cột đánh giá
		
		danhgiaspRepository.save(dgsp);
		
		//cập nhật lại điểm trung bình đánh giá sản phẩm
	List<danhgiasp>	 pro= danhgiaspRepository.findBySanphamid(dgsp.getSanphamid() );
	int i=0;
	double review= 0;
	
	for(danhgiasp diem : pro  ) {
		review += diem.getStar() ; 
		i+=1;
		
		
	}
	review = review/i   ;
	review = (double) (Math.round( review*10.0 )/ 10.0 );
	Product sp = productRepository.findById(dgsp.getSanphamid());
	sp.setDanhgia( review);
	productRepository.save(sp)  ;
		//ket thuc điểm trung bình sản phẩm đc cập nhật
		
		
		 return "redirect:/DonHangKhachHang";
	  
	  }
	
	
	
	
	
}
