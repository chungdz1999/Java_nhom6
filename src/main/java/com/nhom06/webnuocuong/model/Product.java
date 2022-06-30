package com.nhom06.webnuocuong.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_sanpham")
public class Product {
	@Id
	@Column(name = "sanpham_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sanphamid;

	@Column(name = "sanpham_ten")
	private String sanphamten;

	@Column(name = "sanpham_giatien")
	private int sanphamgiatien;

	@Column(name = "sanpham_mota")
	private String sanphammota;

	@Column(name = "sanpham_anhchinh")
	private String sanphamanhchinh;

	@Column(name = "sanpham_trangthai")
	private int sanphamtrangthai;

	@Column(name = "thoigiantao")
	private Date thoigiantao;

	@Column(name = "sanpham_daban")
	private int sanphamdaban;

	@Column(name = "sanpham_giacu")
	private int sanphamgiacu;

	@Column(name = "danhmucsp_id")
	private int danhmucspid;
	
	@Column(name = "danhgia")
	private double danhgia;
	

//	public Product(String sanphamten, int sanphamgiatien, String sanphammota, String sanphamanhchinh,
//			int sanphamtrangthai, Date thoigiantao, int sanphamdaban, int sanphamgiacu, int danhmucspid) {
//		super();
//		this.sanphamten = sanphamten;
//		this.sanphamgiatien = sanphamgiatien;
//		this.sanphammota = sanphammota;
//		this.sanphamanhchinh = sanphamanhchinh;
//		this.sanphamtrangthai = sanphamtrangthai;
//		this.thoigiantao = thoigiantao;
//		this.sanphamdaban = sanphamdaban;
//		this.sanphamgiacu = sanphamgiacu;
//		this.danhmucspid = danhmucspid;
//	}

	
	public int getSanphamid() {
		return sanphamid;
	}

	public Product() {

	}

	public Product(String sanphamten, int sanphamgiatien, String sanphammota, String sanphamanhchinh, int danhmucspid) {
		super();
		this.sanphamten = sanphamten;
		this.sanphamgiatien = sanphamgiatien;
		this.sanphammota = sanphammota;
		this.sanphamanhchinh = sanphamanhchinh;
		this.danhmucspid = danhmucspid;
	}
	
	
	

	public double getDanhgia() {
		return danhgia;
	}

	public void setDanhgia(double danhgia) {
		this.danhgia = danhgia;
	}

	public void setSanphamid(int sanphamid) {
		this.sanphamid = sanphamid;
	}

	public String getSanphamten() {
		return sanphamten;
	}

	public void setSanphamten(String sanphamten) {
		this.sanphamten = sanphamten;
	}
	
	

	public int getSanphamgiatien() {
		return sanphamgiatien;
	}

	public void setSanphamgiatien(int sanphamgiatien) {
		this.sanphamgiatien = sanphamgiatien;
	}

	public String getSanphammota() {
		return sanphammota;
	}

	public void setSanphammota(String sanphammota) {
		this.sanphammota = sanphammota;
	}

	public String getSanphamanhchinh() {
		return sanphamanhchinh;
	}

	public void setSanphamanhchinh(String sanphamanhchinh) {
		this.sanphamanhchinh = sanphamanhchinh;
	}

	public int getSanphamtrangthai() {
		return sanphamtrangthai;
	}

	public void setSanphamtrangthai(int sanphamtrangthai) {
		this.sanphamtrangthai = sanphamtrangthai;
	}

	public Date getThoigiantao() {
		return thoigiantao;
	}

	public void setThoigiantao(Date thoigiantao) {
		this.thoigiantao = thoigiantao;
	}

	public int getSanphamdaban() {
		return sanphamdaban;
	}

	public void setSanphamdaban(int sanphamdaban) {
		this.sanphamdaban = sanphamdaban;
	}

	public int getSanphamgiacu() {
		return sanphamgiacu;
	}

	public void setSanphamgiacu(int sanphamgiacu) {
		this.sanphamgiacu = sanphamgiacu;
	}

	public int getDanhmucspid() {
		return danhmucspid;
	}

	public void setDanhmucspid(int danhmucspid) {
		this.danhmucspid = danhmucspid;
	}

//	 @Transient
//	  public String getPhotosImagePath() {
//	  if (sanphamanhchinh == null ) return null;
//	         
//	        return "/user-photos/" +  + "/" + photos;
//	    }

}