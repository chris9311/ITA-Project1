package com.ita.oracledaoserver.operation;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import com.ita.dao.DepartmentDao;
import com.ita.dao.impl.DepartmentDaoImpl;
import com.ita.model.Department;

public class LoadDepartmentAll {
	private OutputStream outputStream;
	
	public LoadDepartmentAll(OutputStream outputStream) {
		super();
		this.outputStream = outputStream;
	}

	public void loadDepartmentAll() {
		try {
			System.out.println("******************** LoadDepartmentAll Start ********************");
			DepartmentDao departmentDao=new DepartmentDaoImpl();
			List<Department> list=departmentDao.showAllDepartment();
			int size=list.size();
			outputStream.write((String.valueOf(size)+"\n").getBytes());
			for (Iterator<Department> iterator = list.iterator(); iterator.hasNext();) {
				Department department = (Department) iterator.next();
				outputStream.write((department.toString()+"\n").getBytes());
			}
			System.out.println("Send all department ends.....");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
