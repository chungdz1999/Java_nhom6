package com.nhom06.webnuocuong.controller;

import java.security.Principal;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nhom06.webnuocuong.model.giohang;
import com.nhom06.webnuocuong.model.Kvgh;
import com.nhom06.webnuocuong.model.Product;
import com.nhom06.webnuocuong.model.User;
import com.nhom06.webnuocuong.model.donhang;
import com.nhom06.webnuocuong.model.khachhang;
import com.nhom06.webnuocuong.repository.HomeRepository;
import com.nhom06.webnuocuong.repository.KvghRepository;
import com.nhom06.webnuocuong.repository.ProductRepository;
import com.nhom06.webnuocuong.repository.UserRepository;
import com.nhom06.webnuocuong.repository.giohangRepository;
import com.nhom06.webnuocuong.repository.khachhangRepository;
import com.nhom06.webnuocuong.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private HomeRepository homeRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private khachhangRepository khRepository;
	@Autowired
	private UserService userService;
	
	
	
	@Autowired
	private giohangRepository ghRepository;
	@Autowired
	private KvghRepository kvghRepository;

	public MainController(UserService userService) {
		super();
		this.userService = userService;
	}
	

	/*
	 * @GetMapping("/dangnhap") public String dangnhap() { return "DangNhap"; }
	 */

	@GetMapping("/")
	public String index(Model model, Principal principal,  HttpServletRequest request) {
		request.getSession().setAttribute("product", null);
		
//		int khid=-9;
//		//bat dau lay gio hang cua khach
//		if (principal != null) {
//			String a = "";
//			
//		
//			 a = principal.getName();
//			 User user = userRepository.findByUsername(a);
//			 khachhang kh = khRepository.findByTaikhoan(user.getId() );
//			 
//			 khid= kh.getKhachhangid() ;
//			 
//			 int tongtien=0;
//			 List<giohang> giohanghtml = ghRepository.findByKhachhangid(khid);
//			 model.addAttribute("listgiohang", giohanghtml);
//			 
//			 
//			 model.addAttribute("kh", kh);
//			 model.addAttribute("kvgh", kvghRepository.findAll( ));
//			
//			 
//			
//			
//			 //tinh tong tien trong gio hang
//			 for(giohang giohang : giohanghtml  ) {
//				 
//				 tongtien += giohang.getProduct().getSanphamgiatien()* giohang.getSoluong();
//				 
//			 }
//			 
//			 model.addAttribute("tongtien", tongtien );
//		}
//		//ket thuc lay gio hang cua khach
//		
//		
//		model.addAttribute("giohang", new giohang());
//		model.addAttribute("home", homeRepository.findAll());
//	    model.addAttribute("product", productRepository.findAll());
	    
		return "redirect:/trangchu/page/1";
		
		
	}
	
	
	
	 @GetMapping("/timsanpham/search") 
	 public String search(@RequestParam("s") String s, Model model, HttpServletRequest request ) {
		 request.getSession().setAttribute("product", null);
		 
		 
		 String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		 Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	String sss = pattern.matcher(temp).replaceAll("").replace('đ','d').replace('Đ','D');
		 
		 
		 model.addAttribute("product", productRepository.findBySanphamtenContaining(sss)  )  ; 
	  return "redirect:/timsp/"+sss+"/page/1";
	  
	  }
	 
	
	
	
	 @GetMapping("/timsp/{sss}/page/{pageNumber}") 
	//@GetMapping("/timsanpham/search/page")
	public String showProductPage1(HttpServletRequest request,@PathVariable String sss ,@PathVariable int pageNumber, Model model,
			Principal principal) {
		model.addAttribute("home", homeRepository.findAll());
		String timkiem = "Kết quả tìm: "+sss;
		model.addAttribute("timk", timkiem);
		
//		if ()
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("product");
		int pagesize = 9;
		List<Product> list = (List<Product>) productRepository.findBySanphamtenContaining(sss) ;

		//System.out.println(list.size());
		for(Product s4 : productRepository.findBySanphamtenContaining(sss) )  {
			System.out.println(   s4.getSanphamten()   );
		}
		
		
		
		
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("product", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/timsp/" + sss + "/page/" ;
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("product", pages);
		
		//bat dau lay gio hang cua khach
		int khid=-9;
		if (principal != null) {
			String a = "";
			
		
			 a = principal.getName();
			 User user = userRepository.findByUsername(a);
			 khachhang kh = khRepository.findByTaikhoan(user.getId() );
			 if(kh != null) {
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
		model.addAttribute("dacogh", "1");
		//ket thuc lay gio hang cua khach
		
		
		
		return "index";
	}
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/trangchu/page/{pageNumber}")
	public String showProductPage(HttpServletRequest request, @PathVariable int pageNumber, Model model,
			Principal principal) {
		
		
//		if ()
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("product");
		int pagesize = 9;
		List<Product> list = (List<Product>) productRepository.findAll();

		System.out.println(list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("product", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/trangchu/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("product", pages);
		model.addAttribute("home", homeRepository.findAll());
		
		
		//bat dau lay gio hang cua khach
				int khid=-9;
				if (principal != null) {
					String a = "";
					
				
					 a = principal.getName();
					 User user = userRepository.findByUsername(a);
					 khachhang kh = khRepository.findByTaikhoan(user.getId() );
					 
					 if(kh !=null ) {
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
				model.addAttribute("dacogh", "1");
				//ket thuc lay gio hang cua khach
		
		
		
		
		return "index";
	}
	
	
	@GetMapping("/danhmucsp/{id}")
	public String home3(Model model, HttpServletRequest request, RedirectAttributes redirect, @PathVariable int id) {
		request.getSession().setAttribute("product", null);

		model.addAttribute("product", productRepository.findByDanhmucspid(id)  )  ; 
		
	//	List<product> findAllByOrderBySanphamgiatienDesc();
		//model.addAttribute("product",  productRepository.findAllByOrderBySanphamgiatienDesc() )  ;
		
		return "redirect:/danhmucsp/"+ id + "/page/1";
	}
	
	@GetMapping("/danhmucsp/{id}/page/{pageNumber}")
	public String showProductPage1(HttpServletRequest request, @PathVariable int pageNumber, Model model,
			Principal principal, @PathVariable int id) {
		model.addAttribute("home", homeRepository.findAll());
		
//		if ()
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("product");
		int pagesize = 9;
		List<Product> list = (List<Product>) productRepository.findByDanhmucspid(id);

		System.out.println(list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("product", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/danhmucsp/" + id + "/page/" ;
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("product", pages);
		
		
		//bat dau lay gio hang cua khach
		int khid=-9;
		if (principal != null) {
			String a = "";
			
		
			 a = principal.getName();
			 User user = userRepository.findByUsername(a);
			 khachhang kh = khRepository.findByTaikhoan(user.getId() );
			 
			 if(kh !=null ) {
				 
			 
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
		model.addAttribute("dacogh", "1");
		//ket thuc lay gio hang cua khach
		
		
		
		return "index";
	}
	
	
	@GetMapping("/sapxepspt")
	public String index(Model model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("product", null);

		return "redirect:/sapxepspt/page/1";
	}
	
	@GetMapping("/sapxepspt/page/{pageNumber}")
	public String showProductPage1(HttpServletRequest request, @PathVariable int pageNumber, Model model,
			Principal principal) {
		
		
//		if ()
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("product");
		int pagesize = 9;
		List<Product> list = (List<Product>) productRepository.findAllByOrderBySanphamgiatienAsc();

		System.out.println(list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("product", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/sapxepspt/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("product", pages);
		model.addAttribute("home", homeRepository.findAll());
		
		
		//bat dau lay gio hang cua khach
				int khid=-9;
				if (principal != null) {
					String a = "";
					
				
					 a = principal.getName();
					 User user = userRepository.findByUsername(a);
					 khachhang kh = khRepository.findByTaikhoan(user.getId() );
					 
					 if(kh !=null) {
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
				model.addAttribute("dacogh", "1");
				//ket thuc lay gio hang cua khach
		
		
		
		
		return "index";
	}
	
	
	

	@GetMapping("/sapxepspg")
	public String index1(Model model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("product", null);

		return "redirect:/sapxepspg/page/1";
	}
	
	@GetMapping("/sapxepspg/page/{pageNumber}")
	public String showProductPage2(HttpServletRequest request, @PathVariable int pageNumber, Model model,
			Principal principal) {
		
		
//		if ()
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("product");
		int pagesize = 9;
		List<Product> list = (List<Product>) productRepository.findAllByOrderBySanphamgiatienDesc();

		System.out.println(list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("product", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/sapxepspg/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("product", pages);
		model.addAttribute("home", homeRepository.findAll());
		
		
		//bat dau lay gio hang cua khach
				int khid=-9;
				if (principal != null) {
					String a = "";
					
				
					 a = principal.getName();
					 User user = userRepository.findByUsername(a);
					 khachhang kh = khRepository.findByTaikhoan(user.getId() );
					 
					 if(kh != null) {
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
				model.addAttribute("dacogh", "1");
				//ket thuc lay gio hang cua khach
		
		
		
		
		return "index";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
