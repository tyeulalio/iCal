package iCal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class Event {
	String title;
	String description;
	int dateCreated; // CREATED
	int dateLastModified; // LAST-MODIFIED
	int timeStart; // DTSTART
	int timeEnd; // DTEND
	String uid;
	String classType;
	int location;
	
	// This method takes the current date and time and returns it
	public static String getDateTime()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmsszz");
	    return ( sdf.format(cal.getTime()) );
	}
	
}
