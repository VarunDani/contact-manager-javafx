package edu.utdallas.db.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.utdallas.db.model.Person;
import edu.utdallas.db.utility.DateUtility;

/**
 * 
 * This Class provides All DB interface method for Person Object
 * This class will get connection from Connection Object and Perform various operation on 
 * Person Table in Database
 * 
 * @version 1.0
 * @since v1.0
 * @author Varun Dani
 *
 */
public class PersonUtility {
	
	
	/**
	 * This method will Insert new Person Entry in table company.person
	 * 
	 * 
	 * @param person
	 * @return void
	 */
	public void addPerson(Person person)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		      
		try
		{
			 conn = ConnectionObj.getConnection();
		      stmt = conn.prepareStatement("INSERT INTO person (`first_name`, `middle_initial`, `last_name`, `birth_date`, `sex`) values (?, ?, ?, ?, ?)");
		      stmt.setString(1, person.getFirstName());
		      stmt.setString(2, person.getMiddleInitial());
		      stmt.setString(3, person.getLastName());
		      stmt.setDate(4, new java.sql.Date(DateUtility.getDateObj(person.getBirthDate()).getTime()));
		      stmt.setString(5, person.getSex());
		      
		      stmt.executeUpdate();
		      
		      ResultSet rs = stmt.getGeneratedKeys();
		      if (rs.next()) {
		        int newId = rs.getInt(1);
		        person.setId(String.valueOf(newId));
		      }
		      
			
		}
		catch(Exception e )
		{
			System.out.println("Problem in Inserting into Person Object ");
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
	 * This method will Update Person Info from given ID Parameter from screen
	 * 
	 * @param person
	 * @return void
	 */
	public void updatePerson(Person person)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		      
		try
		{
			 conn = ConnectionObj.getConnection();
		      stmt = conn.prepareStatement("UPDATE person SET first_name=?,middle_initial=?,last_name=?,birth_date=?,sex=?"
		      		+ " WHERE person_id=?");
		      stmt.setString(1, person.getFirstName());
		      stmt.setString(2, person.getMiddleInitial());
		      stmt.setString(3, person.getLastName());
		      stmt.setDate(4, new java.sql.Date(DateUtility.getDateObj(person.getBirthDate()).getTime()));
		      stmt.setString(5, person.getSex());
		      
		      stmt.setInt(6, Integer.parseInt(person.getId()));
		      
		      stmt.executeUpdate();
			
		}
		catch(Exception e )
		{
			System.out.println("Problem in Updating Person Data ");
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
	 * @param person
	 * @return void
	 */
	public void searchPerson(Person person)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		      
		try
		{
			 conn = ConnectionObj.getConnection();
			 StringBuilder queryBuilder = new StringBuilder("select * from person where ");
			 int counter =1;
			 //TODO : Complete Search Idf Possible 
			 
			/* if(!"".equals(person.getFirstName()))
			 {
				 queryBuilder.append("first_name=?");
				 stmt.setString(counter++, person.getFirstName());
			 }*/
		      stmt = conn.prepareStatement("");
		      stmt.setString(1, person.getFirstName());
		      stmt.setString(2, person.getMiddleInitial());
		      stmt.setString(3, person.getLastName());
		      stmt.setDate(4, new java.sql.Date(DateUtility.getDateObj(person.getBirthDate()).getTime()));
		      stmt.setString(5, person.getSex());
		      
		      stmt.setInt(6, Integer.parseInt(person.getId()));
		      
		      stmt.executeUpdate();
			
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
		}
	}
	
	
	/**
	 * get All Person Object
	 * 
	 * @param personId
	 * @return ArrayList<Person>
	 */
	public ArrayList<Person> getPersons()
	{
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rs = null;
		String sqlQuery="";
		ArrayList<Person> personList = new ArrayList<Person>();
		Person personObj;
		try
		{
			 conn = ConnectionObj.getConnection();
			 sqlQuery = "select person_id,first_name,middle_initial,last_name,birth_date,sex  from company.person";
			 
		      stmt = conn.createStatement();

		      rs = stmt.executeQuery(sqlQuery);
		      
		      while (rs.next()) {
		    	  personObj = new Person(String.valueOf(rs.getInt("person_id")),
		    			  rs.getString("FIRST_NAME"),rs.getString("MIDDLE_INITIAL"),
		    			  rs.getString("LAST_NAME"),rs.getString("BIRTH_DATE"),
		    			  rs.getString("SEX"));
		    	  
		    	  
		    	  personList.add(personObj);
		    	  
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
		
		return personList;
	}
	
	/**
	 * This method will Delete Person Info from given ID Parameter from screen
	 * 
	 * @param person
	 * @return void
	 */
	public void deletePerson(Person person)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		      
		try
		{
			 conn = ConnectionObj.getConnection();
		      stmt = conn.prepareStatement("DELETE FROM person "
		      		+ " WHERE person_id=?");
		      
		      stmt.setInt(1, Integer.parseInt(person.getId()));
		      
		      stmt.executeUpdate();
			
		}
		catch(Exception e )
		{
			System.out.println("Problem in Updating Person Data ");
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
