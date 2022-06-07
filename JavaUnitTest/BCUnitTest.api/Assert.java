package bcUnitTest.api;

public class Assert {
	
	// Compares two integers, relays true if they are equal
	public static void equals(int a, int b) {
		if (a == b) {
			assert relay(true);
		} else {
			assert relay(false);
		}
	}
	
	// Compares two doubles, relays true if they are equal
	public static void equals(double a, double b) {
		if (a == b) {
			assert relay(true);
		} else {
			assert relay(false);
		}
	}
	
	// Compares two longs, relays true if they are equal
	public static void equals(long a, long b) {
		if (a == b) {
			assert relay(true);
		} else {
			assert relay(false);
		}
	}
	
	// Compares two strings, relays true if they are equal
	public static void equals(String a, String b) {
		if (a == b) {
			assert relay(true);
		} else {
			assert relay(false);
		}
	}
	
	// Compares two chars, relays true if they are equal
	public static void equals(char a, char b) {
		if (a == b) {
			assert relay(true);
		} else {
			assert relay(false);
		}
	}
	
	// Compares two booleans, relays true if they both are true, or both are false
	public static void equals(boolean a, boolean b) {
		if (a == true && b == true) {
			assert relay(true);
		} else if (a == false && b == false) {
			assert relay(true);
		} else {
			assert relay(false);
		}
	}
	
	// The relay flag determines if a test passes or fails
	public static boolean relay(boolean flag) {
		return flag;
	}

}
