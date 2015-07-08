package iCal;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {
	private String path;
	private boolean append = false; // if file exists, true = append; false = overwrite
	private Event currentEvent = new Event();
	private boolean header = false; //used to determine if header needs to be printed
	
	// CONSTRUCTORS 
	public WriteFile(Event object, String filePath){
		path = filePath;
		currentEvent = object;
		
	}
	
	public WriteFile(String event, String filePath, boolean appendValue){
		path = filePath;
		append = appendValue;
	}
	
	//WRITE TO FILE
	public void writeToFile(String textLine) throws IOException {
		//print header only before the first event
		if (!header){
			writeHeader();
			header = true;
		}
		
		//creates writing capabilities
		FileWriter write = new FileWriter(path, true); //don't overwrite header with event = true
		PrintWriter printLine = new PrintWriter(write);
		
		printLine.println("SUMMARY:" + currentEvent.getTitle());
		
		printLine.close();
	}
	
	public void writeHeader() throws IOException{
		FileWriter write2 = new FileWriter(path, append);
		PrintWriter printLine2 = new PrintWriter(write2);
		
		printLine2.println("Header is working");
		
		printLine2.close();
	}
}
