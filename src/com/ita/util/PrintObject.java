package com.ita.util;

import com.ita.model.Department;
import com.ita.model.Person;

public class PrintObject {
	
	public static void printPersonWithDepartment(String pString,String dString){
		Person person=Person.stringToPerson(pString);
		Department department=Department.stringToDepartment(dString);
		System.out.println("pid:"+person.getpId()+"  -  "+"pname:"+person.getpName()+"  -  "+"birthday:"
				+person.getBirthday().toString()+"  -  "+"tel:"+person.getTel()+"  -  "+"salary:"+person.getSalary()+
				"  -  "+"dname:"+department.getdName()+"  -  "+"city:"+department.getCity());
	}
	
	public static void printDepartment(String dString){
		Department department=Department.stringToDepartment(dString);
		System.out.println("did:"+department.getdId()+"  -  "+"dname:"+department.getdName()+"  -  "+"city:"+department.getCity());
		
	}
	public static void printPerson(String pString){
		Person person=Person.stringToPerson(pString);
		System.out.println("pid:"+person.getpId()+"  -  "+"pname:"+person.getpName()+"  -  "+"birthday:"
				+person.getBirthday().toString()+"  -  "+"tel:"+person.getTel()+"  -  "+
				"did:"+person.getdId()+"  -  "+"salary:"+person.getSalary());
		
	}

}
