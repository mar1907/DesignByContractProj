package model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import printables.Printable;

public class Person implements Serializable, Observer{
	private static final long serialVersionUID = -586813203655590642L;
	private static Integer idAvailable;
	private Printable printable;
	private String name;
	private String email;
	private Integer id;
	
	public Person(String name, String email){
		this.name=name;
		this.email=email;
		id=idAvailable++;
	}
	
	public Person(int id, String name, String email){
		this.name=name;
		this.email=email;
		this.id=id;
	}
	
	public Person(int id){
		this.id=id;
	}

	/**
	 * @return the id
	 */
	public final Integer getId() {
		return id;
	}

	/**
	 * @return the idAvailable
	 */
	public static final Integer getIdAvailable() {
		return idAvailable;
	}

	/**
	 * @param idAvailable the idAvailable to set
	 */
	public static final void setIdAvailable(Integer idAvailable) {
		Person.idAvailable = idAvailable;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	
	/**
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Person))return false;
	    Person otherPerson = (Person)other;
	    return (otherPerson.id.equals(this.id));
	}

	public void update(Observable o, Object arg) {
		printable.print("I am "+id+", my account has been modified: "+arg);
	}
	
	public void addPrintable(Printable p){
		printable=p;
	}
	
	public String toString(){
		return id.toString();
	}

}
