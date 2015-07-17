package iCal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	
	//creates an array with timezones so that information can be used
	//when reading in files
	public static void createArray(){
		String[] timezone = new String[87]; //87 = number of time zones
		String fileName = "timezones.txt"; //file with timezones listed
		String currentLine = "";
		
		// Receives input from user about the file to be read
		try {
			// FileReader will read the text file
			FileReader fileReader = new FileReader(fileName);
		
			// Wrap FileReader in BufferedReader
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			// Read file line by line until the end
			while((currentLine = bufferedReader.readLine()) != null){ //might change null to END:VCALENDAR
				System.out.println(currentLine); // Used for testing, just prints each line to screen
						
			}
		// Close files
		bufferedReader.close();
		}
				
		// Outputs error message is fileName is not found
		catch(FileNotFoundException ex1){
			System.out.println("Unable to open file '" + fileName + "'.");
		}
		// Outputs error message if fileName cannot be read
		catch(IOException ex2){
			System.out.println("Error reading file '" + fileName + "'.");
		}
	}
}

