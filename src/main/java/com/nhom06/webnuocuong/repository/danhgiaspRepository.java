package com.nhom06.webnuocuong.repository;

import java.util.List;

import com.nhom06.webnuocuong.model.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface danhgiaspRepository extends JpaRepository<danhgiasp, Integer> {

	List<danhgiasp> findBySanphamid(int id);
}
