package com.ita.oracledaoserver.operation;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.ita.dao.DepartmentDao;
import com.ita.dao.impl.DepartmentDaoImpl;
import com.ita.model.Department;

public class AddDepartment {
	private String value;
	private OutputStream outputStream;
	
	
	public AddDepartment(String value, OutputStream outputStream) {
		super();
		this.value = value;
		this.outputStream = outputStream;
	}

	
	public void addDepartment() {
		System.out.println("******************** addDepartment Start ********************");
		Map<String, String> map= new HashMap<String,String>();
		String[] values=value.split(",");
		for (int i = 0; i < values.length; i++) {
			String[] subValues=values[i].split(":");
			map.put(subValues[0], subValues[1]);
		}
		Department department=new Department(map.get("dName"), map.get("city"));
		DepartmentDao departmentDao=new DepartmentDaoImpl();
		int m=departmentDao.addDepartment(department);
		if(m==0){
			System.out.println("Failed to insert new Department.....");
			try {
				outputStream.write(("Insert fail!"+"\n").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		else {
			System.out.println("Insert new department successfully.....");
			try {
				outputStream.write(("Insert success!"+"\n").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
}
