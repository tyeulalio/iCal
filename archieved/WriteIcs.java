package iCal;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description: creates an ics file using the info from an Event Object
 * @author Scott Leung
 * @param Event e
 
public class WriteIcs (Event e) throws IOException {
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
  //(latitude; longitude, see RFC 3.8.1.6, must be in decimal fractions of degrees)
  pw.println("SEQUENCE:0");
  pw.println("STATUS:CONFIRMED");
  pw.println("SUMMARY;LANGUAGE=en-us:" + e.getTitle());//title
  pw.println("TRANSP:OPAQUE");
  pw.println("END:VEVENT");
  pw.println("END:VCALENDAR");

  pw.close();
}
}*/