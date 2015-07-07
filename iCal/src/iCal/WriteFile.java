package iCal;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {
	private String path;
	private boolean append = false; // if file exists, true = append; false = overwrite
	private Event currentEvent = new Event();
	
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
		FileWriter write = new FileWriter(path, append);
		PrintWriter printLine = new PrintWriter(write);
		
		printLine.println("SUMMARY:" + currentEvent.getTitle());
		
		printLine.close();
	}
}
