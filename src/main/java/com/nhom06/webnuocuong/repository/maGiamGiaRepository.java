package com.nhom06.webnuocuong.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom06.webnuocuong.model.Kvgh;
import com.nhom06.webnuocuong.model.maGiamGia;
@Repository
public interface maGiamGiaRepository extends JpaRepository<maGiamGia, Integer> {
	 
	maGiamGia findById(int i) ;
	maGiamGia findByMagiamgiacode(String i) ;
}