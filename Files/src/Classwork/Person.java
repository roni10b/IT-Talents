package Classwork;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	private String mail;
	private ArrayList<Person> harem;
	
	public Person(String name, int age, String mail) {
		this.name = name;
		this.age = age;
		this.mail = mail;
		harem = new ArrayList<>();
	}
	
	public int getAge() {
		return age;
	}
	
	public String getName() {
		return name;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void addpplToHamem(Person haremcho) {
		harem.add(haremcho);
	}
}
