package com.ita.oracledaoserver.operation;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.ita.dao.DepartmentDao;
import com.ita.dao.impl.DepartmentDaoImpl;
import com.ita.model.Department;

public class UpdateDepartment {
	private String value;
	private OutputStream outputStream;
	
	public UpdateDepartment(String value, OutputStream outputStream) {
		super();
		this.value = value;
		this.outputStream = outputStream;
	}

	public void updateDepartment() {
		System.out.println("******************** updateDepartment Start ********************");
		Map<String, String> map= new HashMap<String,String>();
		String[] values=value.split(",");
		for (int i = 0; i < values.length; i++) {
			String[] subValues=values[i].split(":");
			map.put(subValues[0], subValues[1]);
		}
		Department department=new Department(map.get("dName"), map.get("city"));
		department.setdId(Integer.valueOf(map.get("dId")));
		DepartmentDao departmentDao=new DepartmentDaoImpl();
		int m=departmentDao.updateDepartment(department);
		if(m==0){
			System.out.println("Failed to update department.....");
			try {
				outputStream.write(("Update fail!"+"\n").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		else {
			System.out.println("Update department successfully.....");
			try {
				outputStream.write(("Update success!"+"\n").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}

}
