package iCal;

public class Timezone {
	private static String name = "VTIMEZONE";
	private static String tzid = "Pacific/Honolulu";
	private static String dtStart = "19700101T000000";
	private static String offSetFrom = "-1000";
	private static String offSetTo = "-1000";
	private static String tzName = "HST";
	
	//CONSTRUCTOR
	public Timezone(String id){
		tzid = id;
	}
	
	//accessor methods
	public static String getName(){
		return name;
	}
	
	public static String getTzid(){
		return tzid;
	}
	
	public static String getDtStart(){
		return dtStart;
	}
	
	public static String getOffSetFrom(){
		return offSetFrom;
	}
	
	public static String getOffSetTo(){
		return offSetTo;
	}
	
	public static String getTzName(){
		return tzName;
	}
}
