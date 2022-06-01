package bcUnitTest.engine;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogRecord;


//TODO: Annotate and Comment throughout


public class EngineMain {
	
	private static final String TEST_CLASS_NAME = "bcUnitTest.engine.TestingTests";
	
	private static final Logger INVOCATION_LOGGER = Logger.getLogger(TEST_CLASS_NAME);

	private static final InvocationCounter INVOCATION_COUNTER = new InvocationCounter();

	static {
		INVOCATION_LOGGER.setLevel(Level.ALL);
		INVOCATION_LOGGER.addHandler(INVOCATION_COUNTER);
	}
	
	//TODO: Change to 4 after adding testSkipped
	private static final int NUMBER_OF_TESTS = 3;
	
	public static void checkPositiveResult(List<TestResult> results) {
		int size = results.size();
		switch(size) {
		case 0:
			String errorMessage = "EngineMain failed to find tests";
			throw new AssertionError(errorMessage);
		case NUMBER_OF_TESTS:
			System.out.println("EngineMain found " + NUMBER_OF_TESTS + " tests");
			break;
		default:
			String errorMessage2 = "EngineMain found " + size
				+ " testing tests, not " + NUMBER_OF_TESTS;
			throw new AssertionError(errorMessage2);
		}
	}
	
	public static void main (String[] args) {
		List<TestResult> results = TestEngine.run(TEST_CLASS_NAME);
		checkInvocation();
        INVOCATION_COUNTER.close();
        checkPositiveResult(results);
        System.out.println("All tests have PASSED");
	}
	
	public static void checkInvocation() {
		int actual = INVOCATION_COUNTER.testCount;
		if (actual != NUMBER_OF_TESTS) {
			String msg = "Expected " + NUMBER_OF_TESTS + " tests to be invoked, but only " + actual + " tests were invoked";
			throw new AssertionError(msg);
		}
	}
	
	private static class InvocationCounter extends Handler {
		private boolean open = true;
		
		int testCount = 0;
		
		@Override
		public void close() {
			this.open = false;
		}
		
		@Override
		public void flush() {
			
		}
		
		@Override
		public void publish(LogRecord record) {
			String name = record.getSourceMethodName();
			if (this.open) {
				switch (name) {
					case "@Test":
						this.testCount++;
						break;
					default:
						System.err.println("Not sure what to do with log");
						System.err.println("\"" + name + "\"");
				}
			} else {
				String excMsg = "This handler has already been closed";
				throw new IllegalStateException(excMsg);
			}
		}
	}
}
