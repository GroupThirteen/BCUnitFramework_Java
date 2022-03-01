package bcUnitTest.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import bcUnitTest.api.Test;

public class TestEngine {
	
	static TestResult run(Method test, Object instance) {
		return new TestResult(null, TestStatus.SKIPPED, null);
	}
	
	public static List<TestResult> run(String testClassName) {
		List<TestResult> results = new ArrayList<>();
		return results;
	}
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Enter class to run");
		}
	}

}
