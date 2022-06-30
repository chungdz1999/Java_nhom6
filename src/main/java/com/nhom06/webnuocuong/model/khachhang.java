package com.nhom06.webnuocuong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tbl_khachhang")
public class khachhang {
  @Id
  @Column(name = "khachhang_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int khachhangid;
  
  @Column(name = "taikhoan_id")
  private long taikhoan;
  
  @Column(name = "khachhang_ten")
  private String khachhangten;
  
  
  
  @Column(name = "khachhang_sdt")
  private String khachhangsdt;
  
  @Column(name = "khachhang_anh")
  private String khachhanganh;
  
  @Column(name = "khachhang_diachi")
  private String khachhangdiachi;
  
  @Column(name = "khuvucgh_id")
  private String khuvucghid;
  
  @Column(name = "trangthai_kh")
  private int trangthai;
  

  
  public int getTrangthai() {
	return trangthai;
}
public void setTrangthai(int trangthai) {
	this.trangthai = trangthai;
}
public khachhang() {
	  
  }
public khachhang(long taikhoan, int i ) {
	this.taikhoan = taikhoan;
	this.trangthai = i;
	  
  }



public int getKhachhangid() {
	return khachhangid;
}



public void setKhachhangid(int khachhangid) {
	this.khachhangid = khachhangid;
}



public long getTaikhoan() {
	return taikhoan;
}



public void setTaikhoan(long taikhoan) {
	this.taikhoan = taikhoan;
}



public String getKhachhangten() {
	return khachhangten;
}



public void setKhachhangten(String khachhangten) {
	this.khachhangten = khachhangten;
}




public String getKhachhangsdt() {
	return khachhangsdt;
}



public void setKhachhangsdt(String khachhangsdt) {
	this.khachhangsdt = khachhangsdt;
}



public String getKhachhanganh() {
	return khachhanganh;
}



public void setKhachhanganh(String khachhanganh) {
	this.khachhanganh = khachhanganh;
}



public String getKhachhangdiachi() {
	return khachhangdiachi;
}



public void setKhachhangdiachi(String khachhangdiachi) {
	this.khachhangdiachi = khachhangdiachi;
}



public String getKhuvucghid() {
	return khuvucghid;
}



public void setKhuvucghid(String khuvucghid) {
	this.khuvucghid = khuvucghid;
}
  
  
  

  // getter - setter
  
  
  
  
  
  
  
}