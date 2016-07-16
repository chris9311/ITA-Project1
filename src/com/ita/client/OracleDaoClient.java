package com.ita.client;

import java.util.Date;

public class OracleDaoClient {
	
	private String host;
	private Date startTime;
	
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

	public OracleDaoClient(String host, Date startTime) {
		super();
		this.host = host;
		this.startTime = startTime;
	}
	
	

}
