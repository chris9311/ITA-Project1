package com.ita.dao;

import java.util.List;

import com.ita.model.Person;

public interface PersonDao {
	
	public int addPerson(Person p);
	public int deletePerson(int id);
	public int updatePerson(Person p);
	public List<Person> showAllPerson();
	public Person loadPerson(int id);
	public List<Person> showAllPersonOfADepartment(int did);
	
}
