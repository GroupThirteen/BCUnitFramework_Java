package bcUnitTest.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


import bcUnitTest.api.Test;

public class TestEngine {
	
	//TODO: add filter() function to extract @Test annotation lines
	
	static TestResult run(Method test, Object instance) {
        TestStatus status = TestStatus.PASSED;
        Throwable info = null;
        try {
            test.invoke(instance);
        } catch (InvocationTargetException ite) {
            Throwable cause = ite.getCause();
            if (cause instanceof AssertionError) {
                status = TestStatus.FAILED;
                info = cause;
            }
        } catch (IllegalAccessException iae) {
            String excMsg = "Couldn't run test " + test.getName()
                    + " because of illegal access";
            throw new RuntimeException(excMsg, iae);
        }
        return new TestResult(test, status, info);
    }
	
	public static List<TestResult> run(String testClassName) {
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		loader.setDefaultAssertionStatus(true);
		List<TestResult> results = new ArrayList<>();
		try {
			Class<?> type = loader.loadClass(testClassName);
			@SuppressWarnings("deprecation")
			Object testClassInstance = type.newInstance();
			Method[] procedures = type.getMethods();
			List<Method> tests = new ArrayList<>();
			for (Method procedure : procedures) {
				if (procedure.getAnnotation(Test.class) != null) {
					tests.add(procedure);
				}
			}
			tests.forEach((test) -> {
				results.add(run(test, testClassInstance));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Enter class to run");
		}
	}

}
