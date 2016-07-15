package com.ita.routes;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.ita.util.HtmlUtil;

public class Response {
	
	private String header = "HTTP/10.0 200 OK\r\n";
	private String contentType = "content-Type:text/html;charset=utf8\r\n";
	private OutputStream outputStream;
	private Map<String, String> attribute;

	public Response(OutputStream outputStream) {
		super();
		this.outputStream = outputStream;
		this.attribute = new HashMap<String, String>();
	}
	
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getAttribute(String key) {
		return this.attribute.get(key);
	}

	public void setAttribute(String key, String value) {
		this.attribute.put(key, value);
	}

	public void send(String fileName) throws IOException{
		this.outputStream.write(HtmlUtil.readHtmlFile(fileName, this.attribute).getBytes());
	}

}
