package com.iut;

public class TableUser extends Table {
	
	private int id_user;
	private String name_user;
	private String password;
	
	@Column("id_user")
	public int getId_user() {
		return id_user;
	}
	
	@Column("id_user")
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	@Column("name_user")
	public String getName_user() {
		return name_user;
	}
	
	@Column("name_user")
	public void setName_user(String name_user) {
		this.name_user = name_user;
	}
	
	@Column("password")
	public String getPassword() {
		return password;
	}
	
	@Column("password")
	public void setPassword(String password) {
		this.password = password;
	}
	
}