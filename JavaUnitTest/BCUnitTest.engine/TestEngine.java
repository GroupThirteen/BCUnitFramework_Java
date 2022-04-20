package bcUnitTest.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Annotation;

import bcUnitTest.api.Test;

public class TestEngine {
	
	//TODO: add filter() function to extract @Test annotation lines
	
	static TestResult run(Method test, Object instance) {
		try {
			test.invoke(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TestResult(test, TestStatus.SKIPPED, null);
	}
	
	public static List<TestResult> run(String testClassName) {
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		List<TestResult> results = new ArrayList<>();
		try {
			Class<?> type = loader.loadClass(testClassName);
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

