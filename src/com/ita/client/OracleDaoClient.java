package com.ita.client;

import java.net.Socket;
import java.util.Date;

public class OracleDaoClient {
	
	private String host;
	private Date startTime;
	private int socketHashCode;
	private Socket socket;
	
	public Socket getSocket() {
		return socket;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public OracleDaoClient(String host, Date startTime, int socketHashCode, Socket socket) {
		super();
		this.host = host;
		this.startTime = startTime;
		this.socketHashCode = socketHashCode;
		this.socket = socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public int getSocketHashCode() {
		return socketHashCode;
	}

}
