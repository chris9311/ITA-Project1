package com.ita.controllers;

import java.io.IOException;

import com.ita.routes.Request;
import com.ita.routes.Response;

public class ServerController {

	public ServerController() {
		// TODO Auto-generated constructor stub
	}
	
	public void startServer(Request request, Response response) {
		response.setAttribute("title", "this is server Controller");
		response.setAttribute("value", "this is Server Controller value");
		try {
			response.send("index");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
