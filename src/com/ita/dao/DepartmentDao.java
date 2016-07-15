package com.ita.dao;

import java.util.List;

import com.ita.model.Department;

public interface DepartmentDao {
	
	public int addDepartment(Department d);
	public int deleteDepartment(int id);
	public int updateDepartment(Department d);
	public List<Department> showAllDepartment();
	public Department loadDepartment(int id);
	
}
