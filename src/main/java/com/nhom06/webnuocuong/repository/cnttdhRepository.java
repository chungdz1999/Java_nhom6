package com.nhom06.webnuocuong.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom06.webnuocuong.model.*;
@Repository
public interface cnttdhRepository extends JpaRepository<cnttDonHang, Integer> {
	 cnttDonHang findById(int id);
	 List<cnttDonHang> findByDhId(int id);
	
}