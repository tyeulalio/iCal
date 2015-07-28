package iCal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Comparator;
import java.util.TimeZone;
import java.util.UUID;

public class Event implements Comparator<Event>{
	private String title; //SUMMARY
	private String description;
	private String comment; // COMMENT
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
	private String dtStart;
	private String dtEnd;
	

//////////////////////////////////////////////////////////////
/// ** constructors ** 
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
	public Event() {
		title = "";
		description = "";
		comment = "";
		setClassType("Public");
		dateCreated = getDateTime();
		fileName = null;
		UUID idOne = UUID.randomUUID();
		setUUID(idOne.toString() + "@Himalia.com");
		location = "";
	}
	
	public Event(String titlex, String descriptx, 
					String dateStartx, String dateEndx, 
					String timeStartx, String timeEndx, 
					float longx, float latx, String filex) {
		title = titlex;
		description = descriptx;
		dateStart = dateStartx;
		dateEnd = dateEndx;
		timeStart = timeStartx;
		timeEnd = timeEndx;
		comment = "";
		setClassType("Public");
		longitude = longx;
		latitude = latx;
		fileName = filex;
		UUID idOne = UUID.randomUUID();
		setUUID(idOne.toString() + "@Himalia.com");
		location = "";
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
	      
		// Formats time as UTC -- will make working with timezones easier
  	  	//System.out.println("current: "+ sdf.format(cal.getTime())); // test print current time
  	  	TimeZone z = cal.getTimeZone(); // retrieve current timezone of user 
  	  	int offset = z.getRawOffset(); // checks offset from UTC
  	  	if(z.inDaylightTime(new Date())){ //checks for daylightsavings (DST) time
  	  		offset = offset + z.getDSTSavings();
  	  	}
  	  	int offsetHrs = offset / 1000 / 60 / 60;
  	  	int offsetMins = offset / 1000 / 60 % 60;
  	  	//System.out.println("offset: " + offsetHrs); // TESTING
  	  	//System.out.println("offset: " + offsetMins); // TESTING

  	  	// Adjusts for offset from UTC, including DST
  	  	cal.add(Calendar.HOUR_OF_DAY, (-offsetHrs));
  	  	cal.add(Calendar.MINUTE, (-offsetMins));

  	  	// Formats UTC time in proper form
  	  	String utcTime = sdf.format(cal.getTime());
  	  	utcTime = utcTime.substring(0, utcTime.length()-3);
  	  	utcTime += "Z";
  	  	//System.out.println("GMT Time: " + utcTime);
  	  		
  	  	return (utcTime);
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
	
	public String getComment() {
		return comment;
	}
	
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
  
  public String getDtstart(){
	  if (dtStart == null){
		  return dateStart + "T" + timeStart;
	  }
	  
	  else {
		  return dtStart;
	  }
  }
  
  public String getDtend(){
	  if (dtEnd == null){
		  return dateEnd + "T" + timeEnd;
	  }
	  
	  else {
		  return dtEnd;
	  }
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
		dateStart = x; //+ "00";
	}
	
	public void setDateEnd(String x) {
		dateEnd = x; //+ "00";
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
  
  public void setDtstart(){  
	  dtStart = convertToUTC(dateStart, timeStart, tzid);
  }
  
  public void setDtend(){
	  dtEnd = convertToUTC(dateEnd, timeEnd, tzid);
  }
  
  public static String convertToUTC(String dateStartEnd, String timeStartEnd, String timezone){
	  // String dateStartEnd should be set to either dateStart or dateEnd
	  // String timeStartEnd should be set to either timeStart or timeEnd
	  
	  // If there is not tzid info, then UTC cannot be used
	  if (timezone == null){
		  return null;
	  }
	  
	  //System.out.println("end: " + dateEnd + timeEnd);
	  
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
	  int year, month, day, hrs, min, sec;
	  //System.out.println(dateStart); //output looks like: 2015121200
	  String date = dateStartEnd;
	  // Separate year, month and date from dateStart or dateEnd
	  year = Integer.parseInt(date.substring(0, 4));
	  month = Integer.parseInt(date.substring(4, 6));
	  day = Integer.parseInt(date.substring(6,8));
	  //TESTING
	  //System.out.println("year is: " + year); 
	  //System.out.println("month is: " + month);
	  //System.out.println("date is: "+ date);
	  
	  String time = timeStartEnd;
	  //System.out.println("timestart is: " + time);
	  // Separate hrs and min from timeStart and timeEnd
	  hrs = Integer.parseInt(time.substring(0, 2));
	  min = Integer.parseInt(time.substring(2, 4));
	  sec = 0;
	  //System.out.println("time is: " + hrs + " " + min + " " + sec);
	  
	  Calendar utcCal = Calendar.getInstance();
	  utcCal.set(year, month, day, hrs, min, sec);
	  //String tempDtstart = sdf.format(utcCal.getTime());
	  //System.out.println("startCal is:" + tempDtstart);
	  
	  //System.out.println("utcCal's month is: " + utcCal.get(Calendar.MONTH)); //this test works
	  
	  // Formats time as UTC -- will make working with timezones easier
	  //System.out.println("current: "+ sdf.format(cal.getTime())); // test print current time
	  TimeZone z = utcCal.getTimeZone(); // retrieve current timezone of user 
	  int offset = z.getRawOffset(); // checks offset from UTC
	  if(z.inDaylightTime(new Date())){ //checks for daylightsavings (DST) time
	  	offset = offset + z.getDSTSavings();
	  }
	  int offsetHrs = offset / 1000 / 60 / 60;
	  int offsetMins = offset / 1000 / 60 % 60;
	  //System.out.println("offset: " + offsetHrs); // TESTING
	  //System.out.println("offset: " + offsetMins); // TESTING
	  //System.out.println("offset total: " + offset); //TESTING
	  
	  // Adjusts for offset from UTC, including DST
	  utcCal.add(Calendar.HOUR_OF_DAY, (-offsetHrs));
	  utcCal.add(Calendar.MINUTE, (-offsetMins));
	  
	  //System.out.println("utc 2nd month check: " + utcCal.get(Calendar.MONTH)); //works
	  //System.out.println("utc getTime format check: " + utcCal.getTime());

	  //System.out.println("tzid is: " + timezone); //TESTING
	  // Adjust for event in different timezone
	  int tzidInt = Integer.parseInt(timezone);
	  tzidInt /= 100;
	  //System.out.println("tzidInt is: " + tzidInt); //TESTING
	  int tzidDiff = offsetHrs - tzidInt;
	  //System.out.println("tzidDiff = " + tzidDiff); //TESTING
	  utcCal.add(Calendar.HOUR_OF_DAY,  (tzidDiff));
	  
	  utcCal.add(Calendar.MONTH, (-1));
	  //System.out.println("utc getTime is: " + utcCal.getTime()); //doesn't work
	  //System.out.println("utc sfd format is: " + sdf.format(utcCal.getTime()));
	  
	  // Formats UTC time in proper form
	  String utc = sdf.format(utcCal.getTime());
	  utc = utc.substring(0, utc.length());
	  //System.out.println("UTC Time: " + utc);
	  
	  return utc;
  }
  
  @Override
  public String toString() {
    return "Title:\t" + title + " \t\tDate:\t" + dateStart + " \tTime:\t" + timeStart.substring(0, 4) + "\n\t\t\tComment: " + comment;
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
