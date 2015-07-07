package iCal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class Event {
	String title;
	String description;
	static String dateCreated; // CREATED
	String dateLastModified; // LAST-MODIFIED
	String dateStart; // DTSTART
	String dateEnd; // DTEND
	String timeStart; // DTSTART
	String timeEnd; // DTEND
	
	String uid;
	String classType;
	String location;
	
	   //////////////////////////////////////////////////////////////
	   /// ** constructors ** 
	   //////////////////////////////////////////////////////////////
	   //////////////////////////////////////////////////////////////
	public Event() {
		title = "";
		description = "";
		String classType = "Public";
		dateCreated = getDateTime();
	}
	
	public Event(String titlex, String descriptx, String dateStartx, String dateEndx, String timeStartx, String timeEndx) {
		title = titlex;
		description = descriptx;
		dateStart = dateStartx;
		dateEnd = dateEndx;
		timeStart = timeStartx;
		timeEnd = timeEndx;
		String classType = "Public";
	}
	
   //////////////////////////////////////////////////////////////
   /// ** methods ** 
   //////////////////////////////////////////////////////////////

	// * accessor methods *
	
	// This method takes the current date and time and returns it
	public static String getDateTime()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmsszz");
	    return ( sdf.format(cal.getTime()) );
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
	
	public String getDateCreated() {
		return dateCreated;
	}
	
	public String getDateModified() {
		return dateLastModified;
	}
	
	public String getTimeStart() {
		return timeStart;
	}
	
	public String getTimeEnd() {
		return timeEnd;
	}
	////////////////////////////////////////////////////////////////
	// * mutator methods *
	
	// This method sets the current date and time to be the date created 
	private static void setDateCreated(String s) 
	{
		dateCreated = getDateTime();
	}
	
	public void setTitle(String x) {
		title = x;
	}

	public void setDescription(String x) {
		description = x;
	}

	
	public void setDateModified(String x) {
		dateLastModified = x;
	}
	
	public void setDateStart(String x) {
		dateStart = x;
	}
	
	public void setDateEnd(String x) {
		dateEnd = x;
	}
	
	public void setTimeStart(String x) {
		timeStart = x;
	}
	
	public void setTimeEnd(String x) {
		timeEnd = x;
	}
}
