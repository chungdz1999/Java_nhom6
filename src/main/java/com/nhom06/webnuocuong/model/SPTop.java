package com.nhom06.webnuocuong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


public class SPTop {
	
	private String sanphamanh;
	private long sanphamdaban;
	private String sanphamten;
	private int sanphamgiatien;
	
	public SPTop(String sanphamanh, long sanphamdaban, String sanphamten, int sanphamgiatien) {
		super();
		this.sanphamanh = sanphamanh;
		this.sanphamdaban = sanphamdaban;
		this.sanphamten = sanphamten;
		this.sanphamgiatien = sanphamgiatien;
	}
	public long getSanphamdaban() {
		return sanphamdaban;
	}
	public void setSanphamdaban(long sanphamdaban) {
		this.sanphamdaban = sanphamdaban;
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
	public String getSanphamanh() {
		return sanphamanh;
	}
	public void setSanphamanh(String sanphamanh) {
		this.sanphamanh = sanphamanh;
	}
	
	

	
	
	
	
	
	
	
	
	

}
