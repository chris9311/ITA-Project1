package com.ita.controllers;

import com.ita.routes.Request;
import com.ita.routes.Response;
import com.ita.server.SingletonOracleDaoServer;

public class ServerController {

	public ServerController() {
		
	}
	
	public void serverCtrl (Request request, Response response) {
		
		if (SingletonOracleDaoServer.isServerIsOpen()) {			
			response.setAttribute("serverStatus", "Stop");
		}else {
			response.setAttribute("serverStatus", "Start");
		}
		response.setAttribute("title", "Server Controller");
		response.setAttribute("value", "You can start or stop Server with the button.");
		response.sendHtml("serverCtrl");
	}
	
	public void changeStatus(Request request, Response response) {
		String serverStatus = "";
		if ( SingletonOracleDaoServer.isServerIsOpen() ) {			
			SingletonOracleDaoServer.stopServer();
			serverStatus = "Start";
		}else {
			SingletonOracleDaoServer.startServer();
			serverStatus = "Stop";
		}
		response.setAttribute("title", "Server Controller");
		response.setAttribute("value", "You can start or stop Server with the button.");
		response.sendString(serverStatus);
	}
}
