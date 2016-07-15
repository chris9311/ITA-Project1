package com.ita.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {
		
	private static final String url = "jdbc:oracle:thin:@ZHA-ITA129-W7.corp.oocl.com:1521:xe";
	private static final String username = "MIHE";
	private static final String password = "MIHE";
	private static final String driverClass = "oracle.jdbc.OracleDriver";
	public static Connection connect(){
		Connection con=null;
		try {
			Class.forName(driverClass);
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void free(Connection con,PreparedStatement pst,ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
			if(pst!=null){
				pst.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(DbUtil.connect());
	}
	
}
