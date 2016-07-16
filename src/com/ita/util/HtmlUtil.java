package com.ita.util;

import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
//import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {
	
	public static final String filePath = "./client/html/"; 
	
//	public static void response(Socket socket,String fileName, Map<String, String> elements){
//		OutputStream outputStream = null;
//		InputStream inputStream = null;
//		try {
//			outputStream = socket.getOutputStream();
//			inputStream = socket.getInputStream();
//			outputStream.write("HTTP/10.0 200 OK\r\n".getBytes());
//			outputStream.write("content-Type:text/html;charset=utf8\r\n".getBytes());
//			outputStream.write("\r\n".getBytes());
//			outputStream.write(readHtmlFile(fileName, elements).getBytes());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			try {
//				outputStream.close();
//				inputStream.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	public static String readHtmlFile(String fileName, Map<String, String> elements) throws IOException{
		
		if(! fileName.matches(".html | .htm")) 
			fileName = fileName + ".html"; 
		BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath + fileName));
		String line = null;
		StringBuffer htmlPage = new StringBuffer();
		while ((line = bufferedReader.readLine()) != null) {
			htmlPage.append(replaceElement(line, elements));
		}
		bufferedReader.close();
		return htmlPage.toString();
	}
	
	public static String replaceElement(String html, Map<String, String> elements){
		
		String ReturnHtml = html;
		String regex = "(\\{\\{(.+?)\\}\\})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(html);
		while (matcher.find()) {
			ReturnHtml = ReturnHtml.replace(matcher.group(1), elements.get(matcher.group(2)));
		}
//		System.out.println(ReturnHtml);
		return ReturnHtml;
	}
}
