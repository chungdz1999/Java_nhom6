package com.nhom06.webnuocuong.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom06.webnuocuong.model.Kvgh;
@Repository
public interface KvghRepository extends JpaRepository<Kvgh, Integer> {
	 //Optional<Kvgh> findById(int id);
	 Kvgh findById(int id);
	
}