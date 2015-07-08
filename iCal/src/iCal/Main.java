package iCal;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Main {

  public static void main(String[] args) throws IOException {
    writeIcs(gatherData());

  }


  public static Event gatherData() throws IOException {
    Scanner scan = new Scanner(System.in);
    Event event1 = new Event();

    // event's title
    System.out.print("Enter the event's title: ");
    event1.setTitle(scan.nextLine());

    // event's description
    System.out.print("Enter the event's description: ");
    event1.setDescription(scan.nextLine());

    // event's start date
    System.out.print("Enter the event's start date in the format YYYYMMDD: ");
    event1.setDateStart(checkValidDate());

    // event's end date
    System.out.print("Enter the event's end date in the format YYYYMMDD: ");
    event1.setDateEnd(checkValidDate());

    // event's start time
    System.out.print("Enter event's start time in 24 hr format (e.g. 4 pm = 1600): ");
    event1.setTimeStart(checkValidTime()); // calls method below to validate time entered is in correct format

    // event's end time
    System.out.print("Enter event's ending time in 24 hr format (e.g. 4 pm = 1600): ");
    event1.setTimeEnd(checkValidTime()); // calls method below to validate time entered is in correct format

    // date modified

    // UID
    // put random number gernator here, followed by @Himalian.com

    // location
    System.out.print("Enter event's location: ");
    event1.setLocation(scan.nextLine());

    // GEO
    System.out.print("Would you like to enter a latitude and longitude? Enter Y/N. ");
    String geoAnswer = scan.nextLine();
    float latitude = 0, longitude = 0;
    boolean proceed = false;
    while (!proceed) {
      if (geoAnswer.charAt(0) == 'Y' || geoAnswer.charAt(0) == 'y') {
        System.out.println("Enter coordinates in decimal degrees.");
        System.out.print("Enter latitude: ");
        latitude = scan.nextFloat(); // truncate to 6 decimal places on output
        System.out.print("Enter longitute: ");
        longitude = scan.nextFloat(); // truncate to 6 decimal places on output
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
    
    //get a filename
    scan.nextLine();
    System.out.print("Enter a name for your .ics file(.ics will be appendded): ");
    String filename = scan.nextLine();
    event1.setFilename(filename);

    // Used to test code
    System.out.println("\nTESTING BEGINS");
    System.out.println("SUMMARY:" + event1.getTitle());
    System.out.println("DESCRIPTION:" + event1.getDescription());
    System.out.println("CREATED:" + event1.getDateCreated());
    System.out.println("DTSTART:" + event1.getTimeStart());
    System.out.println("DTEND:" + event1.getTimeEnd());
    System.out.printf("GEO:%.6f; %.6f\n", latitude, longitude);
    System.out.print("CLASS:" + classification + "\n");

    //WriteFile test = new WriteFile(event1, "test.txt");
    //test.writeToFile("Testing");

    return event1;
  }


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
   * Description: creates an ics file using the info from an Event Object
   * @author Scott Leung
   * @param Event e
   */
  private static void writeIcs(Event e) throws IOException
  {
    PrintWriter pw = new PrintWriter(new FileWriter((e.getFilename() +".ics"), false));
 
    //header info
    pw.println("BEGIN:VCALENDAR");
    pw.println("PRODID:-//Team Himalia//iCalendar Assignment//EN");
    pw.println("VERSION:2.0");
    pw.println("CALSCALE:GREGORIAN");
    pw.println("METHOD:PUBLISH");
    pw.println("BEGIN:VTIMEZONE");
    pw.println("TZID:Pacific/Honolulu");
    pw.println("BEGIN:STANDARD");
    pw.println("DTSTART:19700101T000000");
    pw.println("TZOFFSETFROM:-1000");
    pw.println("TZOFFSETTO:-1000");
    pw.println("TZNAME:HST");
    pw.println("END:STANDARD");
    pw.println("END:VTIMEZONE");
    //event info begin
    pw.println("BEGIN:VEVENT");
    pw.println("DTSTART:20150708T000000Z");//start time
    pw.println("DTEND:20150708T010000Z");//end time
    pw.println("DTSTAMP:20150708T055708Z");//time stamp
    pw.println("UID:tb863cq76eqdncengt90o41ioo@google.com");//need unique id
    pw.println("CLASS:" + e.getClassType());//classification 
    pw.println("CREATED:20150707T015617Z");//time stamp
    pw.println("DESCRIPTION:" + e.getDescription());//description
    pw.println("LAST-MODIFIED:20150708T055519Z");//time stamp
    pw.println("LOCATION:" + e.getLocation());//location variable
    //pw.println("GEO:");/*need this for assignment requirement 
    //(latitude; longitude, see RFC 3.8.1.6, must be in decimal fractions of degrees)*/
    pw.println("SEQUENCE:0");
    pw.println("STATUS:CONFIRMED");
    pw.println("SUMMARY;LANGUAGE=en-us:" + e.getTitle());//title
    pw.println("TRANSP:OPAQUE");
    pw.println("END:VEVENT");
    pw.println("END:VCALENDAR");

    pw.close();
  }
}
