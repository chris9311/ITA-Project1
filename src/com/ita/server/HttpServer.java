package com.ita.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpServer extends Server {

	public HttpServer(Socket socket) {
		super(socket);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void connect() {
		
	System.out.println("******************** Http Server Start ********************");
		// TODO Auto-generated method stub
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			outputStream = socket.getOutputStream();
			inputStream = socket.getInputStream();
			outputStream.write("HTTP/10.0 200 OK\r\n".getBytes());
			outputStream.write("content-Type:text/html;charset=utf8\r\n".getBytes());
			outputStream.write("\r\n".getBytes());
			outputStream.write("hello".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				outputStream.close();
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		connect();
	}

}
