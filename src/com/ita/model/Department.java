package com.ita.model;

import java.io.Serializable;

import oracle.net.aso.d;

public class Department implements Serializable {
	
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
	public static Department stringToDepartment(String str){
		Department department=new Department();
		String dString=str.substring(1, str.length()-1);
		String[] dStrings=dString.split(",");
		department.setdId(Integer.valueOf(dStrings[0]));
		department.setdName(dStrings[1]);
		department.setCity(dStrings[2]);
		return department;
	}
	@Override
	public String toString() {
		return "["+this.getdId()+","+this.getdName()+","+this.getCity()+"]";
	}

}
