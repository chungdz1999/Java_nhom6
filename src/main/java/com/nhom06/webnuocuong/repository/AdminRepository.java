package com.nhom06.webnuocuong.repository;

import com.nhom06.webnuocuong.model.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Admin findByTaikhoan(long id);
}
