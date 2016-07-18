package com.ita.oracledaoserver.operation;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.ita.dao.PersonDao;
import com.ita.dao.impl.PersonDaoImpl;
import com.ita.model.Person;

public class AddPerson {
	private String value;
	private OutputStream outputStream;
	
	public AddPerson(String value, OutputStream outputStream) {
		super();
		this.value = value;
		this.outputStream = outputStream;
	}

	public void addPerson() {
		System.out.println("******************** addPerson Start ********************");
		Map<String, String> map= new HashMap<String,String>();
		String[] values=value.split(",");
		for (int i = 0; i < values.length; i++) {
			String[] subValues=values[i].split(":");
			map.put(subValues[0], subValues[1]);
		}
		String dateString=map.get("birthday");
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date uDate=new java.util.Date();
		try {
			uDate = simpleDateFormat.parse(dateString);
			Date date=new Date(uDate.getTime());
			Person person=new Person(map.get("pName"), date, map.get("tel"), 
					Integer.valueOf(map.get("dId")), Integer.valueOf(map.get("salary")));
			PersonDao personDao=new PersonDaoImpl();
			int m=personDao.addPerson(person);
			if(m==0){
				System.out.println("Failed to insert new person.....");
				try {
					outputStream.write(("Insert fail!"+"\n").getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			else {
				System.out.println("Insert new person successfully.....");
				try {
					outputStream.write(("Insert success!"+"\n").getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
