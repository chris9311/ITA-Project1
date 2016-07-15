package com.ita.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import com.ita.server.HttpServer;

public class ServerStart {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = new ServerSocket(8088);
		while (true) {			
			Socket socket = serverSocket.accept();
			new Thread(new HttpServer(socket)).start();
		}
	}

}
