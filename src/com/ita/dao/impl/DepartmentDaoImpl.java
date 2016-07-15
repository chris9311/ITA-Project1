package com.ita.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ita.dao.DepartmentDao;
import com.ita.model.Department;
import com.ita.util.DbUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public int addDepartment(Department d) {
		String sql = "insert into depart (did,dname,city) values (depart_seq01.nextval,?,?)";
		Connection con = null;
		PreparedStatement pst = null;
		con = DbUtil.connect();
		int m = 0;
		try {
			pst = con.prepareStatement(sql);
			con.setAutoCommit(false);
			pst.setString(1, d.getdName());
			pst.setString(2, d.getCity());
			m = pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}

		return m;
	}

	@Override
	public int deleteDepartment(int id) {
		String sql = "delete from depart where did=?";
		Connection con = null;
		PreparedStatement pst = null;
		con = DbUtil.connect();
		int m = 0;
		try {
			pst = con.prepareStatement(sql);
			con.setAutoCommit(false);
			pst.setInt(1, id);

			m = pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}

		return m;
	}

	@Override
	public int updateDepartment(Department d) {
		String sql = "update depart set dname=?,city=? where did=?";
		Connection con = null;
		PreparedStatement pst = null;
		con = DbUtil.connect();
		int m = 0;
		try {
			pst = con.prepareStatement(sql);
			con.setAutoCommit(false);
			pst.setString(1, d.getdName());
			pst.setString(2, d.getCity());
			pst.setInt(3, d.getdId());
			m = pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}

		return m;
	}

	@Override
	public List<Department> showAllDepartment() {
		List<Department> ds = new ArrayList<Department>();

		String sql = "select * from depart";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = DbUtil.connect();
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int dId = rs.getInt("did");
				String dName = rs.getString("dname");
				String city = rs.getString("city");
				Department department=new Department(dName,city);
				department.setdId(dId);
				ds.add(department);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}
		return ds;
	}

	@Override
	public Department loadDepartment(int id) {
		Department d=null;
		String sql = "select * from customer where id=?";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = DbUtil.connect();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String dName = rs.getString("dname");
				String city=rs.getString("city");
				d = new Department(dName, city);
				d.setdId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}
		return d;
	}
	
//	public static void main(String[] args) {
//		DepartmentDao departmentDao=new DepartmentDaoImpl();
//		Department department=null;
//		String[] strings={"Finance","HumanResources","Marketing","Production",
//				"Information","Securities","Technology","Administrative","NetSales",
//				" GeneralManagemen","Compliance","Design","Logistics","Test"};
//		String[] strings2={"Hongkong","ZhuHai","ShangHai","Manila","SiliconValley"};
//		for(int i=0;i<strings.length;i++){
//			department=new Department(strings[i], strings2[(int)(Math.random()*strings2.length)]);
//				System.out.println(department);
//			departmentDao.addDepartment(department);
//		}
//	}
}
