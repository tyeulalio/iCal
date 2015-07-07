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
	int dateStart; // DTSTART
	int dateEnd; // DTEND
	int timeStart; // DTSTART
	int timeEnd; // DTEND
	
	String uid;
	String classType;
	int location;
	
	   //////////////////////////////////////////////////////////////
	   /// ** constructors ** 
	   //////////////////////////////////////////////////////////////
	   //////////////////////////////////////////////////////////////
	public Event(String titlex, String descriptx, int dateStartx, int dateEndx, int timeStartx, int timeEndx) {
		title = titlex;
		description = descriptx;
		dateStart = dateStartx;
		dateEnd = dateEndx;
		timeStart = timeStartx;
		timeEnd = timeEndx;
		String classType = "Public";
	}
	
	// This method takes the current date and time and returns it
	public static String getDateTime()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmsszz");
	    return ( sdf.format(cal.getTime()) );
	}
	
	// This method sets the current date and time to be the date created 
	private static void setDateCreated(String s) 
	{
		dateCreated = getDateTime();
	}
}
