package com.ita.server;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.ita.oracledaoserver.operation.AddDepartment;
import com.ita.oracledaoserver.operation.AddPerson;
import com.ita.oracledaoserver.operation.LoadAllPersonOfADepartment;
import com.ita.oracledaoserver.operation.LoadDepartmentAll;
import com.ita.oracledaoserver.operation.LoadPersonAll;
import com.ita.oracledaoserver.operation.RemoveDepartment;
import com.ita.oracledaoserver.operation.RemovePerson;
import com.ita.oracledaoserver.operation.UpdateDepartment;
import com.ita.oracledaoserver.operation.UpdatePerson;

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
			inputStream = socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream));
			
			while(true){
				String command=reader.readLine();
				String[] commands=command.split(" ");
				if("A-P".equals(commands[0])){          //新增person
					AddPerson addPerson=new AddPerson(commands[1], outputStream);
					addPerson.addPerson();
				}else if ("A-D".equals(commands[0])) {    //新增department
					AddDepartment addDepartment=new AddDepartment(commands[1], outputStream);
					addDepartment.addDepartment();
				}else if ("U-P".equals(commands[0])) {    //更新person
					UpdatePerson updatePerson=new UpdatePerson(commands[1], outputStream);
					updatePerson.updatePerson();
				}else if ("U-D".equals(commands[0])) {    //更新department
					UpdateDepartment updateDepartment=new UpdateDepartment(commands[1], outputStream);
					updateDepartment.updateDepartment();
				}else if ("D-P".equals(commands[0])) {   //删除指定id的person
					RemovePerson removePerson=new RemovePerson(commands[1], outputStream);
					removePerson.removePerson();
				}else if ("D-D".equals(commands[0])) {   //删除department以及其内的person
					RemoveDepartment removeDepartment=new RemoveDepartment(commands[1], outputStream);
					removeDepartment.removeDepartment();
				}else if ("L-PA".equals(commands[0])) {   //查询所有person的信息，除了departmentID
					LoadPersonAll loadPersonAll=new LoadPersonAll(outputStream);
					loadPersonAll.loadPersonAll();
				}else if ("L-DA".equals(commands[0])) {   //查询所有department
					LoadDepartmentAll loadDepartmentAll=new LoadDepartmentAll(outputStream);
					loadDepartmentAll.loadDepartmentAll();
				}else if ("L-PD".equals(commands[0])) {   //查询一个department所有person的所有属性
					LoadAllPersonOfADepartment loadAllPersonOfADepartment=new LoadAllPersonOfADepartment(outputStream, commands[1]);
					loadAllPersonOfADepartment.loadAllPersonOfADepartment();
				}else if ("Q".equals(commands[0])) {   //退出
					SingletonOracleDaoServer.clientClose(socket);
					break;
				}
			}
		 }catch (EOFException e) {
			 System.out.println("Client is closed");
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
	@Override
	public void run() {
		connect();
	}

}
