package edu.utdallas.db.model;


/**
 * This class will work as Model Class For Email Details
 * 
 * @since v1.0
 * @version 1.0
 * @author Varun Dani
 *
 */
public class EmailId {

	private String emailIdKey;
	private String emailId;
	
	/**
	 * Parameterized Constructor 
	 * 
	 * @param emailIdKey
	 * @param emailId
	 */
	public EmailId(String emailIdKey, String emailId) {
		super();
		this.emailIdKey = emailIdKey;
		this.emailId = emailId;
	}
	
	
	public String getEmailIdKey() {
		return emailIdKey;
	}
	public void setEmailIdKey(String emailIdKey) {
		this.emailIdKey = emailIdKey;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Override
	public String toString() {
		return "EmailId [emailIdKey=" + emailIdKey + ", emailId=" + emailId + "]";
	}
	
	
	
}
