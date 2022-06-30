package com.nhom06.webnuocuong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_danhmucsp")
public class DanhMuc {
	@Id
	@Column(name = "danhmucsp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int danhmucspid;

	@Column(name = "danhmucsp_ten")
	private String danhmucspten;

	@Column(name = "danhmucsp_mota")
	private String danhmucspmota;

	@Column(name = "danhmucsp_trangthai")
	private String danhmucsptrangthai;

	public int getDanhmucsp_id() {
		return danhmucspid;
	}

	public int getDanhmucspid() {
		return danhmucspid;
	}

	public void setDanhmucspid(int danhmucspid) {
		this.danhmucspid = danhmucspid;
	}

	public String getDanhmucspten() {
		return danhmucspten;
	}

	public void setDanhmucspten(String danhmucspten) {
		this.danhmucspten = danhmucspten;
	}

	public String getDanhmucsptrangthai() {
		return danhmucsptrangthai;
	}

	public String getDanhmucspmota() {
		return danhmucspmota;
	}

	public void setDanhmucspmota(String danhmucspmota) {
		this.danhmucspmota = danhmucspmota;
	}

	public void setDanhmucsptrangthai(String danhmucsptrangthai) {
		this.danhmucsptrangthai = danhmucsptrangthai;
	}

}