package iCal;

public class TestMode {
	public static boolean testMode = false;
	
	public static boolean getTestMode() {
		return testMode;
	}
	
	public String toString() {
		if (testMode==false) {
			return ("OFF");
		}
		else if (testMode == true) {
			return ("ON");
		}
		return null;
	}
	
	public static void setTestMode() {
		if (testMode==false){
			testMode = true;
		}
		else if (testMode==true) {
			testMode = false;
		}
	}
	
}
