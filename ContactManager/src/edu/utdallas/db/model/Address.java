package edu.utdallas.db.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * This Address Modal Class 
 * It will Person Both Operation of working as Plain Java Object 
 * and providing object container for Contact Info Table View for JavaFXs 
 * 
 * @since v1.0
 * @version 1.0
 * @author Varun Dani
 *
 */
public class Address {

	private SimpleStringProperty id;
	private SimpleStringProperty personId;
	private SimpleStringProperty address1;
	private SimpleStringProperty address2;
	private SimpleStringProperty city;
	private SimpleStringProperty state;
	private SimpleStringProperty country;
	private SimpleStringProperty zip;
	private SimpleStringProperty contactDate;
	private SimpleStringProperty phoneNumber;
	private SimpleStringProperty emailAddress;
	private SimpleStringProperty phoneNumberId;
	private SimpleStringProperty emailAddressId;
	

	/**
	 * 
	 * Parameterized Constructor
	 * 
	 * @param id
	 * @param personId
	 * @param address1
	 * @param address2
	 * @param city
	 * @param state
	 * @param country
	 * @param zip
	 * @param contactDate
	 * @param phoneNumber
	 * @param emailAddress
	 * @param phoneNumberId
	 * @param emailAddressId
	 */
	public Address(String id, String personId, String address1, String address2, String city, String state,
			String country, String zip, String contactDate,String phoneNumber,String emailAddress,String phoneNumberId,String emailAddressId) {
		super();
		this.id = new SimpleStringProperty(id);
		this.personId = new SimpleStringProperty(personId);
		this.address1 = new SimpleStringProperty(address1);
		this.address2 = new SimpleStringProperty(address2);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.country = new SimpleStringProperty(country);
		this.zip = new SimpleStringProperty(zip);
		this.contactDate = new SimpleStringProperty(contactDate);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.emailAddress = new SimpleStringProperty(emailAddress);
		this.phoneNumberId = new SimpleStringProperty(phoneNumberId);
		this.emailAddressId = new SimpleStringProperty(emailAddressId);
	}


	/**
	 * Getter Setter Methods for all string Properties
	 * 
	 */
	public String getId() {
		return id.get();
	}
	public void setId(String id) {
		this.id.set(id);
	}
	public String getAddress1() {
		return address1.get();
	}
	public void setAddress1(String address1) {
		this.address1.set(address1);
	}
	public String getAddress2() {
		return address2.get();
	}
	public void setAddress2(String address2) {
		this.address2.set(address2);
	}
	public String getCity() {
		return city.get();
	}
	public void setCity(String city) {
		this.city.set(city);
	}
	public String getState() {
		return state.get();
	}
	public void setState(String state) {
		this.state.set(state);
	}
	public String getCountry() {
		return country.get();
	}
	public void setCountry(String country) {
		this.country.set(country);
	}
	public String getZip() {
		return zip.get();
	}
	public void setZip(String zip) {
		this.zip.set(zip);
	}
	public String getPersonId() {
		return personId.get();
	}
	public void setPersonId(String personId) {
		this.personId.set(personId);
	}
	public String getContactDate() {
		return contactDate.get();
	}
	public void setContactDate(String contactDate) {
		this.contactDate.set(contactDate);
	}
	public String getPhoneNumber() {
		return phoneNumber.get();
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	public String getEmailAddress() {
		return emailAddress.get();
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress.set(emailAddress);
	}
	public String getPhoneNumberId() {
		return phoneNumberId.get();
	}
	public void setPhoneNumberId(String phoneNumberId) {
		this.phoneNumberId.set(phoneNumberId);
	}
	public String getEmailAddressId() {
		return emailAddressId.get();
	}
	public void setEmailAddressId(String emailAddressId) {
		this.emailAddressId.set(emailAddressId);
	}
	
	//Property Methods
	public StringProperty idProperty() {
          return this.id;
    }
	public StringProperty personIdProperty() {
        return this.personId;
	}
	public StringProperty address1Property() {
        return this.address1;
	}
	public StringProperty address2Property() {
        return this.address2;
	}
	public StringProperty cityProperty() {
        return this.city;
	}
	public StringProperty stateProperty() {
        return this.state;
	}
	public StringProperty countryProperty() {
        return this.country;
	}
	public StringProperty zipProperty() {
        return this.zip;
	}
	public StringProperty contactDateProperty() {
        return this.contactDate;
	}
	public StringProperty phoneNumberProperty() {
        return this.phoneNumber;
	}
	public StringProperty emailAddressProperty() {
        return this.emailAddress;
	}
	public StringProperty phoneNumberIdProperty() {
        return this.phoneNumberId;
	}
	public StringProperty emailAddressIdProperty() {
        return this.emailAddressId;
	}
	
	
	/**
	 * Overriden toString Method	
	 */
	@Override
	public String toString() {
		return "Address [id=" + id + ", personId=" + personId + ", address1=" + address1 + ", address2=" + address2
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", zip=" + zip + ", contactDate="
				+ contactDate + "]";
	}
	
	
	
}
