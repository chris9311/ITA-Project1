package com.ita.oracledaoserver.operation;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ita.dao.PersonDao;
import com.ita.dao.impl.PersonDaoImpl;
import com.ita.model.Person;

public class LoadAllPersonOfADepartment {
	private OutputStream outputStream;
	private String value;
	
	public LoadAllPersonOfADepartment(OutputStream outputStream, String value) {
		super();
		this.outputStream = outputStream;
		this.value = value;
	}

	public void loadAllPersonOfADepartment() {
		System.out.println("******************** loadAllPersonOfADepartment Start ********************");
		Map<String, String> map= new HashMap<String,String>();
		String[] values=value.split(":");
		map.put(values[0], values[1]);
		try {
			PersonDao personDao=new PersonDaoImpl();
			List<Person> persons=personDao.showAllPersonOfADepartment(Integer.valueOf(map.get("dId")));
			int size=persons.size();
			outputStream.write((String.valueOf(size)+"\n").getBytes());
			for (Iterator<Person> iterator = persons.iterator(); iterator.hasNext();) {
				Person person = (Person) iterator.next();
				outputStream.write((person.toString()+"\n").getBytes());
			}
			System.out.println("Send all person ends....");
		} catch (IOException e) {
			e.printStackTrace();
		}
}
}
