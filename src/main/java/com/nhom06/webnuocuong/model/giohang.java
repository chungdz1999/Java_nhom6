package com.nhom06.webnuocuong.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "tbl_giohang")
public class giohang {
  @Id
  @Column(name = "giohang_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int giohangid;
  
  @Column(name = "sanpham_id" ,insertable=false, updatable=false,nullable = true)
  private int sanphamid;
  
  @Column(name = "giohang_soluong")
  private int soluong;
  
  @Column(name = "khachhang_id")
  private int khachhangid;
  
  
 
 
	 


  @ManyToOne(optional=false)
  @JoinColumn(name="sanpham_id", referencedColumnName="sanpham_id")
  private Product product;




public Product getProduct() {
	return product;
}



public void setProduct(Product product) {
	this.product = product;
}



public giohang()  {
	  
  }
  
  

public giohang( int sanphamid, int soluong, int khachhangid) {
	
	
	this.sanphamid = sanphamid;
	this.soluong = soluong;
	this.khachhangid = khachhangid;
}







public int getGiohangid() {
	return giohangid;
}



public void setGiohangid(int giohangid) {
	this.giohangid = giohangid;
}



public int getKhachhang_id() {
	return giohangid;
}

public void setKhachhang_id(int khachhang_id) {
	this.giohangid = khachhang_id;
}

public int getSanphamid() {
	return sanphamid;
}

public void setSanphamid(int sanphamid) {
	this.sanphamid = sanphamid;
}

public int getSoluong() {
	return soluong;
}

public void setSoluong(int soluong) {
	this.soluong = soluong;
}

public int getKhachhangid() {
	return khachhangid;
}

public void setKhachhangid(int khachhangid) {
	this.khachhangid = khachhangid;
}
  
  
  // getter setter
  
  
}