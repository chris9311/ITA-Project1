package com.ita.oracledaoserver.operation;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ita.dao.DepartmentDao;
import com.ita.dao.PersonDao;
import com.ita.dao.impl.DepartmentDaoImpl;
import com.ita.dao.impl.PersonDaoImpl;
import com.ita.model.Person;

public class RemoveDepartment {
	private String value;
	private OutputStream outputStream;
	
	public RemoveDepartment(String value, OutputStream outputStream) {
		super();
		this.value = value;
		this.outputStream = outputStream;
	}

	public void removeDepartment() {
		System.out.println("******************** removeDepartment Start ********************");
		Map<String, String> map= new HashMap<String,String>();
		String[] values=value.split(":");
		map.put(values[0], values[1]);
		PersonDao personDao= new PersonDaoImpl();
		List<Person> persons=personDao.showAllPersonOfADepartment(Integer.valueOf(map.get("dId")));
		for (Iterator<Person> iterator = persons.iterator(); iterator.hasNext();) {
			Person person = (Person) iterator.next();
			personDao.deletePerson(person.getpId());
		}
		DepartmentDao departmentDao=new DepartmentDaoImpl();
		int m=departmentDao.deleteDepartment(Integer.valueOf(map.get("dId")));
		if(m==0){
			System.out.println("Delete department failed.....");
			try {
				outputStream.write(("Delete fail!"+"\n").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		else {
			System.out.println("Delete Department successfully.....");
			try {
				outputStream.write(("Delete success!"+"\n").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}

}
