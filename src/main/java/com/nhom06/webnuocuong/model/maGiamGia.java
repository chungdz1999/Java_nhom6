package com.nhom06.webnuocuong.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_magiamgia")
public class maGiamGia {
	@Id
	@Column(name = "magiamgia_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "magiamgia_code")
	private String magiamgiacode;

	@Column(name = "magiamgia_phantram")
	private int magiamgiaphantram;
	
	@Column(name = "magiamgia_toida")
	private int magiamgiatoida;

	@Column(name = "magiamgia_soluong")
	private int magiamgiasoluong;
	
	@Column(name = "magiamgia_ngayhethan")
	private String ngayhethan;

	public maGiamGia() {
		
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int danhmucspid) {
		this.id = danhmucspid;
	}

	public String getMagiamgiacode() {
		return magiamgiacode;
	}

	public void setMagiamgiacode(String magiamgiacode) {
		this.magiamgiacode = magiamgiacode;
	}

	public int getMagiamgiaphantram() {
		return magiamgiaphantram;
	}

	public void setMagiamgiaphantram(int magiamgiaphantram) {
		this.magiamgiaphantram = magiamgiaphantram;
	}

	public int getMagiamgiatoida() {
		return magiamgiatoida;
	}

	public void setMagiamgiatoida(int magiamgiatoida) {
		this.magiamgiatoida = magiamgiatoida;
	}

	public int getMagiamgiasoluong() {
		return magiamgiasoluong;
	}

	public void setMagiamgiasoluong(int magiamgiasoluong) {
		this.magiamgiasoluong = magiamgiasoluong;
	}

	public String getNgayhethan() {
		return ngayhethan;
	}

	public void setNgayhethan(String ngayhethan) {
		this.ngayhethan = ngayhethan;
	}
	
	
	

	
	

}