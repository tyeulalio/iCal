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
		
		//Need to write timezone data
		printLine.printf("%s%s%n", "BEGIN:", Timezone.getName());
		printLine.printf("%s%s%n", "TZID:", Timezone.getTzid());
		printLine.printf("%s%n", "BEGIN:STANDARD");
		printLine.printf("%s%s%n", "DTSTART:", Timezone.getDtStart());
		printLine.printf("%s%s%n", "TZOFFSETFROM:", Timezone.getOffSetFrom());
		printLine.printf("%s%s%n", "TZOFFSETTO:", Timezone.getOffSetTo());
		printLine.printf("%s%s%n", "TZNAME:", Timezone.getTzName());
		printLine.printf("%s%n", "END:STANDARD");
		printLine.printf("%s%s%n", "END:", Timezone.getName());
		
		//Start writing event data
		printLine.println("SUMMARY:" + currentEvent.getTitle());
		
		//Need to close the Calendar Object after event data entered
		printLine.printf("%s%s%n", "END:", Calendar.getName());
		
		printLine.close();
	}
	
	public void writeHeader() throws IOException{
		FileWriter write2 = new FileWriter(path, append);
		PrintWriter printLine2 = new PrintWriter(write2);
		
		//begins the calendar object
		printLine2.printf("%s%s%n", "BEGIN:", Calendar.getName());
		printLine2.printf("%s%s%n", "PRODID:", Calendar.getProId());
		printLine2.printf("%s%s%n", "VERSION:", Calendar.getVersion());
		printLine2.printf("%s%s%n", "CALSCALE:", Calendar.getCalScale());
		printLine2.printf("%s%s%n", "METHOD:", Calendar.getMethod());
		
		printLine2.close();
	}
}
