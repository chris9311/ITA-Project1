package com.ita.controllers;

import java.util.List;

import com.ita.client.OracleDaoClient;
import com.ita.routes.Request;
import com.ita.routes.Response;
import com.ita.server.SingletonOracleDaoServer;

public class ClientListController {

	public ClientListController() {
		// TODO Auto-generated constructor stub
	}
	
	public void index(Request request, Response response) {
		response.setAttribute("title", "Client List");
//		response.setAttribute("value", clientListStr.toString());
		response.sendHtml("clientList");
	}
	
	public void getClientList(Request request, Response response) {
		List<OracleDaoClient> clientList = SingletonOracleDaoServer.clientList;
		StringBuffer clientListJson = new StringBuffer();
		if(clientList.size() == 0){
			clientListJson.append("<h3>No clients online! </h3>");
		}else {
			for (int i = 0; i < clientList.size(); i++) {
//			if( i == 0 && clientList.size() == 1){
//				clientListJson.append("[{'host':'" + clientList.get(i).getHost() + "','startTime':'" +clientList.get(i).getStartTime()+"'}]");
//			}else if (i == 0) {
//				clientListJson.append("[{'host':'" + clientList.get(i).getHost() + "','startTime':'" +clientList.get(i).getStartTime()+"'},");
//			}else if(i == clientList.size() - 1){
//				clientListJson.append("{'host':'" + clientList.get(i).getHost() + "','startTime':'" +clientList.get(i).getStartTime()+"'}]");
//			}else {				
//				clientListJson.append("{'host':'" + clientList.get(i).getHost() + "','startTime':'" +clientList.get(i).getStartTime()+"'},");
//			}
				clientListJson.append("Host : " + clientList.get(i).getHost().substring(1,clientList.get(i).getHost().length()) + " --------------- Start Time : " + clientList.get(i).getStartTime() + "<br/>");
			}
		}
		response.sendString(clientListJson.toString());
	}
}
