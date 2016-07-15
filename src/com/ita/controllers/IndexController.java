package com.ita.controllers;

import java.io.IOException;

import com.ita.routes.Request;
import com.ita.routes.Response;

public class IndexController {
	
	public IndexController() {
		// TODO Auto-generated constructor stub
	}

	public void index(Request request, Response response){
		try {
			response.setAttribute("title", "this is title");
			response.setAttribute("value", "this is value");
			response.send("index");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
