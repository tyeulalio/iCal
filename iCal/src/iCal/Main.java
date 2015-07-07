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
		
		//event's 
		
		System.out.println("Event is: " + event1.title);
		

	}

}
