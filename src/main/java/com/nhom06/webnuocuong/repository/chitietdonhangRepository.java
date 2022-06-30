package com.nhom06.webnuocuong.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nhom06.webnuocuong.model.SPTop;
import com.nhom06.webnuocuong.model.chitietdonhang;
import com.nhom06.webnuocuong.model.donhang;
import com.nhom06.webnuocuong.model.giohang;

@Repository
public interface chitietdonhangRepository extends CrudRepository<chitietdonhang, Integer> {
		
	List<chitietdonhang> findByDonhangid(int i);
	chitietdonhang findByChitietdonhangid(int i);

	//Tổng sản phẩm đã bán trong hôm nay
	@Query(value ="SELECT SUM(ctdh.chitietdonhang_soluongsp) as soluongsp from tbl_chitietdonhang as ctdh INNER JOIN tbl_donhang as dh ON ctdh.donhang_id=dh.donhang_id WHERE DAY(ctdh.ngaytao) = DAY(CURRENT_DATE()) and dh.trangthaidonhang_id=4;", nativeQuery = true)
	public String findTongSpDaBan();
	
	//Tổng doanh thu hôm nay
	@Query(value= "SELECT SUM(dh.tongtien) from tbl_chitietdonhang as ctdh INNER JOIN tbl_donhang as dh ON ctdh.donhang_id=dh.donhang_id WHERE DAY(ctdh.ngaytao) = DAY(CURRENT_DATE()) and dh.trangthaidonhang_id=4",nativeQuery = true )
	public String findTongDoanhThu();
	
//	//Tổng số đơn hàng đã giao hôm nay
//		@Query(value ="SELECT COUNT(dh.donhang_id)from tbl_donhang as dh WHERE dh.trangthaidonhang_id = 4 and DAY(dh.ngaytao) = DAY(CURRENT_DATE());", nativeQuery = true)
//		ArrayList<Object>  findTongSoDonHang();
	
	//Tổng đon hàng
	@Query(value="SELECT COUNT(dh.donhang_id)from tbl_donhang as dh WHERE dh.trangthaidonhang_id = 4 and DAY(dh.ngaytao) = DAY(CURRENT_DATE());",nativeQuery = true )
	public String findTongDonHang();

	//Tổng đơn hàng mỗi ngày của tháng này
	@Query(value ="SELECT COUNT(dh.donhang_id) as sodonngay from tbl_donhang as dh"
			+ " WHERE MONTH(dh.ngaytao) = MONTH(CURRENT_DATE()) and dh.trangthaidonhang_id=4 GROUP BY DAY(dh.ngaytao);", nativeQuery = true)
	ArrayList<Object>  findDonHangMoiNgay() ;
	
	//Tổng doanh thu mỗi ngày của tháng này
	@Query(value ="SELECT sum(dh.tongtien) from tbl_donhang as dh "
			+ "WHERE dh.trangthaidonhang_id = 4 and MONTH(dh.ngaytao) = MONTH(CURRENT_DATE()) GROUP BY DAY(dh.ngaytao);", nativeQuery = true)
	ArrayList<Object>  findDoanhThuMoiNgay();
	
	
	//Tổng sản phẩm đã bán mỗi ngày của tháng này
	@Query(value ="SELECT COUNT(ctdh.chitietdonhang_soluongsp) as soluongsp from tbl_chitietdonhang as ctdh INNER JOIN tbl_donhang as dh ON ctdh.donhang_id=dh.donhang_id WHERE MONTH(ctdh.ngaytao) = MONTH(CURRENT_DATE()) and dh.trangthaidonhang_id=4 GROUP BY DAY(ctdh.ngaytao);", nativeQuery = true)
	ArrayList<Object>  findSanPhamDMoiNgay();
	
	//Tổng đơn hàng mỗi ngày của năm 
	@Query(value ="SELECT COUNT(dh.donhang_id) as sodonngay from tbl_donhang as dh"
			+ " WHERE YEAR(dh.ngaytao) = YEAR(CURRENT_DATE()) and dh.trangthaidonhang_id=4 GROUP BY DAY(dh.ngaytao);", nativeQuery = true)
	ArrayList<Object>  findDonHangMoiNam() ;
	
	//Tổng doanh thu mỗi ngày của Năm này
		@Query(value ="SELECT sum(dh.tongtien) from tbl_donhang as dh "
				+ "WHERE dh.trangthaidonhang_id = 4 and YEAR(dh.ngaytao) = YEAR(CURRENT_DATE()) GROUP BY DAY(dh.ngaytao);", nativeQuery = true)
		ArrayList<Object>  findDoanhThuMoiNam();
		
		//Tổng sản phẩm đã bán mỗi ngày của tháng này
		@Query(value ="SELECT COUNT(ctdh.chitietdonhang_soluongsp) as soluongsp from tbl_chitietdonhang as ctdh INNER JOIN tbl_donhang as dh ON ctdh.donhang_id=dh.donhang_id WHERE MONTH(ctdh.ngaytao) = MONTH(CURRENT_DATE()) and dh.trangthaidonhang_id=4 GROUP BY DAY(ctdh.ngaytao);", nativeQuery = true)
		ArrayList<Object>  findSanPhamDMoiNam();	
	
	//Ngày có đơn hàng
	@Query(value ="SELECT DISTINCT dh.ngaytao as soluongsp from tbl_donhang as dh "
			+ "WHERE MONTH(dh.ngaytao) = MONTH(CURRENT_DATE()) and dh.trangthaidonhang_id=4;", nativeQuery = true)
	ArrayList<Object>  findNgay();
	
	//Ngày có đơn hàng theo nam
		@Query(value ="SELECT DISTINCT dh.ngaytao as soluongsp from tbl_donhang as dh "
				+ "WHERE YEAR(dh.ngaytao) = YEAR(CURRENT_DATE()) and dh.trangthaidonhang_id=4;", nativeQuery = true)
		ArrayList<Object>  findNgayTheoNam();
	
	
	//Sản phẩm top
//	@Query("SELECT new com.nhom06.webnuocuong.model.SPTop( SUM(ctdh.ctdhsoluongsp), sp.sanphamten , sp.sanphamgiatien) FROM ((chitietdonhang AS ctdh INNER JOIN donhang AS dh ON dh.donhangid = ctdh.donhangid) INNER JOIN Product AS sp ON sp.sanphamid = ctdh.ctdhidsp) WHERE dh.trangthaidonhangid=4 GROUP BY sp.sanphamid ORDER BY soluong DESC")
//	public List<SPTop> findSanPhamTop();
	
	//Sản phẩm top
	@Query("SELECT new com.nhom06.webnuocuong.model.SPTop( sp.sanphamanhchinh, SUM(ctdh.ctdhsoluongsp)as soluong, sp.sanphamten , sp.sanphamgiatien) FROM chitietdonhang AS ctdh INNER JOIN donhang AS dh ON dh.donhangid = ctdh.donhangid INNER JOIN Product AS sp ON sp.sanphamid = ctdh.ctdhidsp  WHERE dh.trangthaidonhangid=4 GROUP BY sp.sanphamid ORDER BY soluong DESC")
	public List<SPTop> findSanPhamTop();
	
}
