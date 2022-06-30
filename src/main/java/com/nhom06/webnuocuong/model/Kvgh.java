package com.nhom06.webnuocuong.model;





import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tbl_khuvucgh")
public class Kvgh {
  @Id
  @Column(name = "khuvucgh_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "khuvucgh_ten")
  private String name;
  @Column(name = "khuvucgh_giatien")
  private int gia;
  @Column(name = "thoigiangiao")
  private int thoigian;
  @Column(name = "khuvucgh_trangthai")
  private int trangthai;
  public Kvgh() {
	  
	// TODO Auto-generated constructor stub
}
public Kvgh(int id, String name, int gia, int thoigian, int trangthai) {
	super();
	this.id = id;
	this.name = name;
	this.gia = gia;
	this.thoigian = thoigian;
	this.trangthai = trangthai;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getGia() {
	return gia;
}
public void setGia(int gia) {
	this.gia = gia;
}
public int getThoigian() {
	return thoigian;
}
public void setThoigian(int thoigian) {
	this.thoigian = thoigian;
}
public int getTrangthai() {
	return trangthai;
}
public void setTrangthai(int trangthai) {
	this.trangthai = trangthai;
}
  
  
 
  
  
}
