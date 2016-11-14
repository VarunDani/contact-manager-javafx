package edu.utdallas.db.model;

/**
 * This class will work as Model Class For Phone Number Details
 * 
 * @since v1.0
 * @version 1.0
 * @author Varun Dani
 *
 */
public class PhoneNumber {

	private String phoneNumberId;
	private String phoneNumber;
	
	/**
	 * Parameterized Constructor 
	 * 
	 * @param phoneNumberId
	 * @param phoneNumber
	 */
	public PhoneNumber(String phoneNumberId, String phoneNumber) {
		super();
		this.phoneNumberId = phoneNumberId;
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneNumberId() {
		return phoneNumberId;
	}
	public void setPhoneNumberId(String phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

	@Override
	public String toString() {
		return "PhoneNumber [phoneNumberId=" + phoneNumberId + ", phoneNumber=" + phoneNumber + "]";
	}
	
}
