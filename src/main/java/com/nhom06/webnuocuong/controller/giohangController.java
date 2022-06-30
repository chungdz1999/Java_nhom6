package com.nhom06.webnuocuong.controller;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;

import com.nhom06.webnuocuong.model.*;
import com.nhom06.webnuocuong.repository.*;


@Controller

public class giohangController {
	
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
	private KvghRepository kvghRepository;
	@Autowired
	private maGiamGiaRepository mggRepository;
	
	
	@GetMapping("/GioHang") 
	public String GioHang(Model model,Principal principal) {
		
		String a = "";
		int khid=-9;
		if (principal == null) {
			 return "GioHang"; 
			
		}
			 a = principal.getName();
			 User user = userRepository.findByUsername(a);
			 khachhang kh = khRepository.findByTaikhoan(user.getId() );
			 
			 
		if( kh != null ) {
				 
			 
			 khid= kh.getKhachhangid() ;
			 
			 int tongtien=0;
			 List<giohang> giohanghtml = ghRepository.findByKhachhangid(khid);
			 model.addAttribute("listgiohang", giohanghtml);
			 model.addAttribute("listsanpham", productRepository.findAll());
			 model.addAttribute("taikhoan", user);
			 model.addAttribute("kh", kh);
			 model.addAttribute("user", user);
			 model.addAttribute("kvgh", kvghRepository.findAll( ));
			 model.addAttribute("donhang", new donhang());
			 
			 
			 if(kh.getKhuvucghid()==null) {
				 
				 model.addAttribute("kvgh_kh", new Kvgh(0,"...",0,0,0)    );
			 }else {
				 int kvghid_kh = Integer.parseInt(kh.getKhuvucghid());
				 model.addAttribute("kvgh_kh", kvghRepository.findById(kvghid_kh )    );
			 }
			
			 //tinh tong tien trong gio hang
			 for(giohang giohang : giohanghtml  ) {
				 
				 tongtien += giohang.getProduct().getSanphamgiatien()* giohang.getSoluong();
				 
			 }
			 model.addAttribute("tongtien", tongtien );
			 model.addAttribute("magg", new maGiamGia() );
			 model.addAttribute("giamgia", 0);
			 
		}
			 
			
		
		
		return "GioHang"; 
	}
	
	
	@PostMapping("/kiemtraMagg") 
	public String kiemtraMagg(@RequestParam("code") String code,Model model,Principal principal) throws ParseException {
		
		String a = "";
		int khid=-9;
		if (principal == null) {
			 return "GioHang"; 
			
		}
			 a = principal.getName();
			 User user = userRepository.findByUsername(a);
			 khachhang kh = khRepository.findByTaikhoan(user.getId() );
			 
			 
		if( kh != null ) {
			
			 khid= kh.getKhachhangid() ;
			 
			 int tongtien=0;
			 List<giohang> giohanghtml = ghRepository.findByKhachhangid(khid);
			 model.addAttribute("listgiohang", giohanghtml);
			 model.addAttribute("listsanpham", productRepository.findAll());
			 model.addAttribute("taikhoan", user);
			 model.addAttribute("kh", kh);
			 model.addAttribute("user", user);
			 model.addAttribute("kvgh", kvghRepository.findAll( ));
			 model.addAttribute("donhang", new donhang());
			 
			 
			 if(kh.getKhuvucghid()==null) {
				 
				 model.addAttribute("kvgh_kh", new Kvgh(0,"...",0,0,0)    );
			 }else {
				 int kvghid_kh = Integer.parseInt(kh.getKhuvucghid());
				 model.addAttribute("kvgh_kh", kvghRepository.findById(kvghid_kh )    );
			 }
			
			 //tinh tong tien trong gio hang
			 for(giohang giohang : giohanghtml  ) {
				 
				 tongtien += giohang.getProduct().getSanphamgiatien()* giohang.getSoluong();
				 
			 }
			 model.addAttribute("tongtien", tongtien );
			 model.addAttribute("magg", new maGiamGia() );
			 
			 maGiamGia mgg = mggRepository.findByMagiamgiacode(code) ;
			 
			// LocalDate datenow = java.time.LocalDate.now();
			 long millis=System.currentTimeMillis();
			 java.sql.Date datenow  =new java.sql.Date(millis);
			 int giamgia=0;
			 int makodung=0;
			 int mahethan=0;
			  
			 
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(mgg != null) {
					
					Date date_magg = sdf.parse(mgg.getNgayhethan());
					//LocalDate date_ma = date_magg;
					//Date datenow2 = sdf.parse(   datenow );
					
					if( ( mgg.getMagiamgiasoluong() >0 ) && ( datenow.compareTo(date_magg)  <=0 )   ) {
						 giamgia =(tongtien* mgg.getMagiamgiaphantram() ) /100 ;
						model.addAttribute("giamgia", giamgia  );
					}else {
						model.addAttribute("mahethan", 1 );
						model.addAttribute("giamgia", 0 );
					}
					
					
				}else {
					model.addAttribute("makodung", 1 );
					model.addAttribute("giamgia", 0 );
				}
				
			 
		}
			 
			
		
		
		return "GioHang"; 
	}
	
	
	@PostMapping("/ThemGioHang")
	public String themgiohang(@ModelAttribute("giohang") giohang gioh, Model model,Principal principal ,HttpServletRequest request ) {
		
		String referer = request.getHeader("Referer");
		
		String a = "";
		int khid=-9;
		int spid = gioh.getSanphamid();
		int sl = gioh.getSoluong();
		if (principal == null) {
			return "redirect:/";
		}
			 a = principal.getName();
			 User user = userRepository.findByUsername(a);
			 khachhang kh = khRepository.findByTaikhoan(user.getId() );
			 
	if( kh != null ) {
			 khid= kh.getKhachhangid() ;
			 
			giohang kiemtragh = ghRepository.findBySanphamidAndKhachhangid(spid, khid) ;
			
			if(kiemtragh != null) {
				//model.addAttribute("dacogh", "0");
				
				kiemtragh.setSoluong( sl + kiemtragh.getSoluong());
				ghRepository.save( kiemtragh   ) ;
				return "redirect:"+ referer   ;
				
			}
			
		
		
		giohang gh = new giohang(spid,sl,khid);
		Product pro = productRepository.findById(spid);
		gh.setProduct(pro);
			ghRepository.save(gh) ;
	}
		//ghRepository.save(gh) ;
		//model.addAttribute("dacogh", "1");
		return "redirect:"+ referer    ;
		
	}

	
	@PostMapping("/xoagiohang")
	  public String xoagiohang(@RequestParam("giohangid") int id, Model model, Principal principal,HttpServletRequest request) {
		
		String a = "";
		int khid=-9;
		
		
		
		if (principal == null) {
			return "redirect:/";
		}
		
		
			 a = principal.getName();
			 User user = userRepository.findByUsername(a);
			 khachhang kh = khRepository.findByTaikhoan(user.getId() );
			 if( kh != null ) {
				 
			 
			 giohang ghkhid = ghRepository.findByGiohangid(id);
			 khid= kh.getKhachhangid() ;
			 
			 if(ghkhid.getKhachhangid() == khid) {
				 ghRepository.deleteById(id);
			 }
			 
			 }
	   
	    model.addAttribute("listgiohang", ghRepository.findAll());
	  //  return "GioHang";
	    String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	  }
	
	@PostMapping("/capnhatgiohang")
	  public String capnhatgiohang(@RequestParam("giohangid") int id, @RequestParam("soluong") int soluong,Model model, Principal principal) {
		
		String a = "";
		int khid=-9;
		
		if (principal == null) {
			return "redirect:/";
		}
		
			 a = principal.getName();
			 User user = userRepository.findByUsername(a);
			 khachhang kh = khRepository.findByTaikhoan(user.getId() );

			 if(kh != null) {
			 giohang ghkhid = ghRepository.findByGiohangid(id);
			 khid= kh.getKhachhangid() ;
			 
				 if(ghkhid.getKhachhangid() == khid) {
					 ghkhid.setSoluong(soluong);
					 ghRepository.save(ghkhid);
					 
				 }
			 }
			 
	    model.addAttribute("listgiohang", ghRepository.findAll());
	  //  return "GioHang";
	    
	    return "redirect:/GioHang";
	  }
	
	
	
}
