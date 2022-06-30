package com.nhom06.webnuocuong.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "tbl_donhang")
public class donhang {
  @Id
  @Column(name = "donhang_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int donhangid;
  
  @Column(name = "trangthaidonhang_id" )
  private int trangthaidonhangid;
  
  @Column(name = "khachhang_id")
  private int khachhangid;
  
  @Column(name = "phuongthucthanhtoan")
  private int phuongthucthanhtoan;
  
  @Column(name = "tongtien")
  private int tongtien;
  
  @Column(name = "phivanchuyen")
  private int phivanchuyen;
  
  @Column(name = "thoigiangiao")
  private int thoigiangiao;
  
  @Column(name = "thoigiantao")
  @JsonFormat(pattern="HH:mm:ss")
  private LocalTime thoigiantao;
  
  @Column(name = "ngaytao")
  @JsonFormat(pattern="yyyy-MM-dd")
  private LocalDate ngaytao;
  
  @Column(name = "nguoinhan_ten")
  private String nguoinhanten;
  
  @Column(name = "nguoinhan_diachi")
  private String nguoinhandiachi;
  
  @Column(name = "nguoinhan_sdt")
  private String nguoinhansdt;
  
  @Column(name = "nguoinhan_ghichu")
  private String nguoinhanghichu;
  
  @Column(name = "nguoinhan_email")
  private String nguoinhanemail;
  
  @Column(name = "nguoigiao_ten")
  private String shipten;
  
  @Column(name = "nguoigiao_sdt")
  private String shipsdt ;
  
  @Column(name = "giamgia")
  private String giamgia ;
  
  
  public donhang() {
	  
  }
  
  public donhang(int donhangid, int trangthaidonhang) {
	  this.donhangid = donhangid;
	  this.trangthaidonhangid = trangthaidonhang;
  }
  
  
  

public donhang( int trangthaidonhangid, int khachhang_id, int phuongthucthanhtoan, int tongtien,
		int phivanchuyen, int thoigiangiao, LocalTime thoigiantao, LocalDate ngaytao, String nguoinhanten,
		String nguoinhan_diachi, String sdt, String nguoinhanghichu, String nguoinhanemail, String giamgia) {
	super();
	
	this.trangthaidonhangid = trangthaidonhangid;
	this.khachhangid = khachhang_id;
	this.phuongthucthanhtoan = phuongthucthanhtoan;
	this.tongtien = tongtien;
	this.phivanchuyen = phivanchuyen;
	this.thoigiangiao = thoigiangiao;
	this.thoigiantao = thoigiantao;
	this.ngaytao = ngaytao;
	this.nguoinhanten = nguoinhanten;
	this.nguoinhandiachi = nguoinhan_diachi;
	this.nguoinhansdt = sdt;
	this.nguoinhanghichu = nguoinhanghichu;
	this.nguoinhanemail = nguoinhanemail;
	this.giamgia = giamgia;
	
}




public String getGiamgia() {
	return giamgia;
}

public void setGiamgia(String giamgia) {
	this.giamgia = giamgia;
}

public int getDonhangid() {
	return donhangid;
}

public void setDonhangid(int donhangid) {
	this.donhangid = donhangid;
}

public int getTrangthaidonhangid() {
	return trangthaidonhangid;
}

public void setTrangthaidonhangid(int trangthaidonhangid) {
	this.trangthaidonhangid = trangthaidonhangid;
}

public int getKhachhangid() {
	return khachhangid;
}

public void setKhachhangid(int khachhangid) {
	this.khachhangid = khachhangid;
}

public int getPhuongthucthanhtoan() {
	return phuongthucthanhtoan;
}

public void setPhuongthucthanhtoan(int phuongthucthanhtoan) {
	this.phuongthucthanhtoan = phuongthucthanhtoan;
}

public int getTongtien() {
	return tongtien;
}

public void setTongtien(int tongtien) {
	this.tongtien = tongtien;
}

public int getPhivanchuyen() {
	return phivanchuyen;
}

public void setPhivanchuyen(int phivanchuyen) {
	this.phivanchuyen = phivanchuyen;
}

public int getThoigiangiao() {
	return thoigiangiao;
}

public void setThoigiangiao(int thoigiangiao) {
	this.thoigiangiao = thoigiangiao;
}

public LocalTime getThoigiantao() {
	return thoigiantao;
}

public void setThoigiantao(LocalTime thoigiantao) {
	this.thoigiantao = thoigiantao;
}

public LocalDate getNgaytao() {
	return ngaytao;
}

public void setNgaytao(LocalDate ngaytao) {
	this.ngaytao = ngaytao;
}

public String getNguoinhanten() {
	return nguoinhanten;
}

public void setNguoinhanten(String nguoinhanten) {
	this.nguoinhanten = nguoinhanten;
}

public String getNguoinhandiachi() {
	return nguoinhandiachi;
}

public void setNguoinhandiachi(String nguoinhandiachi) {
	this.nguoinhandiachi = nguoinhandiachi;
}

public String getNguoinhansdt() {
	return nguoinhansdt;
}

public void setNguoinhansdt(String nguoinhansdt) {
	this.nguoinhansdt = nguoinhansdt;
}

public String getNguoinhanghichu() {
	return nguoinhanghichu;
}

public void setNguoinhanghichu(String nguoinhanghichu) {
	this.nguoinhanghichu = nguoinhanghichu;
}

public String getNguoinhanemail() {
	return nguoinhanemail;
}

public void setNguoinhanemail(String nguoinhanemail) {
	this.nguoinhanemail = nguoinhanemail;
}

public String getShipten() {
	return shipten;
}

public void setShipten(String shipten) {
	this.shipten = shipten;
}

public String getShipsdt() {
	return shipsdt;
}

public void setShipsdt(String shipsdt) {
	this.shipsdt = shipsdt;
}









  

  // getter setter
  
  
  
  
}