package com.ita.routes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

public class Router {
	
	public Socket socket;
	private Map<String, String> routeMap;

	public Router(Socket socket) {
		super();
		this.socket = socket;
		this.routeMap = new HashMap<String, String>();
	}

	public void addRoute(){
		Properties list = new Properties();
		try {
			list.load(new FileInputStream("./router.properties"));
		} catch (FileNotFoundException e) {
			System.out.println("error : configuration file not fonud");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Set<String> urlList = list.stringPropertyNames();
		for (String url : urlList) {
			String controller = list.getProperty(url);			
			this.routeMap.put(url, controller);
		}
	}
	
	public void analyzeRequest() throws Exception{
		OutputStream outputStream = null;
		BufferedReader bufferedReader = null;
		outputStream = this.socket.getOutputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(this.socket.getInputStream());
		bufferedReader = new BufferedReader(inputStreamReader);
		String line = null;
		line = bufferedReader.readLine();
		System.out.println(" ********** http request header : " + line);
		if(line != null){			
			String resource=line.substring(line.indexOf('/'),line.lastIndexOf('/')-5);
			resource=URLDecoder.decode(resource, "UTF-8");
//			System.out.println("resource : " + resource);
			Map<String, String> params = new HashMap<String, String>();
			String url = resource;
			if(resource.contains("?")){
				url = resource.split("?")[0];
				String paramString = resource.split("?")[1];
				if(paramString.split("&").length > 0){				
					String[] paramsStr = paramString.split("&");
					for (String param : paramsStr) {
						params.put(param.split("=")[0], param.split("=")[1]);
					}
				}
			}
			String method = new StringTokenizer(line).nextElement().toString();
//			System.out.println("method : " + method);
			Request request;
			Response response;
			while ((line = bufferedReader.readLine()) != null) {
//				System.out.println(line);
				if(line.equals("")) break;
			}
//			if("POST".equalsIgnoreCase(method)) {
//				System.out.println(bufferedReader.readLine());
//			}
//			bufferedReader.close();
			request = new Request(method, params);
			response = new Response(outputStream);
			routeForwarding(url,request, response);
		}
	}
		
	public void routeForwarding(String url, Request request, Response response) throws Exception, SecurityException{
		Class<?> cClass = null;
		String className = "Index";
		String methodName = "index";
		if(!url.equals("/") && !url.equals("/favicon.ico")){
			String cm = this.routeMap.get(url);
			if(cm.contains(".")){				
				methodName = cm.split("\\.")[1];
				className = cm.split("\\.")[0];
			}else {
				className = cm;
			}
		}
		cClass = Class.forName("com.ita.controllers." + className + "Controller");
		Method method = cClass.getMethod(methodName, Request.class, Response.class);
		method.invoke(cClass.newInstance(), request, response);
	};
}
