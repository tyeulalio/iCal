package iCal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
	private String fileName;
	private String currentLine = ""; //used to read file line by line'
	private EventLinkedList<Event> calendar;
	
	// CONSTRUCTORS 
		public ReadFile(String file, EventLinkedList<Event> cal){
			fileName = file;	
			calendar = cal;
		}
	
	// This method will actually read the input of the file	
	public void ReadFile(){
		Scanner keyboard = new Scanner(System.in);
		// Receives input from user about the file to be read
		try {
			// FileReader will read the text file
			FileReader fileReader = new FileReader(fileName);
		
			// Wrap FileReader in BufferedReader
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			// Read file line by line until the end
			while((currentLine = bufferedReader.readLine()) != null){ //might change null to END:VCALENDAR
				//System.out.println(currentLine); // Used for testing, just prints each line to screen
				
				// Need to decipher what the line means
				// Find events in the file
				if (currentLine.equals("BEGIN:VEVENT")){
					// create new event
					Event newEvent = new Event();
					
					// Read first line after VEVENT starts
					currentLine = bufferedReader.readLine();
					// Keep deciphering file lines until reaching the end of event
					while (!currentLine.equals("END:VEVENT")){
						decipher(currentLine, newEvent); // Deciphers information in line
						currentLine = bufferedReader.readLine(); // Reads next line of file
					}
					
					
					// If .ics file contained tzid in dtstart/dtend, must convert to offset time
					if (newEvent.getTzid() != null){
						//System.out.println("tzid is: " + newEvent.getTzid()); //TEST
						Timezone timezone = new Timezone(newEvent.getTzid());
						timezone.createArray();
						//System.out.println("tzOffSet is: " + timezone.searchArray());	//TEST
						newEvent.setTzid(Timezone.searchArray());
					}
					
				    // Set Dtstart and Dtend only after timezone entry is known
				    newEvent.setDtstart();
				    newEvent.setDtend();
				    
				    System.out.println("Would you like to overwrite the original file on export? Enter Y/N.");
				    // Use this to rewrite onto import file
				    String overwrite = keyboard.nextLine();
				    if (overwrite.equalsIgnoreCase("Y") || overwrite.equalsIgnoreCase("Y")) {
				    		newEvent.setFileName(fileName.substring(0, fileName.length()-4));
	                    }
	                else if (overwrite.equalsIgnoreCase("No") || overwrite.equalsIgnoreCase("N")) {
	                    	// Need a filename for event to be output properly
	    					// Get first word of summary
	    					String title = newEvent.getTitle();
	    					int titleSpace = title.indexOf(' ');
	    					String titleFirstWord, locationFirstWord;
	    					if (titleSpace == -1)
	    						titleFirstWord = title;
	    					else 
	    						titleFirstWord = title.substring(0, titleSpace);
	    					// Get first word of location
	    					String location = newEvent.getLocation();
	    					int locationSpace = location.indexOf(' ');
	    					if (locationSpace == -1)
	    						locationFirstWord = location;
	    					else 
	    						locationFirstWord = location.substring(0, locationSpace);
	    					if (locationFirstWord.length() > 2)
	    						locationFirstWord = "_at_" + locationFirstWord;
	    					newEvent.setFileName(titleFirstWord + locationFirstWord);
	                    }
	                else {
	                  	  System.out.print("Type \'Y\' or \'N\' to whether you want to Test Mode:");
	                  	  overwrite = keyboard.nextLine();
	                    }
					
					//tests list
					calendar.add(newEvent);					
				}				
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
	} //end ReadFile method
	
	// This method is used to decipher what information is contained in each line of the file
	public void decipher(String currentLine, Event newEvent){
		String propertyData; 
		String propertyName;
		
		int colonIndex = currentLine.indexOf(':'); //find colon index
		// need to check for semicolon for dtstart and dtend
		int semicolonIndex = currentLine.indexOf(';');
		
		// this is used to catch the different formats that dtstart and dtend come in
		// See this page for format examples:
		// http://www.kanzaki.com/docs/ical/dateTime.html
		if (semicolonIndex != -1 && semicolonIndex < colonIndex){
			propertyData = currentLine.substring(semicolonIndex+1);
			propertyName = currentLine.substring(0,semicolonIndex);
		}
		else {
			propertyData = currentLine.substring(colonIndex+1);
			propertyName = currentLine.substring(0, colonIndex);
		}
		//keep deciphering event until the end of even is reached
		switch (propertyName){
		case "SUMMARY":
			newEvent.setTitle(propertyData);
			break;
		case "DESCRIPTION":
			newEvent.setDescription(propertyData);
			break;
		case "DTSTART":
			int indexOfColon = propertyData.indexOf(':');
			// checking format of dtstart
			// There are 3 formats that dtstart might be in
			// dtStart might look like this: DTSTART;TZID=US-Eastern:19980119T020000
			if ((propertyData.substring(0,4)).equals("TZID")){
				int indexOfEqual = propertyData.indexOf('=');
				String tzid = propertyData.substring(indexOfEqual+1, indexOfColon);
				newEvent.setTzid(tzid);
				//System.out.print("Event tzid is: " + newEvent.getTzid());
				propertyData = propertyData.substring(indexOfColon+1);
			}
			
			// DTSTART might look like: DTSTART:19980119T070000Z
			
			// DTSTART might look like this: DTSTART:20150807T130000
			// Numbers before 'T' is dateStart
			// Numbers after 'T' is timeStart
			int indexOfT = propertyData.indexOf('T');
			String dateStart = propertyData.substring(0,indexOfT);
			String timeStart = propertyData.substring(indexOfT+1);
			
			newEvent.setDateStart(dateStart);
			newEvent.setTimeStart(timeStart);
					
			// The next 2 lines are used to test whether dateStart and timeStart are correct
			//System.out.println("dateStart is: " + dateStart);
			//System.out.println("timeStart is: " + timeStart);	 
			break;
		case "DTEND":
			indexOfColon = propertyData.indexOf(':');
			// checking format of dtstart
			if ((propertyData.substring(0,4)).equals("TZID")){
				int indexOfEqual = propertyData.indexOf('=');
				String tzid = propertyData.substring(indexOfEqual+1, indexOfColon);
				newEvent.setTzid(tzid);
				// System.out.print("Event tzid is: " + newEvent.getTzid());
				propertyData = propertyData.substring(indexOfColon+1);
			}
			
			// DTEND looks like this: 20150807T130000
			// Numbers before 'T' is dateStart
			// Numbers after 'T' is timeStart
			indexOfT = propertyData.indexOf('T');
			String dateEnd = propertyData.substring(0,indexOfT);
			String timeEnd = propertyData.substring(indexOfT+1);
					
			newEvent.setDateEnd(dateEnd);
			newEvent.setTimeEnd(timeEnd);
							
			// The next 2 lines are used to test whether dateStart and timeStart are correct
			//System.out.println("dateEnd is: " + dateEnd);
			//System.out.println("timeEnd is: " + timeEnd);	 			
			break;
		case "DTSTAMP":
			newEvent.setDtstamp(propertyData);
			break;
		case "UID":
			newEvent.setUUID(propertyData);
			break;
		case "CLASS":
			newEvent.setClassType(propertyData);
			break;
		case "CREATED":
			newEvent.setDateCreated(propertyData);		
			// Testing
			//System.out.println("Date created is: " + propertyData);
			//System.out.println("Event date created is: " + newEvent.getDateCreated());
			break;
		case "LOCATION":
			if (propertyData == null)
				newEvent.setLocation("");
			newEvent.setLocation(propertyData);
			break;
		case "GEO":
			// Geo is formatted as such, GEO:0.000000;0.000000
			// latitude is before ;
			// longitude is after ;
			int indexOfSemiColon = propertyData.indexOf(';');
			String lat = propertyData.substring(0,indexOfSemiColon);
			String lon = propertyData.substring(indexOfSemiColon+1);
			
			float latitude = Float.parseFloat(lat);
			float longitude = Float.parseFloat(lon);
			
			newEvent.setLatitude(latitude);
			newEvent.setLongitude(longitude);
			
			// Next 2 lines text geo
			//System.out.printf("Event's lat is: %.6f%n", newEvent.getLatitude());
			//System.out.printf("Event's lon is: %.6f%n", newEvent.getLongitude());
			break;		
		default: 
			// System.out.println("Non-summary lines found too");
			break;		
		} //ends switch
		
			
	}
	
}// ends ReadFile class
