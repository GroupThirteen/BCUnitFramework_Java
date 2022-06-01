package bcUnitTest.engine;

import java.lang.reflect.Method;

public class TestResult {
	
	private final TestStatus testStatus;
	private final Throwable testStackTraceHolder;
	private final Method testProcedure;
	
	// TODO: Create getters for each, no setters
	
	public TestResult(Method procedure, TestStatus status, Throwable information) {
		this.testStatus = status;
		this.testStackTraceHolder = information;
		this.testProcedure = procedure;
	}
	
	public TestStatus getStatus() {
		return this.testStatus;
	}
	
	public Throwable getTraceHolder() {
		return this.testStackTraceHolder;
	}
	
	public Method getProcedure() {
		return this.testProcedure;
	}

}
