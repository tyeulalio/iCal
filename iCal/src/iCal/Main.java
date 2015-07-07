package iCal;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		
		//creates an event
		Event event1 = new Event();
		
		//event's title
		System.out.print("Enter the event's title: ");
		event1.title =  scan.nextLine();
		
		//event's description
		System.out.print("Enter the event's description: ");
		event1.description = scan.nextLine();
		
		//created date
		
		
		//Used to test code
		System.out.println("Event is: " + event1.title);
		System.out.println("Event description is: " + event1.description);
		System.out.println("Date created is: " + event1.dateCreated);
		
		

	}

}
