package iCal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.util.Comparator;

public class Event implements Comparator<Event>{
	private String title; //SUMMARY
	private String description;
	private static String comment; // COMMENT
	private static String dateCreated; // CREATED
	private String dtstamp; // LAST-MODIFIED
	private String dateStart; // DTSTART
	private String dateEnd; // DTEND
	private String timeStart; // DTSTART
	private String timeEnd; // DTEND
	private String uuid;
	private String classType;
	private String location;
	private float latitude, longitude;
	private String tzid;
	private String fileName;
	

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
	
	public Event(String titlex, String descriptx, 
					String dateStartx, String dateEndx, 
					String timeStartx, String timeEndx, 
					float longx, float latx, String string) {
		title = titlex;
		description = descriptx;
		dateStart = dateStartx;
		dateEnd = dateEndx;
		timeStart = timeStartx;
		timeEnd = timeEndx;
		comment = "";
		String classType = "Public";
		longitude = longx;
		latitude = latx;
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
	
	public String getDtstamp() {
		return dtstamp;
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
  
  public String getTzid(){
  	return tzid;
  }
  
  public String getFileName(){
	  return fileName;
  }
  

	////////////////////////////////////////////////////////////////
	// * mutator methods *
	
	// This method sets the current date and time to be the date created 
	public static void setDateCreated(String s) 
	{
		if (s != null){
			dateCreated = s;
		}
		else {
			dateCreated = getDateTime();
		}
	}
	
	public void setTitle(String x) {
		title = x;
	}

	public void setDescription(String x) {
		description = x;
	}
	
	public void setComment(String x) {
		comment = x;
	}

	public void setDtstamp(String x) {
		dtstamp = x;
	}
	
	public void setDateStart(String x) {
		dateStart = x + "00";
	}
	
	public void setDateEnd(String x) {
		dateEnd = x + "00";
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
  
  public void setTzid(String x){
	tzid = x;
  }
  
  public void setFileName(String x){
	  fileName = x;
  }
  
  @Override
  public String toString() {
    return "Title:\t" + title + " \t\tDate:\t" + dateStart + " \tTime:\t" + timeStart + "\n\t\t\tComment: " + comment;
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
