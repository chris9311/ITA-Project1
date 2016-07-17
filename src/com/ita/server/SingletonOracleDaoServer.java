package com.ita.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ita.client.OracleDaoClient;

public class SingletonOracleDaoServer{
	
	private static SingletonOracleDaoServer sos = new SingletonOracleDaoServer();
	private static ServerSocket serverSocket;
	private static boolean serverIsOpen = false;
	private static List<OracleDaoClient> clientList = new ArrayList<OracleDaoClient>();
	private static Thread thread;
	
	public SingletonOracleDaoServer() {
	}
	
	
	
	public static boolean isServerIsOpen() {
		return serverIsOpen;
	}



	public static void stopServer() {
		if(serverIsOpen){
			try {
				serverIsOpen = false;
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void startServer() {
		if(!serverIsOpen){
			try {
				serverSocket = new ServerSocket(8081);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			serverIsOpen = true;
			new Thread(){
				public void run() {
					while (true) {
						if( serverIsOpen!= true) break;
						try {
							Socket socket = serverSocket.accept();
							new Thread(new OracleDaoServer(socket)).start();
							clientList.add(new OracleDaoClient(socket.getInetAddress().toString(), new Date()));
						} catch (SocketException se) {
							// TODO: handle exception
							System.out.println(" ******************** Tcp Server is closed ********************");
						}catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();							
						}
					}
				};
			}.start();
		}
	}

	public static SingletonOracleDaoServer getInstance () {
		return sos;
	}
	

}
