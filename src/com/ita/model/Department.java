package com.ita.model;

public class Department {
	
	private int dId;
	private String dName;
	private String city;
	public Department() {
		super();
	}
	public Department(String dName, String city) {
		super();
		this.dName = dName;
		this.city = city;
	}
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	

}
