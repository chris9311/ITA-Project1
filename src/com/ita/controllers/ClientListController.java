package com.ita.controllers;

import com.ita.routes.Request;
import com.ita.routes.Response;

public class ClientListController {

	public ClientListController() {
		// TODO Auto-generated constructor stub
	}
	
	public void index(Request request, Response response) {
		response.setAttribute("title", "Client List");
		response.setAttribute("value", "This is a list of online client.");
		response.sendHtml("clientList");
	}
}
