package com.ita.oracledaoserver.operation;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;

import com.ita.dao.PersonDao;
import com.ita.dao.impl.PersonDaoImpl;
import com.ita.model.Department;
import com.ita.model.Person;

public class LoadPersonAll {
	private OutputStream outputStream;
	
	public LoadPersonAll(OutputStream outputStream) {
		super();
		this.outputStream = outputStream;
	}

	public void loadPersonAll() {
		try {
			System.out.println("******************** loadPersonAll Start ********************");
			PersonDao personDao=new PersonDaoImpl();
			Map<Person, Department> map=personDao.showPersonAndHisDepartment();
			int size=map.size();
			outputStream.write((String.valueOf(size)+"\n").getBytes());
			System.out.println(size);
			Set<Person> persons=map.keySet();
			for (Person person : persons) {
				outputStream.write((person.toString()+"\n").getBytes());
				outputStream.write((map.get(person).toString()+"\n").getBytes());
			}
			System.out.println("Send all person ends....");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
