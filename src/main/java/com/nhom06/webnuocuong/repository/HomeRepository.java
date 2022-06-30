package com.nhom06.webnuocuong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nhom06.webnuocuong.model.DanhMuc;

@Repository
public interface HomeRepository extends JpaRepository<DanhMuc, Integer> {

}
