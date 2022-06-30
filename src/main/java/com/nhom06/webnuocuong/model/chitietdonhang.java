package com.nhom06.webnuocuong.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tbl_chitietdonhang")
public class chitietdonhang {
	@Id
	@Column(name = "chitietdonhang_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int chitietdonhangid;
	
	@Column(name = "donhang_id")
	private int donhangid;

	@Column(name = "chitietdonhang_idsp")
	private int ctdhidsp;

	@Column(name = "chitietdonhang_soluongsp")
	private int ctdhsoluongsp;

	@Column(name = "chitietdonhang_giasp")
	private int ctdhgiasp;
	
	@Column(name = "chitietdonhang_tensp")
	private String ctdhtensp;
	
	@Column(name = "danhgia")
	private int danhgia;
	
	
	@Column(name = "ngaytao")
	@JsonFormat(pattern="yyyy-MM-dd")
	  private LocalDate ngaytao;


	
	
	public chitietdonhang() {
		
	}
	
	
	
	public chitietdonhang( int donhangid, int ctdhidsp, int ctdhsoluongsp, int ctdhgiasp,
			String ctdhtensp, LocalDate ngaytao, int danhgia) {
		super();
		
		this.donhangid = donhangid;
		this.ctdhidsp = ctdhidsp;
		this.ctdhsoluongsp = ctdhsoluongsp;
		this.ctdhgiasp = ctdhgiasp;
		this.ctdhtensp = ctdhtensp;
		this.ngaytao = ngaytao;
		this.danhgia = danhgia;
	}

	
	
	
	
	
	

	public int getDanhgia() {
		return danhgia;
	}



	public void setDanhgia(int danhgia) {
		this.danhgia = danhgia;
	}



	public int getChitietdonhangid() {
		return chitietdonhangid;
	}


	public void setChitietdonhangid(int chitietdonhang_id) {
		this.chitietdonhangid = chitietdonhang_id;
	}


	public int getDonhangid() {
		return donhangid;
	}


	public void setDonhangid(int donhangid) {
		this.donhangid = donhangid;
	}


	public int getCtdhidsp() {
		return ctdhidsp;
	}


	public void setCtdhidsp(int ctdhidsp) {
		this.ctdhidsp = ctdhidsp;
	}


	public int getCtdhsoluongsp() {
		return ctdhsoluongsp;
	}


	public void setCtdhsoluongsp(int ctdhsoluongsp) {
		this.ctdhsoluongsp = ctdhsoluongsp;
	}


	public int getCtdhgiasp() {
		return ctdhgiasp;
	}


	public void setCtdhgiasp(int ctdhgiasp) {
		this.ctdhgiasp = ctdhgiasp;
	}


	public String getCtdhtensp() {
		return ctdhtensp;
	}


	public void setCtdhtensp(String ctdhtensp) {
		this.ctdhtensp = ctdhtensp;
	}


	public LocalDate getNgaytao() {
		return ngaytao;
	}


	public void setNgaytao(LocalDate ngaytao) {
		this.ngaytao = ngaytao;
	}
	
	
	
	
	
}