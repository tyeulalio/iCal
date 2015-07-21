package iCal;

import static org.junit.Assert.*;

import org.junit.*;

public class TimezoneTest {

	@Test
	public void testCreateArray() {
		String[][] tzArray = new String[87][2];
		
		for (int i=0; i<87; i++){
			String offSet = tzArray[i][0];
			assertNull(offSet);
		}
	}

}
