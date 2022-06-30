package com.nhom06.webnuocuong.repository;

import java.util.List;

import com.nhom06.webnuocuong.model.DanhMuc;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DanhMucRepository extends JpaRepository<DanhMuc, Integer> {

	List<DanhMuc> findAll();
}
