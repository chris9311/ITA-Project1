package com.ita.server;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ita.dao.DepartmentDao;
import com.ita.dao.PersonDao;
import com.ita.dao.impl.DepartmentDaoImpl;
import com.ita.dao.impl.PersonDaoImpl;
import com.ita.model.Department;
import com.ita.model.Person;

public class OracleDaoServer extends Server {

	public OracleDaoServer(Socket socket) {
		super(socket);
	}

	@Override
	public void connect() {
		System.out.println("******************** Oracle Server Start ********************");
		
		OutputStream outputStream = null;
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			
			outputStream = socket.getOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			
			inputStream = socket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			reader = new BufferedReader(new InputStreamReader(inputStream));
			
			while(true){
				String command=reader.readLine();
				String[] commands=command.split(" ");
				if("A-P".equals(commands[0])){          //新增person
					addPerson(commands[1]);
				}else if ("A-D".equals(commands[0])) {    //新增department
					addDepartment(commands[1]);
				}else if ("U-P".equals(commands[0])) {    //更新person
					updatePerson(commands[1]);
				}else if ("U-D".equals(commands[0])) {    //更新department
					updateDepartment(commands[1]);
				}else if ("D-P".equals(commands[0])) {   //删除指定id的person
					removePerson(commands[1]);
				}else if ("D-D".equals(commands[0])) {   //删除department以及其内的person
					removeDepartment(commands[1]);
				}else if ("L-PA".equals(commands[0])) {   //查询所有person的信息，除了departmentID
					loadPersonAll(objectOutputStream,outputStream,objectInputStream);
				}else if ("L-DA".equals(commands[0])) {   //查询所有department
					loadDepartmentAll(objectOutputStream,outputStream,objectInputStream);
				}else if ("L-PD".equals(commands[0])) {   //查询一个department所有person的所有属性
					loadAllPersonOfADepartment(objectOutputStream,outputStream,objectInputStream,commands[1]);
				}else if ("Q".equals(commands[0])) {   //退出
					SingletonOracleDaoServer.clientClose(socket);
					break;
				}
			}
		 }catch (EOFException e) {
			// TODO: handle exception
			 System.out.println("Client is closed");
		}catch (SocketException e) {
			// TODO: handle exception
			System.out.println("******************** Oracle Server is closed ********************");
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			
			try {
				outputStream.close();
				inputStream.close();
				reader.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
	}
	
	private void loadDepartmentAll(ObjectOutputStream objectOutputStream,OutputStream outputStream, ObjectInputStream objectInputStream) {
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
			System.out.println("发送所有department结束.....");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void loadPersonAll(ObjectOutputStream objectOutputStream,OutputStream outputStream,ObjectInputStream objectInputStream) {
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
			System.out.println("发送所有person结束....");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void loadAllPersonOfADepartment(ObjectOutputStream objectOutputStream,  OutputStream outputStream, ObjectInputStream objectinputStream, String value) {
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
			} catch (IOException e) {
				e.printStackTrace();
			}
	}



	private void removeDepartment(String value) {
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
		if(m==0)
			System.out.println("删除department失败.....");
		else 
			System.out.println("删除department成功.....");
	}

	private void removePerson(String value) {
		Map<String, String> map= new HashMap<String,String>();
		String[] values=value.split(":");
		map.put(values[0], values[1]);
		PersonDao personDao=new PersonDaoImpl();
		int m=personDao.deletePerson(Integer.valueOf(map.get("pId")));
		if(m==0)
			System.out.println("删除person失败.....");
		else 
			System.out.println("删除person成功.....");
	}

	private void updateDepartment(String value) {
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
		if(m==0)
			System.out.println("更新department失败.....");
		else 
			System.out.println("更新department成功.....");
	}

	private void updatePerson(String value) {
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
			if(m==0)
				System.out.println("更新person失败.....");
			else 
				System.out.println("更新person成功.....");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void addPerson(String value) {
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
			if(m==0)
				System.out.println("插入新person失败.....");
			else 
				System.out.println("插入新person成功.....");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void addDepartment(String value) {
		Map<String, String> map= new HashMap<String,String>();
		String[] values=value.split(",");
		for (int i = 0; i < values.length; i++) {
			String[] subValues=values[i].split(":");
			map.put(subValues[0], subValues[1]);
		}
		Department department=new Department(map.get("dName"), map.get("city"));
		DepartmentDao departmentDao=new DepartmentDaoImpl();
		int m=departmentDao.addDepartment(department);
		if(m==0)
			System.out.println("插入新department失败.....");
		else 
			System.out.println("插入新department成功.....");
	}

	@Override
	public void run() {
		connect();
	}

}
