package com.nhom06.webnuocuong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nhom06.webnuocuong.model.giohang;

@Repository
public interface giohangRepository extends JpaRepository<giohang, Integer> {
		giohang findBySanphamidAndKhachhangid(int sanphamid, int khachhangid);
		List <giohang> findByKhachhangid(int khid);
		
		giohang findByGiohangid (int id);
}
