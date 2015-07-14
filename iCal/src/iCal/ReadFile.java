package iCal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	private String fileName;
	private String currentLine = ""; //used to read file line by line
	
	// CONSTRUCTORS 
		public ReadFile(String file){
			fileName = file;	
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
					// Read first line after VEVENT starts
					currentLine = bufferedReader.readLine();
					// Keep deciphering file lines until reaching the end of event
					while (!currentLine.equals("END:VEVENT")){
						decipher(currentLine); // Deciphers information in line
						currentLine = bufferedReader.readLine(); // Reads next line of file
					}
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
	public void decipher(String currentLine){
		int colonIndex = currentLine.indexOf(':'); //find colon index
		String propertyName = currentLine.substring(0, colonIndex);
		
		//keep deciphering event until the end of even is reached
		switch (propertyName){
		
		}
		
			
	}
	
}// ends ReadFile class
