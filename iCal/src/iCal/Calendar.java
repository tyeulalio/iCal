package iCal;

public class Calendar {
	private static String name = "VCALENDAR";
	private static String proId = "-//Team Himalia//iCalendar Assignment//EN";
	private static String version = "2.0";
	private static String calScale = "GREGORIAN";
	private static String method = "PUBLISH";
	
	//Accessor methods
	public static String getName(){
		return name;
	}
	
	public static String getProId(){
		return proId;
	}
	
	public static String getVersion(){
		return version;
	}
	
	public static String getCalScale(){
		return calScale;
	}
	
	public static String getMethod(){
		return method;
	}
}
