package edu.utdallas.db.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.utdallas.db.model.Address;
import edu.utdallas.db.model.EmailId;
import edu.utdallas.db.model.Person;
import edu.utdallas.db.model.PhoneNumber;
import edu.utdallas.db.utility.DateUtility;

/**
 * 
 * This Class provides All DB interface method for Address Object
 * This class will get connection from Connection Object and Perform various operation on 
 * Address Table in Database
 * 
 * @version 1.0
 * @since v1.0
 * @author Varun Dani
 *
 */
public class AddressUtility {

	/**
	 * This method will Insert new Address Entry in table company.contact_info
	 * 
	 * 
	 * @param person
	 * @return void
	 */
	public void addAddress(Address address)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		      
		try
		{
			 conn = ConnectionObj.getConnection();
		      stmt = conn.prepareStatement("INSERT INTO contact_info (`person_id`, `contact_date`, `address_line_1`, `address_line_2`, `city`, `state`, `country`, `zipcode`) values (?, curdate(), ?, ?, ?, ?, ?, ?)");
		      
		      stmt.setInt(1,Integer.parseInt(address.getPersonId()));
		      //stmt.setDate(2, new java.sql.Date(DateUtility.getDateObj(address.getContactDate()).getTime()));
		      stmt.setString(2, address.getAddress1());
		      stmt.setString(3, address.getAddress2());
		      stmt.setString(4, address.getCity());
		      stmt.setString(5, address.getState());
		      stmt.setString(6, address.getCountry());
		      stmt.setString(7, address.getZip());
		      
		      stmt.executeUpdate();
		      
		      // Interaction with Other Two Tables 
		      addPhoneNumber(address);
		      addEmailAddress(address);
		      
			
		}
		catch(Exception e )
		{
			System.out.println("Problem in Inserting into Address Object ");
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	
	/**
	 * This method will Insert new Phone Number Entry in table company.phone_number_info
	 * 
	 * 
	 * @param person
	 * @return void
	 */
	public void addPhoneNumber(Address address)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		      
		try
		{
			 conn = ConnectionObj.getConnection();
		      stmt = conn.prepareStatement("INSERT INTO phone_number_info (`person_id`, `phone_number`, `created_date`) values (?, ?, curdate())");
		      
		      stmt.setInt(1,Integer.parseInt(address.getPersonId()));
		      stmt.setInt(2,Integer.parseInt(address.getPhoneNumber()));
		      
		      stmt.executeUpdate();
		      
		      ResultSet rs = stmt.getGeneratedKeys();
		      if (rs.next()) {
		        int newId = rs.getInt(1);
		        address.setPhoneNumberId(String.valueOf(newId));
		      }
		      
		      
			
		}
		catch(Exception e )
		{
			System.out.println("Problem in Inserting into Phone Number  Object ");
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	/**
	 * This method will Insert new Email Address Entry in table company.email_id_info
	 * 
	 * 
	 * @param person
	 * @return void
	 */
	public void addEmailAddress(Address address)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		      
		try
		{
			 conn = ConnectionObj.getConnection();
		      stmt = conn.prepareStatement("INSERT INTO email_id_info (`person_id`, `email_id`, `created_date`) values (?, ?, curdate())");
		      
		      stmt.setInt(1,Integer.parseInt(address.getPersonId()));
		      stmt.setString(2,address.getEmailAddress());
		     
		      
		      stmt.executeUpdate();
		      
		      ResultSet rs = stmt.getGeneratedKeys();
		      if (rs.next()) {
		        int newId = rs.getInt(1);
		        address.setEmailAddressId(String.valueOf(newId));
		      }
		      
		}
		catch(Exception e )
		{
			System.out.println("Problem in Inserting into Emaail Address Object ");
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	/**
	 * This method will Update Address Info from given ID Parameter from screen
	 * 
	 * @param person
	 * @return void
	 */
	public void updateAddress(Address address)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		      
		try
		{
			 conn = ConnectionObj.getConnection();
		      stmt = conn.prepareStatement("UPDATE contact_info SET person_id=?,address_line_1=?,address_line_2=?,"
		      		+ "city=?,state=?,country=?,zipcode=? "
		      		+ " WHERE contact_info_id=?");

		      stmt.setInt(1,Integer.parseInt(address.getPersonId()));
		      //stmt.setDate(2, new java.sql.Date(DateUtility.getDateFromEle(address.getContactDate()).getTime()));
		      stmt.setString(2, address.getAddress1());
		      stmt.setString(3, address.getAddress2());
		      stmt.setString(4, address.getCity());
		      stmt.setString(5, address.getState());
		      stmt.setString(6, address.getCountry());
		      stmt.setString(7, address.getZip());
		      
		      stmt.setInt(8, Integer.parseInt(address.getId()));
		      
		      stmt.executeUpdate();
		      
		      
		      updateEmailAddress(address);
		      updatePhoneNumber(address);
			
		}
		catch(Exception e )
		{
			System.out.println("Problem in Updating Address Data ");
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	/**
	 * This method will Update Phone Number Info from given ID Parameter from screen
	 * 
	 * @param Adddress
	 * @return void
	 */
	public void updatePhoneNumber(Address address)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		      
		try
		{
			 conn = ConnectionObj.getConnection();
		      stmt = conn.prepareStatement("UPDATE phone_number_info SET person_id=?,phone_number=? "
		      		+ " WHERE idphone_number_info_id=?");

		      stmt.setInt(1,Integer.parseInt(address.getPersonId()));
		      stmt.setInt(2, Integer.parseInt(address.getPhoneNumber()));
		      
		      stmt.setInt(3, Integer.parseInt(address.getPhoneNumberId()));
		      
		      stmt.executeUpdate();
			
		}
		catch(Exception e )
		{
			System.out.println("Problem in Updating Address Data ");
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	/**
	 * This method will Update Phone Number Info from given ID Parameter from screen
	 * 
	 * @param Adddress
	 * @return void
	 */
	public void updateEmailAddress(Address address)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		      
		try
		{
			 conn = ConnectionObj.getConnection();
		      stmt = conn.prepareStatement("UPDATE email_id_info SET person_id=?,email_id=? "
		      		+ " WHERE email_id_info_key=?");

		      stmt.setInt(1,Integer.parseInt(address.getPersonId()));
		      stmt.setString(2, address.getEmailAddress());
		      
		      stmt.setInt(3, Integer.parseInt(address.getEmailAddressId()));
		      
		      stmt.executeUpdate();
			
		}
		catch(Exception e )
		{
			System.out.println("Problem in Updating Address Data ");
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	
	/**
	 * Search Person from database from search criteria
	 * 
	 * @param personId
	 * @return ArrayList<Address>
	 */
	public ArrayList<Address> getAddress(int personId)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery="";
		ArrayList<Address> addressList = new ArrayList<Address>();
		Address addressObj;
		try
		{
			 conn = ConnectionObj.getConnection();
			 sqlQuery = "select * from contact_info where person_id=?";
			 
		      stmt = conn.prepareStatement(sqlQuery);
		      stmt.setInt(1, personId);

		      rs = stmt.executeQuery();
		      
		      while (rs.next()) {
		    	  addressObj = new Address(rs.getString(1),rs.getString(2),rs.getString(4),
		    			  rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
		    			  rs.getString(9),rs.getString(3),"","","","");

		    	  addressList.add(addressObj);
		    	  
		      }
		      
		      try
		      { 
		    	 getPhoneNumberDetails(addressList, personId);
		    	 getEmailIdDetails(addressList, personId);
		      }
		     catch(Exception e )
		     {
		    	 System.out.println("Row Miss match in Fields ");
		     }
		      
		}
		catch(Exception e )
		{
			System.out.println("Problem in Searching Person Data ");
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return addressList;
	}
	
	
	/**
	 * This method will return Phone Number Details from Table phone_number_info based on person ID
	 * @param forPhoneDtls
	 * @param personId
	 */
	public void getPhoneNumberDetails(ArrayList<Address> forPhoneDtls,int personId)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery="";
		ArrayList<PhoneNumber> addressList = new ArrayList<PhoneNumber>();
		PhoneNumber addressObj;
		try
		{
			 conn = ConnectionObj.getConnection();
			 sqlQuery = "select * from phone_number_info where person_id=?";
			 
		      stmt = conn.prepareStatement(sqlQuery);
		      stmt.setInt(1, personId);

		      rs = stmt.executeQuery();
		      
		      while (rs.next()) {
		    	  addressObj = new PhoneNumber(rs.getString(1),rs.getString(3));
		    	  addressList.add(addressObj);
		    	  
		      }
		      
		     try
		     {
			      for (int i = 0; i < forPhoneDtls.size(); i++) {
			    	  forPhoneDtls.get(i).setPhoneNumberId(addressList.get(i).getPhoneNumberId());
			    	  forPhoneDtls.get(i).setPhoneNumber(addressList.get(i).getPhoneNumber());
				}
			 }
		      catch(Exception e)
		      {
		    	  System.out.println("Size Miss match in Table Phone Number ");
		      }
	      
		}
		catch(Exception e )
		{
			System.out.println("Problem in Searching Person Data ");
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	/**
	 * This method will give Email Address details from table email_id_info from person ID
	 * 
	 * 
	 * @param forEmailDetails
	 * @param personId
	 */
	public void getEmailIdDetails(ArrayList<Address> forEmailDetails,int personId)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlQuery="";
		ArrayList<EmailId> addressList = new ArrayList<EmailId>();
		EmailId addressObj;
		try
		{
			 conn = ConnectionObj.getConnection();
			 sqlQuery = "select * from email_id_info where person_id=?";
			 
		      stmt = conn.prepareStatement(sqlQuery);
		      stmt.setInt(1, personId);

		      rs = stmt.executeQuery();
		      
		      while (rs.next()) {
		    	  addressObj = new EmailId(rs.getString(1),rs.getString(3));
		    	  addressList.add(addressObj);
		    	  
		      }
		      try
		      {
		    	  for (int i = 0; i < forEmailDetails.size(); i++) {
			    	  forEmailDetails.get(i).setEmailAddressId(addressList.get(i).getEmailIdKey());
			    	  forEmailDetails.get(i).setEmailAddress(addressList.get(i).getEmailId());
				} 
		      }
		      catch(Exception e)
		      {
		    	  System.out.println("Size Miss match in Table Email Address");
		      }
		      
		    
		}
		catch(Exception e )
		{
			System.out.println("Problem in Searching Person Data ");
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	/**
	 * This method will Delete Address Info from given ID Parameter from screen
	 * 
	 * @param person
	 * @return void
	 */
	public void deleteAddress(Address address)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		      
		try
		{
			 conn = ConnectionObj.getConnection();
		      stmt = conn.prepareStatement("DELETE FROM contact_info "
		      		+ " WHERE person_id=?");
		      
		      stmt.setInt(1, Integer.parseInt(address.getPersonId()));
		      
		      stmt.executeUpdate();
		      deletePhoneDetails(address);
		      deleteEmailInfo(address);
		}
		catch(Exception e )
		{
			System.out.println("Problem in Updating Address Data ");
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	/**
	 * This method will Delete Phone Info from given ID Parameter from screen
	 * 
	 * @param person
	 * @return void
	 */
	public void deletePhoneDetails(Address address)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		      
		try
		{
			 conn = ConnectionObj.getConnection();
		      stmt = conn.prepareStatement("DELETE FROM phone_number_info "
		      		+ " WHERE person_id=?");
		      
		      stmt.setInt(1, Integer.parseInt(address.getPersonId()));
		      
		      stmt.executeUpdate();
			
		}
		catch(Exception e )
		{
			System.out.println("Problem in Updating Phone Data ");
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	/**
	 * This method will Delete Address Info from given ID Parameter from screen
	 * 
	 * @param person
	 * @return void
	 */
	public void deleteEmailInfo(Address address)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		      
		try
		{
			 conn = ConnectionObj.getConnection();
		      stmt = conn.prepareStatement("DELETE FROM email_id_info "
		      		+ " WHERE person_id=?");
		      
		      stmt.setInt(1, Integer.parseInt(address.getPersonId()));
		      
		      stmt.executeUpdate();
			
		}
		catch(Exception e )
		{
			System.out.println("Problem in Updating Email Data ");
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
}
