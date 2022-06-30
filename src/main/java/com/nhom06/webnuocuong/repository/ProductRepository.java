package com.nhom06.webnuocuong.repository;

import java.util.List;

import com.nhom06.webnuocuong.model.Product;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findAll();

	Product findById(int sanphamid);
	
	List<Product> findBySanphamtenContaining(String ten);

	List<Product> findByDanhmucspid(int id);

	List<Product> findAllByOrderBySanphamgiatienDesc();

	List<Product> findAllByOrderBySanphamgiatienAsc();

}
