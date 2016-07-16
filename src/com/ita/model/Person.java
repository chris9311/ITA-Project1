package com.ita.model;

import java.io.Serializable;
import java.sql.Date;

public class Person implements Serializable{
	
	private int pId;
	private String pName;
	private Date birthday;
	private String tel;
	private int dId;
	private int salary;
	
	public Person() {
		super();
	}
	public Person(String pName, Date birthday, String tel, int dId, int salary) {
		super();
		this.pName = pName;
		this.birthday = birthday;
		this.tel = tel;
		this.dId = dId;
		this.salary = salary;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		
		return "["+this.getpId()+","+this.getpName()+","+this.getBirthday().toString()+","+this.getTel()+
				","+this.getdId()+","+this.getSalary()+"]";
	}
}
