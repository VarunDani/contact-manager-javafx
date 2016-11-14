package edu.utdallas.db.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 
 * This is Utility Class that provide utility method for converting to date and from date 
 * Various methods provide different implementation for Date Functionality
 * 
 * @version 1.0
 * @since v1.0
 * @author Varun Dani
 *
 */
public class DateUtility {

	
	/**
	 * get java date object from string date after parsing format yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateObj(String date)
	{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		Date parsedDate = null;
		try {
			parsedDate = (Date)formatter.parse(date);
			 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}
	
	
	/**
	 * This method will give sql date : Not Using 
	 * 
	 * @param date
	 * @return
	 */
	@Deprecated
	public static java.util.Date getSQLDate(String date)
	{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		java.util.Date parsedDate = null;
		try {
			parsedDate = (Date)formatter.parse(date);
			 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}
	
	/**
	 * get String today date in format yyyy-MM-dd
	 * 
	 * 
	 * @return
	 */
	public static String getTodatDateString()
	{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		String todayDate = null;
		try {
			todayDate = formatter.format(new java.util.Date());
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todayDate;
	}
	
	/**
	 * get java date object from string date after parsing format yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static Date getDateFromEle(String date)
	{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		Date parsedDate = null;
		try {
			parsedDate = (Date)formatter.parse(date);
			 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}
}
