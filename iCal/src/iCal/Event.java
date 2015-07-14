package iCal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.util.Comparator;

public class Event implements Comparator<Event>{
	public String title; //SUMMARY
	public String description;
	//static public String comment; // COMMENT
	private static String dateCreated; // CREATED
	private String dateLastModified; // LAST-MODIFIED
	public String dateStart; // DTSTART
	public String dateEnd; // DTEND
	public String timeStart; // DTSTART
	public String timeEnd; // DTEND
	
	private String uuid;
	private String classType;
	private String location;
	private float latitude = 0, longitude = 0;
	public String comment;
	

  //////////////////////////////////////////////////////////////
	   /// ** constructors ** 
	   //////////////////////////////////////////////////////////////
	   //////////////////////////////////////////////////////////////
	public Event() {
		title = "";
		description = "";
		comment = "";
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
		//comment = commentx;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmsszz");
	    return ( sdf.format(cal.getTime()) );
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
	
	//public String getComment() {
	//	return comment;
	//}
	
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
	
  public String getClassType() {
    return classType;  
  }
  
  public String getLocation() {
    return location;
  }
  
  public String getDateStart() {
  	return dateStart;
  }
  
  public String getDateEnd() {
  	return dateEnd;
  }
  
  public Float getLatitude(){
  	return latitude;
  }
  
  public Float getLongitude(){
  	return longitude;
  }
  
  public String getUUID(){
  	return uuid;
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
	
	//public void setComment(String x) {
	//	comment = x;
	//}

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
	
	public void setClassType(String x) {
	  classType = x;
	}

  public void setLocation(String x) {
    location = x;
  }
  
  public void setLongitude(Float x) {
    longitude = x;
  }
  
  public void setLatitude(Float x) {
    latitude = x;
  }
  
   public void setUUID(String x) {
    uuid = x;
  }
  
  @Override
  public String toString() {
    return "Title: " + title + " Date: " + dateStart + " Time: " + timeStart;
  }

  /**
   * compares the date and start time of two events
   * @param Event e1
   * @param Event e2
   * @return int 0 if equal, negative if e1 < e2, positive if e1 > e2
   */
  public int compare(Event e1, Event e2) {
    String str1 = e1.getDateStart() + e1.getTimeStart();
    String str2 = e2.getDateStart() + e2.getTimeStart();
    return str1.compareToIgnoreCase(str2);
  }

}
