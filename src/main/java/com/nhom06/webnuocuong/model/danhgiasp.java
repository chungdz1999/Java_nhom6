package com.nhom06.webnuocuong.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_danhgiasp")
public class danhgiasp {
	
	@Id
	@Column(name = "danhgiasp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "khachhang_id")
	private int khachhangid;

	@Column(name = "chitietdonhang_id")
	private int chitietdonhangid;

	@Column(name = "sanpham_id")
	private int sanphamid;
	
	@Column(name = "noidung")
	private String noidung;
	
	@Column(name = "danhgiasp_star")
	private int star;
	
	@Column(name = "danhgiasp_anh1")
	private String anh1;
	
	@Column(name = "ngaytao")
	private LocalDate ngaytao;
	
	@Column(name = "thoigiantao")
	private LocalTime thoigiantao;

	public danhgiasp() {
		
	}
	
	public danhgiasp( int khachhangid, int ctdonhangid, int sanphamid, String noidung, int star, String anh1,
			LocalDate ngaytao, LocalTime thoigiantao) {
		super();
		
		this.khachhangid = khachhangid;
		this.chitietdonhangid = ctdonhangid;
		this.sanphamid = sanphamid;
		this.noidung = noidung;
		this.star = star;
		this.anh1 = anh1;
		this.ngaytao = ngaytao;
		this.thoigiantao = thoigiantao;
	}
	
	public danhgiasp( int khachhangid, int ctdonhangid, int sanphamid, int star) {
		super();
		
		this.khachhangid = khachhangid;
		this.chitietdonhangid = ctdonhangid;
		this.sanphamid = sanphamid;
		this.star = star;
		
	}
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKhachhangid() {
		return khachhangid;
	}

	public void setKhachhangid(int khachhangid) {
		this.khachhangid = khachhangid;
	}

	public int getChitietdonhangid() {
		return chitietdonhangid;
	}

	public void setChitietdonhangid(int donhangid) {
		this.chitietdonhangid = donhangid;
	}

	public int getSanphamid() {
		return sanphamid;
	}

	public void setSanphamid(int sanphamid) {
		this.sanphamid = sanphamid;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getAnh1() {
		return anh1;
	}

	public void setAnh1(String anh1) {
		this.anh1 = anh1;
	}

	public LocalDate getNgaytao() {
		return ngaytao;
	}

	public void setNgaytao(LocalDate ngaytao) {
		this.ngaytao = ngaytao;
	}

	public LocalTime getThoigiantao() {
		return thoigiantao;
	}

	public void setThoigiantao(LocalTime thoigiantao) {
		this.thoigiantao = thoigiantao;
	}
	
	
	
	
	

}