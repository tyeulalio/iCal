package iCal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
		
		
		int colonIndex = currentLine.indexOf(':'); //find colon index
		String propertyData = currentLine.substring(colonIndex+1);
		String propertyName = currentLine.substring(0, colonIndex);
		
		//keep deciphering event until the end of even is reached
		switch (propertyName){
		case "SUMMARY":
			newEvent.setTitle(propertyData);
			break;
		case "DESCRIPTION":
			newEvent.setDescription(propertyData);
			break;
		case "DTSTART":
			// DTSTART looks like this: 20150807T130000
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
			// need to be able to save this in the event class as variable
			break;
		case "UID":
			// save this somewhere? Need to print onto new .ics file?
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
