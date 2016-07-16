package com.ita.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.ita.routes.Router;
import com.ita.util.HtmlUtil;

public class HttpServer extends Server {

	public HttpServer(Socket socket) {
		super(socket);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void connect() {
	
	Router router = new Router(socket);
	router.addRoute();
	try {
		router.analyzeRequest();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		connect();
	}

}
