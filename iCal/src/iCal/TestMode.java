package iCal;

public class TestMode {
	public static boolean testMode = true;
	
	// Returns the status of TestMode
	public static boolean getTestMode() {
		return testMode;
	}
	
	// For printing the status of TestMode
	public String makeString() {
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
