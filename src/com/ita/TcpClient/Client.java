package com.ita.TcpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.ita.util.Command;
import com.ita.util.PrintObject;

public class Client {

	public static void main(String[] args) throws Exception {
//		"10.222.232.164", 8081
		Socket socket=new Socket("127.0.0.1", 8890);
		BufferedReader readConsole=new BufferedReader(new InputStreamReader(System.in));
		
		OutputStream outputStream = null;
		InputStream inputStream = null;
		BufferedReader reader = null;
		
		outputStream = socket.getOutputStream();
		inputStream = socket.getInputStream();
		reader = new BufferedReader(new InputStreamReader(inputStream));
		
		System.out.println("******************** 客户机启动 ********************");
		while(true){
			
			try{
				   socket.sendUrgentData(0xFF);
				}catch(Exception e){
				System.out.println("server is close");
				}
			
			System.out.println("******************** 请输入指令： ********************");
			String command=readConsole.readLine();
			
			String[] commands=command.split(" ");		
			
			if("A-P".equals(commands[0])){                     //新增person
				if(commands.length==2){
					Command c=Command.getCommand(commands[0], commands[1]);
					if(c.getFlag()){
						outputStream.write((c.getCommand()+"\n").getBytes());
						String reString=reader.readLine();
						System.out.println(reString);
					}else{
						System.out.println("******************** 命令输入错误！ ********************");
					}
				}else{
					System.out.println("******************** 命令输入错误！ ********************");
				}
			}else if ("A-D".equals(commands[0])) {                //新增department
				if(commands.length==2){
					Command c=Command.getCommand(commands[0], commands[1]);
					if(c.getFlag()){
						outputStream.write((c.getCommand()+"\n").getBytes());	
						String reString=reader.readLine();
						System.out.println(reString);
					}else{
						System.out.println("******************** 命令输入错误！ ********************");
					}
				}else{
					System.out.println("******************** 命令输入错误！ ********************");
				}
			}else if ("U-P".equals(commands[0])) {                   //更新person
				if(commands.length==2){
					Command c = Command.getCommand(commands[0], commands[1]);
					if(c.getFlag()){										
						outputStream.write((c.getCommand()+"\n").getBytes());
						String reString=reader.readLine();
						System.out.println(reString);
					}else{
						System.out.println("******************** 命令输入错误！ ********************");
					}
				}else{
					System.out.println("******************** 命令输入错误！ ********************");
				}
			}else if ("U-D".equals(commands[0])) {                     //更新department
				if(commands.length==2){
					Command c=Command.getCommand(commands[0], commands[1]);
					if(c.getFlag()){
						outputStream.write((c.getCommand()+"\n").getBytes());		
						String reString=reader.readLine();
						System.out.println(reString);
					}else{
						System.out.println("******************** 命令输入错误！ ********************");
					}
				}else{
					System.out.println("******************** 命令输入错误！ ********************");
				}
			}else if ("D-P".equals(commands[0])) {               //删除指定id的person
				if(commands.length==2){
					Command c = Command.getCommand(commands[0], commands[1]);
					if(c.getFlag()){
						outputStream.write((c.getCommand()+"\n").getBytes());	
						String reString=reader.readLine();
						System.out.println(reString);
					}else{
						System.out.println("******************** 命令输入错误！ ********************");
					}
				}else{
					System.out.println("******************** 命令输入错误！ ********************");
				}
			}else if ("D-D".equals(commands[0])) {                  //删除department以及其内的person
				if(commands.length==2){
					Command c = Command.getCommand(commands[0], commands[1]);
					if(c.getFlag()){										
						outputStream.write((c.getCommand()+"\n").getBytes());
						String reString=reader.readLine();
						System.out.println(reString);
					}else{
						System.out.println("******************** 命令输入错误！ ********************");
					}
				}else{
					System.out.println("******************** 命令输入错误！ ********************");
				}
			}else if ("L-PA".equals(commands[0])) {                     //查询所有person的信息，除了departmentID
				if(commands.length==1){
					outputStream.write((command+"\n").getBytes());
					String sizeString=reader.readLine();
					if(sizeString!=null){
						int size=Integer.valueOf(sizeString);
						System.out.println("******************** person with his/her department ********************");
						for(int i=0;i<size;i++){
							String pString=reader.readLine();
							String dString=reader.readLine();
							PrintObject.printPersonWithDepartment(pString, dString);
						}
						
					}else{
						System.out.println("******************** 服务器停止！ ********************");
						break;
					}
				}else{
					System.out.println("******************** 命令输入错误！ ********************");
				}
			}else if ("L-DA".equals(commands[0])) {   //查询所有department
				if(commands.length==1){
					outputStream.write((command+"\n").getBytes());
					String sizeString=reader.readLine();
					if(sizeString!=null){
						int size=Integer.valueOf(sizeString);
						System.out.println("******************** department ********************");
						for(int i=0;i<size;i++){
							String dString=reader.readLine();
							PrintObject.printDepartment(dString);
						}						
					}else{
						System.out.println("******************** 服务器停止！ ********************");						
					}
				}else{
					System.out.println("******************** 命令输入错误！ ********************");
				}
			}else if ("L-PD".equals(commands[0])) {   //查询一个department所有person的所有属性
				if(commands.length==2){	
					Command c=Command.getCommand(commands[0], commands[1]);
					if(c.getFlag()){
						outputStream.write((c.getCommand()+"\n").getBytes());
						String sizeString=reader.readLine();
						if(sizeString!=null){
							int size=Integer.valueOf(sizeString);
							System.out.println("******************** person ********************");
							for(int i=0;i<size;i++){
								String pString=reader.readLine();
								PrintObject.printPerson(pString);
							}								
						}else{
							System.out.println("******************** 服务器停止！ ********************");													
						}
					}else{
						System.out.println("******************** 命令输入错误！ ********************");
					}
				}else{
					System.out.println("******************** 命令输入错误！ ********************");
				}
			}else if ("Q".equals(commands[0])){
				outputStream.write((command+"\n").getBytes());
				break;
			}else{
				System.out.println("******************** 命令输入错误！ ********************");
			}
			
		}
		reader.close();
		outputStream.close();
		socket.close();
	}

}
//A-P pName:john,tel:15625856958,dId:10,birthday:1988-11-02,salary:10000
//A-D dName:Test2,city:ZhuHai
//U-P pName:john,tel:15625856958,dId:10,birthday:1988-11-02,salary:10000,pId:206
//U-D dName:Test2,city:ZhuHai,dId:15
//D-P pId:206
//D-D dId:16
//L-DA
//L-PD dId:1
//L-PA 