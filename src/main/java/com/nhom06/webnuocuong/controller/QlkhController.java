package com.nhom06.webnuocuong.controller;




import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nhom06.webnuocuong.model.*;
import com.nhom06.webnuocuong.repository.*;
import com.nhom06.webnuocuong.service.UserService;
@Controller
public class QlkhController {
  @Autowired
  private khachhangRepository khachhangRepository;
  
  @Autowired
  private UserRepository UserRepository;
  
 
  
  @RequestMapping("/TaiKhoanKhachHang")
  public String listTkkh(Model model) {
    model.addAttribute("listKh", khachhangRepository.findAll());
    model.addAttribute("listTk", UserRepository.findAll());
    
    return "admin/TaiKhoanKhachHang";
  }
  
  @RequestMapping("/doitt/{id}")
  public String doitt(@PathVariable long id, Model model) {
    
    khachhang kh = khachhangRepository.findByTaikhoan(id);
    if(kh.getTrangthai()==1)
    	kh.setTrangthai(0);
    else
    	kh.setTrangthai(1);
    khachhangRepository.save(kh);
    return  "redirect:/TaiKhoanKhachHang";
  }
  @RequestMapping("/rsmk/{id}")
  public String rsmk(@PathVariable long id, Model model) {
    
    User user = UserRepository.findById(id);
    
    
   user.setPassword("{bcrypt}$2a$10$o9nBCi3UuFyxdNHBm4eRxObn6CYmjkpyHZDXV7r.WpXA.BoS7sFgO");
    
    UserRepository.save(user);
    return  "redirect:/TaiKhoanKhachHang";
  }
}
  
  
  