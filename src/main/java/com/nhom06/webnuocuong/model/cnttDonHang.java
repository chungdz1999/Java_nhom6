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
@Table(name = "tbl_capnhattrangthaidonhang")

public class cnttDonHang {
	@Id
	@Column(name = "cnttdh_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cnttdhId;
	@Column(name = "admin_id")
	private int adminId;
	@Column(name = "donhang_id")
	private int dhId;
	@Column(name = "ngaycapnhat")
	private LocalDate ngaycn;
	@Column(name = "thoigiancapnhat")
	private LocalTime thoigiancn;
	@Column(name = "noidung")
	private String noidung;
	
	public cnttDonHang() {
		super();
	}
	

	public cnttDonHang( int adminId, int dhId, LocalDate ngaycn, LocalTime thoigiancn, String noidung) {
		super();
		this.adminId = adminId;
		this.dhId = dhId;
		this.ngaycn = ngaycn;
		this.thoigiancn = thoigiancn;
		this.noidung = noidung;
	}


	public int getCnttdhId() {
		return cnttdhId;
	}


	public void setCnttdhId(int cnttdhId) {
		this.cnttdhId = cnttdhId;
	}


	public int getAdminId() {
		return adminId;
	}


	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}


	public int getDhId() {
		return dhId;
	}


	public void setDhId(int dhId) {
		this.dhId = dhId;
	}


	public LocalDate getNgaycn() {
		return ngaycn;
	}


	public void setNgaycn(LocalDate ngaycn) {
		this.ngaycn = ngaycn;
	}


	public LocalTime getThoigiancn() {
		return thoigiancn;
	}


	public void setThoigiancn(LocalTime thoigiancn) {
		this.thoigiancn = thoigiancn;
	}


	public String getNoidung() {
		return noidung;
	}


	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}
	
}
