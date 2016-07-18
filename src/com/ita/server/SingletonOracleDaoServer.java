package com.ita.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ita.client.OracleDaoClient;

public class SingletonOracleDaoServer{
	
	private static SingletonOracleDaoServer sos = new SingletonOracleDaoServer();
	private static ServerSocket serverSocket;
	private static boolean serverIsOpen = false;
	public static List<OracleDaoClient> clientList = new ArrayList<OracleDaoClient>();
	public static Date serverStartTime;

	public SingletonOracleDaoServer() {
	}
	
	
	
	public static boolean isServerIsOpen() {
		return serverIsOpen;
	}



	public static void stopServer() {
		if(serverIsOpen){
			try {
				serverIsOpen = false;
				for (OracleDaoClient oracleDaoClient : clientList) {
					oracleDaoClient.getSocket().close();
				}
				clientList.clear();
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
				serverStartTime = new Date();
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
							clientList.add(new OracleDaoClient(socket.getInetAddress().toString(), new Date(), socket.hashCode(), socket));
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
	
	public static void clientClose(Socket socket){
		for (int i = 0; i < clientList.size(); i++) {
			if(socket.hashCode() == clientList.get(i).getSocketHashCode()){
				try {
					clientList.get(i).getSocket().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				clientList.remove(i);
				break;
			}
		}
	}

}
