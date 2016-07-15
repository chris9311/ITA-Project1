package com.ita.routes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Router {
	
	public Socket socket;
	private Map<String, String> routeMap;

	public Router(Socket socket) {
		super();
		this.socket = socket;
		this.routeMap = new HashMap<String, String>();
	}

	public void addRoute(String route, String controllerName){
		this.routeMap.put(route, controllerName);
	}
	
	public void analyzeRequest() throws Exception{
		OutputStream outputStream = null;
//		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		outputStream = this.socket.getOutputStream();
//		inputStream = this.socket.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(this.socket.getInputStream());
		
		bufferedReader = new BufferedReader(inputStreamReader);
//		int m = 0;
//		int i = 0;
//		byte[] bs = new byte[100];		
//		while ((m = inputStream.read(bs)) != -1) {
//			System.out.println(new String(bs) + " --------------" + i++);
//		}
		
		String line = null;
		line = bufferedReader.readLine();
		System.out.println("first line : " + line);
		String resource=line.substring(line.indexOf('/'),line.lastIndexOf('/')-5);
		resource=URLDecoder.decode(resource, "UTF-8");
		System.out.println("resource : " + resource);
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
		System.out.println("method : " + method);
		Request request;
		Response response;
		System.out.println(" ************************************************************************* ");
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
			if(line.equals("")) break;
		}
		System.out.println("--------------------------------------------------------------------");
		if("POST".equalsIgnoreCase(method)) {
            System.out.println(bufferedReader.readLine());
        }
		
		System.out.println(" ************************************************************************* ");
		request = new Request(method, params);
		response = new Response(outputStream);
		routeForwarding(url,request, response);
	}
		
	public void routeForwarding(String url, Request request, Response response) throws Exception, SecurityException{
		System.out.println("/////////////////" + url );
		Class<?> cClass = null;
		String className = "Index";
		String methodName = "index";
		if(!url.equals("/") && this.routeMap.get(url).contains(".")){
			methodName = this.routeMap.get(url).split(".")[1];
			className = this.routeMap.get(url).split(".")[0];
		}
		cClass = Class.forName(className + "Controller");
		Object object = cClass.newInstance();
//		Object object = cClass.getInterfaces();
//		Method[] ms = cClass.getDeclaredmethods();
//		A a = c.newInstance();
		Method method = cClass.getMethod(methodName, request.getClass(), response.getClass());
		method.setAccessible(true);//因为写成private 所以这里必须设置
		method.invoke(object, request, response);
	};
}
