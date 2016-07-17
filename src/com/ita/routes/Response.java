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

	public void sendHtml(String fileName){
		try {
			this.outputStream.write(this.header.getBytes());
			this.outputStream.write(this.contentType.getBytes());
			this.outputStream.write("\r\n".getBytes());
			this.outputStream.write(HtmlUtil.readHtmlFile(fileName, this.attribute).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				this.outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
	
	public void sendString(String msg) {
		try {
			this.outputStream.write(this.header.getBytes());
			this.outputStream.write(this.contentType.getBytes());
			this.outputStream.write("\r\n".getBytes());
			this.outputStream.write(msg.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				this.outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
