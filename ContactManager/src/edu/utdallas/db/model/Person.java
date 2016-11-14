package edu.utdallas.db.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * This Person Modal Class 
 * It will Person Both Operation of working as Plain Java Object 
 * and providing object container for Person Info Table View for JavaFXs 
 * 
 * @since v1.0
 * @version 1.0
 * @author Varun Dani
 *
 */
public class Person {

	
	
	private SimpleStringProperty id;
	private SimpleStringProperty firstName;
	private SimpleStringProperty middleInitial;
	private SimpleStringProperty lastName;
	private SimpleStringProperty birthDate;
	private SimpleStringProperty sex;
	
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param id
	 * @param firstName
	 * @param middleInitial
	 * @param lastName
	 * @param birthDate
	 * @param sex
	 */
	public Person(String id, String firstName, String middleInitial, String lastName, String birthDate, String sex) {
		super();
		this.id = new SimpleStringProperty(id);
		this.firstName = new SimpleStringProperty(firstName);
		this.middleInitial = new SimpleStringProperty(middleInitial);
		this.lastName = new SimpleStringProperty(lastName);
		this.birthDate = new SimpleStringProperty(birthDate);
		this.sex = new SimpleStringProperty(sex);
	}
	
	
	public String getId() {
		return id.get();
	}
	public void setId(String id) {
		this.id.set(id);
	}
	public String getFirstName() {
		return firstName.get();
	}
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	public String getMiddleInitial() {
		return middleInitial.get();
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial.set(middleInitial);
	}
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public String getBirthDate() {
		return birthDate.get();
	}
	public void setBirthDate(String birthDate) {
		this.birthDate.set(birthDate);
	}
	public String getSex() {
		return sex.get();
	}
	public void setSex(String sex) {
		this.sex.set(sex);
	}
	
	//Property Methods
	public StringProperty idProperty() {
          return this.id;
    }
	public StringProperty firstNameProperty() {
        return this.firstName;
	}
	public StringProperty middleInitialProperty() {
        return this.middleInitial;
	}
	public StringProperty lastNameProperty() {
        return this.lastName;
	}
	public StringProperty birthDateProperty() {
        return this.birthDate;
	}
	public StringProperty sexProperty() {
        return this.sex;
	}
	
	  
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName="
				+ lastName + ", birthDate=" + birthDate + ", sex=" + sex + "]";
	}
	
	
	
}
