package com.ita.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import com.ita.server.HttpServer;
import com.ita.server.OracleDaoServer;

public class ServerStart {

	public static void main(String[] args) throws IOException {

//		ServerSocket serverSocket = new ServerSocket(8088);
		ServerSocket serverSocker1 = new ServerSocket(8890);
		
		while (true) {			
//			Socket socket = serverSocket.accept();
//			new Thread(new HttpServer(socket)).start();
			Socket socket1= serverSocker1.accept();
			new Thread(new OracleDaoServer(socket1)).start();
		}
	}

}
