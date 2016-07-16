package com.ita.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ita.dao.PersonDao;
import com.ita.model.Department;
import com.ita.model.Person;
import com.ita.util.DbUtil;

public class PersonDaoImpl implements PersonDao {

	@Override
	public int addPerson(Person p) {
		String sql = "insert into person(pid,pname,birthday,tel,did,salary) values (person_seq01.nextval,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pst = null;
		con = DbUtil.connect();
		int m = 0;
		try {
			pst = con.prepareStatement(sql);
			con.setAutoCommit(false);
			pst.setString(1, p.getpName());
			pst.setDate(2, p.getBirthday());
			pst.setString(3, p.getTel());
			pst.setInt(4, p.getdId());
			pst.setInt(5, p.getSalary());
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
	public int deletePerson(int id) {
		String sql = "delete from person where pid=?";
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
	public int updatePerson(Person p) {
		String sql = "update person set pname=?,birthday=?,tel=?,did=?,salary=? where pid=?";
		Connection con = null;
		PreparedStatement pst = null;
		con = DbUtil.connect();
		int m = 0;
		try {
			pst = con.prepareStatement(sql);
			con.setAutoCommit(false);
			pst.setString(1, p.getpName());
			pst.setDate(2, p.getBirthday());
			pst.setString(3, p.getTel());
			pst.setInt(4, p.getdId());
			pst.setInt(5, p.getSalary());
			pst.setInt(6, p.getpId());
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
	public List<Person> showAllPerson() {
		List<Person> ps = new ArrayList<Person>();

		String sql = "select * from person";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = DbUtil.connect();
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			Person p=null;
			while (rs.next()) {
				int pId = rs.getInt("pid");
				String pName = rs.getString("pname");
				Date date=rs.getDate("birthday");
				String tel=rs.getString("tel");
				int dId=rs.getInt("did");
				int salary=rs.getInt("salary");
				p=new Person(pName, date, tel, dId, salary);
				p.setpId(pId);
				ps.add(p);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}
		return ps;
	}

	@Override
	public Person loadPerson(int id) {
		String sql = "select * from person where pid=?";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = DbUtil.connect();
		Person p=null;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				int pId = rs.getInt("pid");
				String pName = rs.getString("pname");
				Date date=rs.getDate("birthday");
				String tel=rs.getString("tel");
				int dId=rs.getInt("did");
				int salary=rs.getInt("salary");
				p = new Person(pName, date, tel, dId, salary);
				p.setpId(pId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}
		return p;
	}

	@Override
	public List<Person> showAllPersonOfADepartment(int did) {
		List<Person> ps = new ArrayList<Person>();

		String sql = "select * from person join depart on person.did = depart.did ";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = DbUtil.connect();
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			Person p=null;
			while (rs.next()) {
				if(rs.getInt("did")==did){					
					int pId = rs.getInt("pid");
					String pName = rs.getString("pname");
					Date date=rs.getDate("birthday");
					String tel=rs.getString("tel");
					int dId=rs.getInt("did");
					int salary=rs.getInt("salary");
					p=new Person(pName, date, tel, dId, salary);
					p.setpId(pId);
					ps.add(p);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}
		return ps;
	}

	@Override
	public Map<Person, Department> showPersonAndHisDepartment() {
		Map<Person,Department> map = new HashMap<Person,Department>();

		String sql = "select * from person join depart on person.did = depart.did ";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = DbUtil.connect();
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			Person p=null;
			Department d=null;
			while (rs.next()) {					
					int pId = rs.getInt("pid");
					String pName = rs.getString("pname");
					Date date=rs.getDate("birthday");
					String tel=rs.getString("tel");
					int dId=rs.getInt("did");
					int salary=rs.getInt("salary");
					p=new Person(pName, date, tel, dId, salary);
					p.setpId(pId);
					String dName=rs.getString("dName");
					String city=rs.getString("city");
					d=new Department(dName, city);
					d.setdId(dId);
					map.put(p, d);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DbUtil.free(con, pst, null);
		}
		return map;
	}

}
