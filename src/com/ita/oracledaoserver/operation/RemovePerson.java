package com.ita.oracledaoserver.operation;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.ita.dao.PersonDao;
import com.ita.dao.impl.PersonDaoImpl;

public class RemovePerson {
	private String value;
	private OutputStream outputStream;
	
	public RemovePerson(String value, OutputStream outputStream) {
		super();
		this.value = value;
		this.outputStream = outputStream;
	}

	public void removePerson() {
		System.out.println("******************** removePerson Start ********************");
		Map<String, String> map= new HashMap<String,String>();
		String[] values=value.split(":");
		map.put(values[0], values[1]);
		PersonDao personDao=new PersonDaoImpl();
		int m=personDao.deletePerson(Integer.valueOf(map.get("pId")));
		if(m==0){
			System.out.println("Delete person failed.....");
			try {
				outputStream.write(("Delete fail!"+"\n").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		else {
			System.out.println("Delete person successfully.....");
			try {
				outputStream.write(("Delete success!"+"\n").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
}
