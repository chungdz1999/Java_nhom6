package com.nhom06.webnuocuong.web.dto;

public class UserRegistrationDto {
	private long id;
	private String username;
	private String password;
	private String name;
	private String trangthai;
	private String quyen;
	private String email;

	public UserRegistrationDto() {

	}

	public UserRegistrationDto(String username, String password, String name, String trangthai, String quyen, String email) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.trangthai = trangthai;
		this.quyen = quyen;
		this.email = email;
	}

	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
