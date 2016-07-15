package com.ita.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class OracleDaoServer extends Server {

	public OracleDaoServer(Socket socket) {
		super(socket);
	}

	@Override
	public void connect() {
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			outputStream = socket.getOutputStream();
			inputStream = socket.getInputStream();
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
		connect();
	}

}
