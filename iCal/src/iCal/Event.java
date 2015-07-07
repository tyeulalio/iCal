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
	
	public static void getTime()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    System.out.println( sdf.format(cal.getTime()) );
	}
	
}
