package com.ita.client;

import java.util.Date;

public class OracleDaoClient {
	
	private String host;
	private Date startTime;
	private int socketHashCode;
	
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

	public OracleDaoClient(String host, Date startTime, int socketHashCode) {
		super();
		this.host = host;
		this.startTime = startTime;
		this.socketHashCode = socketHashCode;
	}

	public int getSocketHashCode() {
		return socketHashCode;
	}

}
