package iCal;

import java.util.Scanner;
import java.util.UUID;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class Main {

  public static void main(String[] args) throws IOException {
    writeIcs(gatherData());
  }


  // This method gathers data from the User
  public static Event gatherData() throws IOException {
    Scanner scan = new Scanner(System.in);
    Event event1 = new Event();

    // Event's title
    System.out.print("Enter the event's title: ");
    event1.setTitle(scan.nextLine());

    // Event's description
    System.out.print("Enter the event's description: ");
    event1.setDescription(scan.nextLine());

    // Event's start date
    System.out.print("Enter the event's start date in the format YYYYMMDD: ");
    event1.setDateStart(checkValidDate());

    // Event's end date
    System.out.print("Enter the event's end date in the format YYYYMMDD: ");
    event1.setDateEnd(checkValidDate());

    // Event's start time
    System.out.print("Enter event's start time in 24 hr format (e.g. 4 pm = 1600): ");
    event1.setTimeStart(checkValidTime()); // calls method below to validate time entered is in correct format

    // Event's end time
    System.out.print("Enter event's ending time in 24 hr format (e.g. 4 pm = 1600): ");
    event1.setTimeEnd(checkValidTime()); // calls method below to validate time entered is in correct format

    // Date modified
    event1.setDateModified(event1.getDateTime());

    // UUID
    // Randomly generates number here, followed by @Himalian.com
    UUID idOne = UUID.randomUUID();
    event1.setUUID(idOne.toString());
    
    // Location
    System.out.print("Enter event's location: ");
    event1.setLocation(scan.nextLine());

    // GEO
    System.out.print("Would you like to enter a latitude and longitude? Enter Y/N. ");
    String geoAnswer = scan.nextLine();
        boolean proceed = false;
    while (!proceed) {
      if (geoAnswer.charAt(0) == 'Y' || geoAnswer.charAt(0) == 'y') {
        System.out.println("Enter coordinates in decimal degrees.");
        System.out.print("Enter latitude (0-90): ");
        event1.setLatitude(scan.nextFloat()); // truncate to 6 decimal places on output
        System.out.print("Enter longitute (0-180): ");
        event1.setLongitude(scan.nextFloat()); // truncate to 6 decimal places on output
        proceed = true;
      }
      else if (geoAnswer.charAt(0) == 'N' || geoAnswer.charAt(0) == 'n') {
        proceed = true;
      }
      else {
        System.out.print("Invalid entry. Please enter Y for yes, or N for no. ");
        geoAnswer = scan.nextLine();
      }
    }
    // Lat must range from 0-90
    // Lon must range from 0-180

    // CLASS
    String[] classes = { "PUBLIC", "PRIVATE", "CONFIDENTIAL", "iana-token", "x-name" };
    System.out.println("Classify this event by entering a number from the list below:");
    int classInput = -1;
    while (classInput < 0 || classInput > 4) {
      for (int i = 0; i < classes.length; i++) {
        System.out.println(i + " = " + classes[i]);
      }
      classInput = scan.nextInt();
      if (classInput < 0 || classInput > 4)
        System.out.println("Invalid input. Enter a number from the following list:");
    }
    String classification = classes[classInput];
    event1.setClassType(classification);
    
    // The Data gathered so far...
    System.out.println("\nTESTING BEGINS...!");
    System.out.println("SUMMARY:" + event1.getTitle());
    System.out.println("DESCRIPTION:" + event1.getDescription());
    System.out.println("CREATED:" + event1.getDateCreated());
    System.out.println("Last Modified:" + event1.getDateModified());
    System.out.println("DTSTART:" + event1.getTimeStart());
    System.out.println("DTEND:" + event1.getTimeEnd());
    System.out.printf("GEO:%.6f; %.6f\n", event1.getLatitude(), event1.getLongitude());
    System.out.print("CLASS:" + classification + "\n");
    System.out.println("UID:" + event1.getUUID() + "\n");

    return event1;
  }

  // This method has the user to input the time 
  // and verifies it is in the correct format
  public static String checkValidTime() {
    Scanner scan = new Scanner(System.in);
    String tempTime;
    do {
      tempTime = scan.nextLine();
      if (!(isValidTime(tempTime))) {
        System.out.println("You entered an incorrect time. ");
        System.out.print("Please enter the time in 24 hr format (e.g. 4 pm = 1600): ");
      }
    }
    while (!(isValidTime(tempTime)));
    return tempTime;
  }

  // This method supports the previous time method by parsing its format
  public static boolean isValidTime(String inTime) {
    SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
    timeFormat.setLenient(false);
    try {
      timeFormat.parse(inTime.trim());
    }
    catch (ParseException pe) {
      return false;
    }
    return true;
  }

  // This method has the user to input the date 
  // and verifies it is in the correct format
  public static String checkValidDate() {
    Scanner scan = new Scanner(System.in);
    String tempDate;
    do {
      tempDate = scan.nextLine();
      if (!(isValidDate(tempDate))) {
        System.out.println("You entered an incorrect date. ");
        System.out.print("Enter the event's date in the format YYYYMMDD: ");
      }
    }
    while (!(isValidDate(tempDate)));
    return tempDate;
  }

  // This method supports the previous time method by parsing its format
  public static boolean isValidDate(String inDate) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    dateFormat.setLenient(false);
    try {
      dateFormat.parse(inDate.trim());
    }
    catch (ParseException pe) {
      return false;
    }
    return true;
  }
  
  
  
  /**
   * Description: Creates an ics file using the data from an Event Object
   * @author Scott Leung
   * @param Event e
   */
  private static void writeIcs(Event event1) throws IOException
  {
    //Pick a filename
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter a name for your .ics file(.ics will be appended): ");
    String filename = scan.nextLine();
    PrintWriter pw = new PrintWriter(new FileWriter((filename +".ics"), false));
 		
    //Header info
    pw.printf("%s%n", "BEGIN:VCALENDAR");
    pw.printf("%s%n", "PRODID:-//Team Himalia//iCalendar Assignment//EN");
    pw.printf("%s%n", "VERSION:2.0");
    pw.printf("%s%n", "CALSCALE:GREGORIAN");
    pw.printf("%s%n", "METHOD:PUBLISH");
    pw.printf("%s%n", "BEGIN:VTIMEZONE");
    pw.printf("%s%n", "TZID:Pacific/Honolulu");
    pw.printf("%s%n", "BEGIN:STANDARD");
    pw.printf("%s%n", "DTSTART:19700101T000000");
    pw.printf("%s%n", "TZOFFSETFROM:-1000");
    pw.printf("%s%n", "TZOFFSETTO:-1000");
    pw.printf("%s%n", "TZNAME:HST");
    pw.printf("%s%n", "END:STANDARD");
    pw.printf("%s%n", "END:VTIMEZONE");
    
    //Event info begin
    pw.printf("%s%n", "BEGIN:VEVENT");
    pw.printf("%s%s%s%s%s%n", "DTSTART:", event1.getDateStart(), "T", event1.getTimeStart(), "00");//start time
    pw.printf("%s%s%s%s%s%n", "DTEND:", event1.getDateEnd(), "T", event1.getTimeEnd(), "00");//end time
    pw.printf("%s%s%n", "DTSTAMP:", event1.getDateCreated());//time stamp
    pw.printf("%s%n", "UID:" + event1.getUUID() + "@Himalia.com");//need unique id
    pw.printf("%s%s%n", "CLASS:", event1.getClassType());//classification 
    pw.printf("%s%n", "CREATED:" + event1.getDateCreated());//time created stamp
  
    pw.printf("%s%s%n", "DESCRIPTION:", event1.getDescription());//description
    pw.printf("%s%s%n", "LAST-MODIFIED:", event1.getDateModified());//time modified stamp
    pw.printf("%s%s%n", "LOCATION:", event1.getLocation());//location variable
    pw.printf("%s%.6f;%.6f%n", "GEO:", event1.getLatitude(), event1.getLongitude());//need this for assignment requirement 
    //pw.printf("%s%n", "SEQUENCE:0");
    //pw.printf("%s%n", "STATUS:CONFIRMED");
    pw.printf("%s%s%n", "SUMMARY:", event1.getTitle());//title
    //pw.printf("%s%n", "TRANSP:OPAQUE");
    pw.printf("%s%n", "END:VEVENT");
    pw.printf("%s%n", "END:VCALENDAR");
    System.out.println("Finished saving to file!");
    pw.close();
  }

}
