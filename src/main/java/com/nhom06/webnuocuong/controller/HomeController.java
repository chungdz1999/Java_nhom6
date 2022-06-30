package com.nhom06.webnuocuong.controller;

import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import javax.persistence.criteria.Path;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.*;

import  com.nhom06.webnuocuong.model.*;
import com.nhom06.webnuocuong.repository.*;
import com.nhom06.webnuocuong.repository.KvghRepository;
import com.nhom06.webnuocuong.repository.ProductRepository;
import com.nhom06.webnuocuong.repository.UserRepository;
import com.nhom06.webnuocuong.repository.chitietdonhangRepository;
import com.nhom06.webnuocuong.repository.danhgiaspRepository;
import com.nhom06.webnuocuong.repository.giohangRepository;
import com.nhom06.webnuocuong.repository.khachhangRepository;
import com.nhom06.webnuocuong.service.UserService;
import com.nhom06.webnuocuong.uploadimage.FileUploadUtil;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HomeRepository homeRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private chitietdonhangRepository ctdhRepository;
	
	@Autowired
	private cnttdhRepository cnttdhRepository;
	
	@Autowired
	private khachhangRepository khRepository;
	@Autowired
	private giohangRepository ghRepository;
	
	@Autowired
	private KvghRepository kvghRepository;
	
	@Autowired
	private UserRepository UserRepository;
	
	@Autowired
	private danhgiaspRepository dgspRepository;
	
	private UserService userService;
	private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	public HomeController(UserService userService) {
		super();
		this.userService = userService;
	}
	

	
	@GetMapping("/timsanpham/search/{abc}")
	public String search2(@PathVariable String abc, Model model) {
		
		model.addAttribute("product", productRepository.findBySanphamtenContaining(abc));
		
		return "index";
		
	}
	
	
	


	


	@GetMapping("/ThanhToan") // Nếu người dùng request tới địa chỉ "/"
	public String ThanhToan() {

		return "ThanhToan"; // Trả về file index.html
	}

	@GetMapping("/ThongTinKhachHang") // Nếu người dùng request tới địa chỉ "/"
	public String ThongTinKhachHang(Model model, Principal principal) {
		
		String a = "";
		
		if (principal == null) {
			return "redirect:/";
			
		}
			 a = principal.getName();
			 User user = userRepository.findByUsername(a);
			 khachhang kh = khRepository.findByTaikhoan(user.getId() );
			 
			 model.addAttribute("taikhoan", user);
			// model.addAttribute("kvgh", KvghRepository.findAll());
			 model.addAttribute("kh", kh);
			 model.addAttribute("kvgh", kvghRepository.findAll( ));
			 
			 
			 int khid=-9;
				//bat dau lay gio hang cua khach
				if (principal != null) {
					
					 khid= kh.getKhachhangid() ;
					 
					 int tongtien=0;
					 List<giohang> giohanghtml = ghRepository.findByKhachhangid(khid);
					 model.addAttribute("listgiohang", giohanghtml);
					 
					 
					 model.addAttribute("kh", kh);
					 model.addAttribute("kvgh", kvghRepository.findAll( ));
					
					 
					
					
					 //tinh tong tien trong gio hang
					 for(giohang giohang : giohanghtml  ) {
						 
						 tongtien += giohang.getProduct().getSanphamgiatien()* giohang.getSoluong();
						 
					 }
					 model.addAttribute("tongtien", tongtien );
				}
				//ket thuc lay gio hang cua khach
			 
			 
			 
		return "ThongTinKhachHang"; // Trả về file index.html
	}
	
	@RequestMapping("/updateKh")
	  public String doUpdateKvgh(@ModelAttribute("kh") khachhang kh, Model model, @RequestParam("file") MultipartFile imgFile) {
		
		
		
		
		if(imgFile.isEmpty()==false) {
			String path = "src/main/resources/static/pictures/khachhang";
			String url = "/pictures/khachhang/";
			
			try {
				String fileName = kh.getKhachhangid()+StringUtils.cleanPath(imgFile.getOriginalFilename()) ;
				String filePath = path + "/";
				kh.setKhachhanganh(url+fileName);
				FileUploadUtil.saveFile(filePath, fileName, imgFile);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		
			
			
		
	    khRepository.save(kh);
	    model.addAttribute("kh",kh);
	    //model.addAttribute("ThongTinKhachHang");
	    return "redirect:/ThongTinKhachHang";
	  }
	
	@RequestMapping("/updateKh2")
	  public String doUpdateKvgh2(@ModelAttribute("kh") khachhang kh, Model model) {
		

		
	    khRepository.save(kh);
	    model.addAttribute("kh",kh);
	    //model.addAttribute("ThongTinKhachHang");
	    return "redirect:/GioHang";
	  }
	
	@RequestMapping("/doimk") // Nếu người dùng request tới địa chỉ "/"
	public String doimk(Model model, Principal principal, @RequestParam("password") String pass, @RequestParam("newpass") String newpass,  @RequestParam("cpass") String cpass ) {
		String a = "";

		if (principal == null) {
			return "redirect:/";

		}
		

		a = principal.getName();
		User user = userRepository.findByUsername(a);
		if(encoder.matches(pass,user.getPassword())) {
			// process
			if (newpass.equals(cpass)) {
				user.setPassword(encoder.encode(newpass));
				UserRepository.save(user);
			} else {
				return "redirect:/ThongTinKhachHang?tt2";
			}
		} else
			return "redirect:/ThongTinKhachHang?tt3";
		//model.addAttribute("tt",tt);
		return "redirect:/ThongTinKhachHang?tt1"; // Trả về file index.html
	}
	
	

	

	@GetMapping("/ChiTietDonHangKhachHang") // Nếu người dùng request tới địa chỉ "/"
	public String ChiTietDonHangKhachHang() {

		return "ChiTietDonHangKhachHang"; // Trả về file index.html
	}

	@GetMapping("/admin") 
	public String admin(Model model) {
		
	String spDaBan = ctdhRepository.findTongSpDaBan();
	String doanhThuThang = ctdhRepository.findTongDoanhThu();
	String donHang = ctdhRepository.findTongDonHang();
	
	if(spDaBan == null) {
		 spDaBan = "0";
	}
	

	if(doanhThuThang == null) {
		 doanhThuThang = "0";
	}
	

	if(donHang == null) {
		 spDaBan = "0";
	}
	
	
		
	
	String thoiGian = "Theo tháng";
	
	ArrayList<Object> donHangMoiNgay = ctdhRepository.findDonHangMoiNgay();
	ArrayList<Object> doanhThuMoiNgay = ctdhRepository.findDoanhThuMoiNgay();
	ArrayList<Object> sanPhamMoiNgay = ctdhRepository.findSanPhamDMoiNgay();
	ArrayList<Object> ngay = ctdhRepository.findNgay();
	List<SPTop> spTop= ctdhRepository.findSanPhamTop();

		model.addAttribute("thoigian",thoiGian);
		model.addAttribute("spdaban",spDaBan);
		model.addAttribute("doanhthuthang",doanhThuThang);
		model.addAttribute("donhang",donHang);
		model.addAttribute("donhangmoingay",donHangMoiNgay);
		model.addAttribute("sanphammoingay", sanPhamMoiNgay);
		model.addAttribute("doanhthumoingay", doanhThuMoiNgay);
		model.addAttribute("ngay",ngay);
		model.addAttribute("sptop",spTop);
		

		return "admin/index_admin"; // Trả về file index.html
	}
	
	@GetMapping("/admin/bieudotheonam") 
	public String adminBieuDoTheoNam(Model model) {
		String spDaBan = ctdhRepository.findTongSpDaBan();
		String doanhThuThang = ctdhRepository.findTongDoanhThu();
		String donHang = ctdhRepository.findTongDonHang();
		
		if(spDaBan == null) {
			 spDaBan = "0";
		}
		if(doanhThuThang == null) {
			 doanhThuThang = "0";
		}
		if(donHang == null) {
			 spDaBan = "0";
		}
	String thoiGian = "Theo năm";

	ArrayList<Object> donHangMoiNgay = ctdhRepository.findDonHangMoiNam();
	ArrayList<Object> doanhThuMoiNgay = ctdhRepository.findDoanhThuMoiNam();
	ArrayList<Object> sanPhamMoiNgay = ctdhRepository.findSanPhamDMoiNam();
	ArrayList<Object> ngay = ctdhRepository.findNgayTheoNam();
	List<SPTop> spTop= ctdhRepository.findSanPhamTop();

		model.addAttribute("thoigian",thoiGian);
		model.addAttribute("spdaban",spDaBan);
		model.addAttribute("doanhthuthang",doanhThuThang);
		model.addAttribute("donhang",donHang);
		model.addAttribute("donhangmoingay",donHangMoiNgay);
		model.addAttribute("sanphammoingay", sanPhamMoiNgay);
		model.addAttribute("doanhthumoingay", doanhThuMoiNgay);
		model.addAttribute("ngay",ngay);
		model.addAttribute("sptop",spTop);

		return "admin/index_admin";
	}
	
	@GetMapping("/ChiTietSanPham/{id}") // Nếu người dùng request tới địa chỉ "/"
	public String ChiTietSanPham(Principal principal,Model model,   @PathVariable int id) {
		
		//bat dau lay gio hang cua khach
				
				if (principal != null) {
					String a = "";
					
				
					 a = principal.getName();
					 User user = userRepository.findByUsername(a);
					 khachhang kh = khRepository.findByTaikhoan(user.getId() );
					 if(kh !=null ) {
						 
						 int khid=-9;
					 khid= kh.getKhachhangid() ;
					 
					 int tongtien=0;
					 List<giohang> giohanghtml = ghRepository.findByKhachhangid(khid);
					 model.addAttribute("listgiohang", giohanghtml);
					 
					 model.addAttribute("kh", kh);
					 
					 
					 //tinh tong tien trong gio hang
					 for(giohang giohang : giohanghtml  ) {
						 
						 tongtien += giohang.getProduct().getSanphamgiatien()* giohang.getSoluong();
						 
					 }
					 
					 model.addAttribute("tongtien", tongtien );
					 }
					 
				}
				model.addAttribute("giohang", new giohang());
				model.addAttribute("danhgiasp",  dgspRepository.findBySanphamid(id)    );
				model.addAttribute("danhsachKH", khRepository.findAll() );
				//ket thuc lay gio hang cua khach
		
		
		
		model.addAttribute("ctsp", productRepository.findById(id)  )  ; 
		return "ChiTietSanPham"; // Trả về file index.html
	}
	
	@GetMapping("/cnttDonHang")
	  public String cnttDonHang(Model model) {
	    model.addAttribute("cnttdh", cnttdhRepository.findAll());
	    model.addAttribute("listAdmin", adminRepository.findAll());
	    return "admin/cnttDonHang";
	  }
	@GetMapping("/cnttDonHang/{id}")
	  public String cnttDonHang(@PathVariable int id,Model model) {
	    model.addAttribute("cnttdh", cnttdhRepository.findByDhId(id));
	    model.addAttribute("listAdmin", adminRepository.findAll());
	    return "admin/cnttDonHang";
	  }
	
}