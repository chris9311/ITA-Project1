package com.ita.routes;

import java.util.Map;

public class Request {

	private String method = "GET";
	private Map<String, String> params;
	
	public Request(String method, Map<String, String> params) {
		super();
		this.method = method;
		this.params = params;
	}
	
	public String getParameter(String key) {
		String value = null;
		value =  this.params.get(key);
		return value;
	}
	
	public void setParameter(String key, String value) {
		this.params.replace(key, value);
	}
}
