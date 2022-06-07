package bcUnitTest.engine;

import java.lang.reflect.Method;

public class TestResult {
	
	private final TestStatus testStatus;
	private final Throwable testStackTraceHolder;
	private final Method testProcedure;
	
	// Sets the procedure, status and stack trace information for each test to track the results
	public TestResult(Method procedure, TestStatus status, Throwable information) {
		this.testStatus = status;
		this.testStackTraceHolder = information;
		this.testProcedure = procedure;
	}
	
	// Returns test status
	public TestStatus getStatus() {
		return this.testStatus;
	}
	
	// Returns the tests' stack trace
	public Throwable getTraceHolder() {
		return this.testStackTraceHolder;
	}
	
	// Returns the name of the test method
	public Method getProcedure() {
		return this.testProcedure;
	}

}
