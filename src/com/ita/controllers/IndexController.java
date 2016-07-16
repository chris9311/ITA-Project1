package com.ita.controllers;

import com.ita.routes.Request;
import com.ita.routes.Response;

public class IndexController {
	
	public IndexController() {
	}

	public void index(Request request, Response response){

		response.setAttribute("title", "Index");
		response.setAttribute("value", "You can start or stop server in Server Controller.");
		response.sendHtml("index");
	}
}
