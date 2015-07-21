package iCal;

public class TestMode {
	public static boolean testMode = false;
	
	// Returns the status of TestMode
	public static boolean getTestMode() {
		return testMode;
	}
	
	// For printing the status of TestMode
	public String toString() {
		if (testMode==false) {
			return ("OFF");
		}
		else if (testMode == true) {
			return ("ON");
		}
		return null;
	}
	
	// Changes the status of TestMode
	public static void setTestMode() {
		if (testMode==false){
			testMode = true;
		}
		else if (testMode==true) {
			testMode = false;
		}
	}
	
}
