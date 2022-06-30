package com.nhom06.webnuocuong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_admin")
public class Admin {
	@Id
	@Column(name = "admin_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminid;

	@Column(name = "taikhoan_id")
	private long taikhoan;

	@Column(name = "admin_ten")
	private String adminten;

	@Column(name = "admin_sdt")
	private String adminsdt;

	
	@Column(name = "admin_trangthai")
	private int admintrangthai;
	
	

	public Admin() {
		super();
	}

	public Admin(long taikhoan, int in) {
		super();
		this.taikhoan = taikhoan;
		this.admintrangthai = in;

	}

	public int getAdminid() {
		return adminid;
	}

	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}

	public long getTaikhoan() {
		return taikhoan;
	}

	public void setTaikhoan(long taikhoan) {
		this.taikhoan = taikhoan;
	}

	public String getAdminten() {
		return adminten;
	}

	public void setAdminten(String adminten) {
		this.adminten = adminten;
	}

	public String getAdminsdt() {
		return adminsdt;
	}

	public void setAdminsdt(String adminsdt) {
		this.adminsdt = adminsdt;
	}

	

}