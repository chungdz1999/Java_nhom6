package com.nhom06.webnuocuong.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.nhom06.webnuocuong.model.DanhMuc;
import com.nhom06.webnuocuong.model.Product;
import com.nhom06.webnuocuong.repository.DanhMucRepository;
import com.nhom06.webnuocuong.repository.ProductRepository;
import com.nhom06.webnuocuong.uploadimage.FileUploadUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SanPhamController {

	private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private DanhMucRepository danhMucRepository;

	public SanPhamController(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@GetMapping("/ThemSanPham")
	public String ThemSanPhamForm(Model model) {

		List<DanhMuc> danhmuc = danhMucRepository.findAll();

		model.addAttribute("danhmuc", danhmuc);
		model.addAttribute("sanpham", new Product());

		return "admin/sanpham/ThemSanPham";
	}

	@PostMapping("/ThemSanPham")
	public String ThemSanPham(@ModelAttribute("sanpham") Product pro, @RequestParam("image") MultipartFile imgFile)
			throws IOException {

		if(imgFile.isEmpty()==false) {
		String fileName = StringUtils.cleanPath(imgFile.getOriginalFilename());

		double randomDouble = Math.random();
		double randomDouble2 = Math.random();
		randomDouble = randomDouble * 10000 + 1;
		randomDouble2 = randomDouble2 * 100 + 1;
		double randomDouble3 = randomDouble + randomDouble2;
		int randomInt = (int) randomDouble3;

		fileName = randomInt + fileName;

		pro.setSanphamanhchinh("/pictures/sanpham/" + fileName);

		 productRepository.save(pro);

		String uploadDir = "src/main/resources/static/pictures/sanpham";

		FileUploadUtil.saveFile(uploadDir, fileName, imgFile);
		}
		
		 productRepository.save(pro);
		return "redirect:/ThemSanPham?ThanhCong";
		
	}

	@GetMapping("/DanhSachSP_admin")
	public String DanhSachSanPham(Model model) {
		List<Product> product = productRepository.findAll();
		List<DanhMuc> danhMucs = danhMucRepository.findAll();
		model.addAttribute("sanpham", product);
		model.addAttribute("danhmuc", danhMucs);
		return "admin/sanpham/DanhSachSP_admin";
	}

	@GetMapping("sua-san-pham/{id}")
	public String SuaSanPham(@PathVariable("id") int id, Model model) {

		Product product = (productRepository.findById(id));
		List<DanhMuc> danhMucs = danhMucRepository.findAll();

		model.addAttribute("sanpham", product);
		model.addAttribute("sanphamsua", new Product());
		model.addAttribute("danhmuc", danhMucs);
		return "admin/sanpham/SuaSanPham";
	}

	@PostMapping("/sua-san-pham")
	public String SuaSP(@ModelAttribute("sanpham") Product pro, @RequestParam("image") MultipartFile imgFile)
			throws IOException {
		
		int id = pro.getSanphamid();
		
		Product spanh = productRepository.findById(id);

		String fileName = StringUtils.cleanPath(imgFile.getOriginalFilename());
		if (fileName == "") {

			pro.setSanphamanhchinh(spanh.getSanphamanhchinh());
			productRepository.save(pro);
			return "redirect:/sua-san-pham/"+id+"/?ThanhCong";
		} else {
			

			if(imgFile.isEmpty()==false) {
				

				double randomDouble = Math.random();
				double randomDouble2 = Math.random();
				randomDouble = randomDouble * 10000 + 1;
				randomDouble2 = randomDouble2 * 100 + 1;
				double randomDouble3 = randomDouble + randomDouble2;
				int randomInt = (int) randomDouble3;

				fileName = randomInt + fileName;

				pro.setSanphamanhchinh("/pictures/sanpham/" + fileName);

				 productRepository.save(pro);

				String uploadDir = "src/main/resources/static/pictures/sanpham";

				FileUploadUtil.saveFile(uploadDir, fileName, imgFile);
				
				return "redirect:/sua-san-pham/"+id+"/?ThanhCong";
				}
				
		}
		
		return "redirect:/sua-san-pham/"+id+"/?ThanhCong";
		
		
	}

}
