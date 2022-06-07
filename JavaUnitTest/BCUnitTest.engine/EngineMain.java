package bcUnitTest.engine;

import java.util.List;

public class EngineMain {
	
	// Temporary way of knowing what Test class we want to run
	private static final String TEST_CLASS_NAME = "bcUnitTest.engine.TestingTests";
	
	// Checks to make sure that the engine actually properly finds tests to run
	public static void checkPositiveResult(List<TestResult> results) {
		int size = results.size();
		switch(size) {
		case 0:
			String errorMessage = "EngineMain failed to find tests";
			throw new AssertionError(errorMessage);
		default:
			String message = "EngineMain found " + size
				+ " tests";
			System.out.println(message);
		}
	}
	
	// Main method for running the tests. Calls TestEngine to run the tests and then prints the outcomes and totals of passes and failures
	public static void main(String[] args) {
		int passCount = 0;
		int failCount = 0;
		
		List<TestResult> results = TestEngine.run(TEST_CLASS_NAME);
        checkPositiveResult(results);
        for(TestResult t : results) {
        	if (t.getStatus() == TestStatus.PASSED) {
        		passCount++;
        	}
        	if (t.getStatus() == TestStatus.FAILED) {
        		failCount++;
        	}
        	System.out.println(t.getProcedure() + " - " + t.getStatus());
        }
        
        System.out.println("\nTests passed: " + passCount + "    Tests failed: " + failCount);
	}

}
