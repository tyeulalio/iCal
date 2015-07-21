package iCal;

import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest {

	@Test
	public void testConvertToUTC() {
		String noTimezone = Event.convertToUTC("20150812", "130000", null);
		assertNull(noTimezone);
		
		String hawaiiTz = Event.convertToUTC("20150812", "130000", "-1000");
		assertEquals("20150812T230000Z", hawaiiTz);
		
		String arizonaTz = Event.convertToUTC("20150709", "133000", "-700");
		assertEquals("20150709T203000Z", arizonaTz);
		
		String nukuTz = Event.convertToUTC("20161024", "000000", "+1300");
		assertEquals("20161023T110000Z", nukuTz);
	}
	
	//@Test
	//public void test

}
