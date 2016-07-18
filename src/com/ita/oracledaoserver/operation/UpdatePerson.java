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

public class UpdatePerson {
	private String value;
	private OutputStream outputStream;
	
	public UpdatePerson(String value, OutputStream outputStream) {
		super();
		this.value = value;
		this.outputStream = outputStream;
	}

	public void updatePerson() {
		System.out.println("******************** updatePerson Start ********************");
		Map<String, String> map= new HashMap<String,String>();
		String[] values=value.split(",");
		for (int i = 0; i < values.length; i++) {
			String[] subValues=values[i].split(":");
			map.put(subValues[0], subValues[1]);
		}
		String dateString=map.get("birthday");
		String pId=map.get("pId");
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date uDate=null;
		try {
			uDate = simpleDateFormat.parse(dateString);
			Date date=new Date(uDate.getTime());
			Person person=new Person(map.get("pName"), date, map.get("tel"), 
					Integer.valueOf(map.get("dId")), Integer.valueOf(map.get("salary")));
			person.setpId(Integer.valueOf(pId));
			PersonDao personDao=new PersonDaoImpl();
			int m=personDao.updatePerson(person);
			if(m==0){
				System.out.println("Failed to update person.....");
				try {
					outputStream.write(("Update fail!"+"\n").getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			else {
				System.out.println("Update person successfully.....");
				try {
					outputStream.write(("Update success!"+"\n").getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
