package iCal;

import java.util.Scanner;
import java.util.UUID;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Comparator;


public class Main {

  public static void main(String[] args) throws IOException {
    displayMenu();
    
    /*
    //for testing the linked list. it works.:)
    EventLinkedList<Event> cal = new EventLinkedList<Event>();
    Comparator c = (Comparator) new Event();
    
    cal.add(gatherData());
    cal.insertionSort(c);
    cal.add(gatherData());
    cal.insertionSort(c);
    System.out.println(cal);
    */
  }

public static void displayMenu() throws IOException {
  // Creates a (linked list) called 'cal' to store events
  EventLinkedList<Event> cal = new EventLinkedList<Event>();
  Comparator c = (Comparator) new Event();
  String choice = "";
  
  System.out.print("Welcome! ");
  // Displays menu of options
  do {
	  Scanner keyboard = new Scanner(System.in);
	  if (!(choice.equalsIgnoreCase("Quit") || choice.equals("3"))) {
		  System.out.print("\nType the number or the capitalized word to choose from below:\n" +
	              "1) CREATE an event file and add to calendar\n" +
	              "2) IMPORT .ics files to calendar\n" +
	              "3) TEST \n" +
	              "4) VIEW calendar\n" +
	              "10) QUIT\n: ");
	  }
		  
    try {
    	choice = keyboard.nextLine();
    	
        // 1. Creates an event
        if (choice.equalsIgnoreCase("Create") || choice.equals("1")) {
        	Event new1 = new Event();
            new1 = gatherData();
            writeIcs(new1);
            cal.add(new1);
        }
        
        // 2. Import an event file
        else if (choice.equalsIgnoreCase("Import") || choice.equals("2")) {
             System.out.println("Please enter the name of your file (Do not include .ics/): ");
             String fileName = keyboard.nextLine();
             // check if input contains .ics at the end
             // int fileNameLength = fileName.length();
             String fileNameEnding = fileName.substring(fileName.length()-4); //last four char of fileName
             if (fileNameEnding.equals(".ics")) 
            	  fileName = fileName.substring(0,fileName.length()-4);
              // append .ics to end of fileName
             fileName += ".ics";
        	
        	  ReadFile read = new ReadFile(fileName, cal);
        	  read.ReadFile();
      	    
      	  // The next 3 lines are being used to test ReadFile
      	  cal.insertionSort(c);
      	  System.out.println("ReadFile being tested:");
          System.out.println(cal);
        }
         
        // 3. Tests
        if (choice.equalsIgnoreCase("Test") || choice.equals("3")) {
          //Event(String titlex, String descriptx, String dateStartx, String dateEndx, String timeStartx, String timeEndx) {
          Event test1 = new Event ("Test title", "Test descript", "20150714", "20150714", "140000", "143000", 22, 114);
          Event test2 = new Event ("Test title", "Test descript", "20150714", "20150714", "150000", "153000", 9, 7);
          Event test3 = new Event ("Test title", "Test descript", "20150714", "20150714", "160000", "153000", 9, 7);
          cal.add(test1);
          cal.add(test3);
          cal.add(test2);
          cal.insertionSort(c);
          System.out.println(cal);
        }
        
        // 4. View calendar linked list
        if (choice.equalsIgnoreCase("Test") || choice.equals("4")) {
          System.out.println(cal);
        }
        
        // . Quit program  
        else if (choice.equalsIgnoreCase("Quit") || choice.equals("10")) {
          System.out.print("Have a nice day.");
        }
    }
    catch (Exception e) {
  	  // Error message
  	  System.out.println("Some kind of error occurred.");
  	  keyboard.nextLine();                   // Clear the input buffer
    
    }
    finally {

    }
  } while (!(choice.equalsIgnoreCase("Quit")) && !(choice.equals("10")));
    
    
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
    
    // Time Zone Selection
    System.out.print("Please select a timezone (enter numbers only): \n");
    String format = "%-76s%s%n";
    String format2 = "%-76s%n";
    
    String prefix1 = "(UTC-1000) Hawaii";
    String prefix2 = "(UTC+0300) Nairobi";
    System.out.printf(format, prefix1, prefix2);
    
    String prefix3 = "(UTC-0800) Alaska";
    String prefix4 = "(UTC+0300) Baghdad";
    System.out.printf(format, prefix3, prefix4);
    
    String prefix5 = "(UTC-0700) Pacific time (US and Canada)";
    String prefix6 = "(UTC+0400) Moscow, St. Petersburg, Volgograd";
    System.out.printf(format, prefix5, prefix6);
    
    String prefix7 = "(UTC-0700) Tijuana, Baja California";
    String prefix8 = "(UTC+0400) Abu Dhabi, Muscat";
    System.out.printf(format, prefix7, prefix8);
   
    String prefix9 = "(UTC-0700) Arizona";
    String prefix10 = "(UTC+0400) Port Louis";
    System.out.printf(format, prefix9, prefix10);
    
    String prefix11 = "(UTC-0600) Mountain Time (US and Canada)";
    String prefix12 = "(UTC+0400) Yerevan";
    System.out.printf(format, prefix11, prefix12);
    
    String prefix13 = "(UTC-0600) Central America";
    String prefix14 = "(UTC+0400) Tbilisi";
    System.out.printf(format, prefix13, prefix14);
    
    String prefix15 = "(UTC-0600) Saskatchewan";
    String prefix16 = "(UTC+0430) Tehran";
    System.out.printf(format, prefix15, prefix16);
    
    String prefix17 = "(UTC-0500) Central Time (US and Canada)";
    String prefix18 = "(UTC+0430) Kabul";
    System.out.printf(format, prefix17, prefix18);
    
    String prefix19 = "(UTC-0500) Bogota, Lima, Quito";
    String prefix20 = "(UTC+0500) Baku";
    System.out.printf(format, prefix19, prefix20);
    
    String prefix21 = "(UTC-0500) Mexico City";
    String prefix22 = "(UTC+0500) Islamabad, Karachi";
    System.out.printf(format, prefix21, prefix22);
    
    String prefix23 = "(UTC-0430) Caracas";
    String prefix24 = "(UTC+0500) Tashkent";
    System.out.printf(format, prefix23, prefix24);
    
    String prefix25 = "(UTC-0400) Eastern Time (US and Canada)";
    String prefix26 = "(UTC+0530) Chennai, Kolkata, Mumbai, New Delhi";
    System.out.printf(format, prefix25, prefix26);
     
    String prefix27 = "(UTC-0400) Manaus";
    String prefix28 = "(UTC+0530) Sri Jayawardenepura";
    System.out.printf(format, prefix27, prefix28);
    
    String prefix29 = "(UTC-0400) Georgetown";
    String prefix30 = "(UTC+0545) Kathmandu";
    System.out.printf(format, prefix29, prefix30);
    
    String prefix31 = "(UTC-0400) Santiago";
    String prefix32 = "(UTC+0600) Ekaterinburg";
    System.out.printf(format, prefix31, prefix32);
    
    String prefix33 = "(UTC-0400) La Paz";
    String prefix34 = "(UTC+0600) Astana, Dhaka";
    System.out.printf(format, prefix33, prefix34);
    
    String prefix35 = "(UTC-0400) Asuncion";
    String prefix36 = "(UTC+0630) Rangoon";
    System.out.printf(format, prefix35, prefix36);
    
    String prefix37 = "(UTC-0400) Indiana (East)";
    String prefix38 = "(UTC+0700) Novosibirsk";
    System.out.printf(format, prefix37, prefix38);
    
    String prefix39 = "(UTC-0300) Brasilla";
    String prefix40 = "(UTC+0700) Bangkok, Hanoi, Jakarta";
    System.out.printf(format, prefix39, prefix40);
    
    String prefix41 = "(UTC-0300) Montevideo";
    String prefix42 = "(UTC+0800) Krasnoyarsk";
    System.out.printf(format, prefix41, prefix42);
    
    String prefix43 = "(UTC-0300) Atlantic Time (Canada)";
    String prefix44 = "(UTC+0800) Perth";
    System.out.printf(format, prefix43, prefix44);
    
    String prefix45 = "(UTC-0300) Buenos Aires";
    String prefix46 = "(UTC+0800) Taipei";
    System.out.printf(format, prefix45, prefix46);
    
    String prefix47 = "(UTC-0300) Cayenne";
    String prefix48 = "(UTC+0800) Beijing, Chongqing, Hong Kong, Urumqi";
    System.out.printf(format, prefix47, prefix48);
    
    String prefix49 = "(UTC-0230) Newfoundland";
    String prefix50 = "(UTC+0800) Kuala Lumpur, Singapore";
    System.out.printf(format, prefix49, prefix50);
    
    String prefix51 = "(UTC-0200) Mid-Atlantic";
    String prefix52 = "(UTC+0800) Ulaan Bataar";
    System.out.printf(format, prefix51, prefix52);
    
    String prefix53 = "(UTC-0200) Greenland";
    String prefix54 = "(UTC+0900) Irkutsk";
    System.out.printf(format, prefix53, prefix54);
   
    String prefix55 = "(UTC-0100) Cape Verde Is.";
    String prefix56 = "(UTC+0900) Osaka, Sapporo, Tokyo";
    System.out.printf(format, prefix55, prefix56);
   
    String prefix57 = "(UTC) Monrovia";
    String prefix58 = "(UTC+0900) Seoul";
    System.out.printf(format, prefix57, prefix58);
    
    String prefix59 = "(UTC) Azores";
    String prefix60 = "(UTC+0930) Darwin";
    System.out.printf(format, prefix59, prefix60);
   
    String prefix61 = "(UTC+0100) Casablanca";
    String prefix62 = "(UTC+0930) Adelaide";
    System.out.printf(format, prefix61, prefix62);
    
    String prefix63 = "(UTC+0100) Greenwich Mean Time: Dublin, Edinburgh, Lisbon, London";
    String prefix64 = "(UTC+1000) Yakutsk";
    System.out.printf(format, prefix63, prefix64);
    
    String prefix65 = "(UTC+0100) West Central Africa";
    String prefix66 = "(UTC+1000) Brisbane";
    System.out.printf(format, prefix65, prefix66);
    
    String prefix67 = "(UTC+0200) Cairo";
    String prefix68 = "(UTC+1000) Guam, Port Moresby";
    System.out.printf(format, prefix67, prefix68);
    
    String prefix69 = "(UTC+0200) Harare, Pretoria";
    String prefix70 = "(UTC+1000) Hobart";
    System.out.printf(format, prefix69, prefix70);
    
    String prefix71 = "(UTC+0200) Sarajevo, Skopje, Warsaw, Zagreb";
    String prefix72 = "(UTC+1000) Canberra, Melbourne, Sydney";
    System.out.printf(format, prefix71, prefix72);
    
    String prefix73 = "(UTC+0200) Brussels, Copenhagen, Madrid, Paris";
    String prefix74 = "(UTC+1100) Vladivostok";
    System.out.printf(format, prefix73, prefix74);
    
    String prefix75 = "(UTC+0200) Belgrade, Bratislava, Budapest, Ljubljana, Prague";
    String prefix76 = "(UTC+1100) Magadan, Solomon Is., New Caledonia";
    System.out.printf(format, prefix75, prefix76);
    
    String prefix77 = "(UTC+0200) Amsterdam, Berlin, Bern, Rome, Stockholm, Vienna";
    String prefix78 = "(UTC+1200) Fiji, Marshall Is.";
    System.out.printf(format, prefix77, prefix78);
    
    String prefix79 = "(UTC+0300) Athens, Istanbul, Minsk";
    String prefix80 = "(UTC+1200) Guam, Kamchatka";
    System.out.printf(format, prefix79, prefix80);
    
    String prefix81 = "(UTC+0300) Bucharest";
    String prefix82 = "(UTC+1200) Auckland, Wellington";
    System.out.printf(format, prefix81, prefix82);
    
    String prefix83 = "(UTC+0300) Helsinki, Kyiv, Riga, Sofia, Tallinn, Vilnius";
    String prefix84 = "(UTC+1200) International Date Line West (Eniwetok, Kwajalein)";
    System.out.printf(format, prefix83, prefix84);
    
    String prefix85 = "(UTC+0300) Jerusalem";
    String prefix86 = "(UTC+1300) Nuku'alofa";
    System.out.printf(format, prefix85, prefix86);
   
    String prefix87 = "(UTC+0300) Kuwait, Riyadh";
    System.out.printf(format2, prefix87);
    

    // Date modified
    event1.setDtstamp(event1.getDateTime());

    // UUID (Randomly generates number here, followed by @Himalian.com)
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
    System.out.println("\nNew event summary:");
    System.out.println("SUMMARY:" + event1.getTitle());
    System.out.println("DESCRIPTION:" + event1.getDtstamp());
    System.out.println("CREATED:" + event1.getDateCreated());
    System.out.println("Last Modified:" + event1.getDtstamp());
    System.out.println("DTSTART:" + event1.getTimeStart());
    System.out.println("DTEND:" + event1.getTimeEnd());
    System.out.printf("GEO:%.6f; %.6f\n", event1.getLatitude(), event1.getLongitude());
    System.out.print("CLASS:" + classification + "\n");
    System.out.println("UID:" + event1.getUUID() + "\n");
    //System.out.println("COMMENT:" + event1.getComment() + "\n");

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
    pw.printf("%s%s%s%s%n", "DTSTART:", event1.getDateStart(), "T", event1.getTimeStart());//start time
    pw.printf("%s%s%s%s%s%n", "DTEND:", event1.getDateEnd(), "T", event1.getTimeEnd());//end time
    pw.printf("%s%s%n", "DTSTAMP:", event1.getDateCreated());//time stamp
    pw.printf("%s%n", "UID:" + event1.getUUID() + "@Himalia.com");//need unique id
    pw.printf("%s%s%n", "CLASS:", event1.getClassType());//classification 
    pw.printf("%s%n", "CREATED:" + event1.getDateCreated());//time created stamp
  
    pw.printf("%s%s%n", "DESCRIPTION:", event1.getDescription());//description
  //pw.printf("%s%S%n", "COMMENT:", event1.getComment()); //comment   
 //pw.printf("%s%s%n", "LAST-MODIFIED:", event1.getDateModified());//time modified stamp -- we don't need last-modified, same as dtstamp
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
