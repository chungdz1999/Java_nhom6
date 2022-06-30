package com.nhom06.webnuocuong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom06.webnuocuong.model.Product;
import com.nhom06.webnuocuong.model.donhang;
import com.nhom06.webnuocuong.model.giohang;

@Repository
public interface donhangRepository extends JpaRepository<donhang, Integer> {
	List<donhang> findByKhachhangidOrderByDonhangidDesc(int id);
	List<donhang> findByTrangthaidonhangidAndKhachhangidOrderByDonhangidDesc(int id, int khid);
	donhang findByDonhangid(int id) ;
	
	
	
	
	int countByKhachhangid (int khid);
	int countByTrangthaidonhangidAndKhachhangid (int a, int b);
	
	int countByDonhangid (int id);
	int countByTrangthaidonhangid (int a);
	List<donhang> findByTrangthaidonhangidOrderByDonhangidDesc(int trangthaidonhangid);
	List<donhang> findAllByOrderByDonhangidDesc();
}
