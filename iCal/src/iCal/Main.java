package iCal;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		gatherData();	
		
	}
	
	public static void gatherData(){
		Scanner scan= new Scanner(System.in);
		Event event1 = new Event();
		
		//event's title
		System.out.print("Enter the event's title: ");
		event1.setTitle(scan.nextLine());
		
		//event's description
		System.out.print("Enter the event's description: ");
		event1.setDescription(scan.nextLine());
				
		//event's start time
		System.out.println("Enter event's start time in 24 hr format (e.g. 4 pm = 1600): ");
		//event1.timeStart = ();
				
		//created date
				
		//date modified
				
		
		//Used to test code
		System.out.println("Event is: " + event1.getTitle());
		System.out.println("Event description is: " + event1.getDescription());
		System.out.println("Date created is: " + event1.getDateCreated());

	}

}
