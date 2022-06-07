package bcUnitTest.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import bcUnitTest.api.AfterAllTests;
import bcUnitTest.api.BeforeAllTests;
import bcUnitTest.api.Order;
import bcUnitTest.api.Test;

public class TestEngine {
	
	// Double parameter run method to invoke each test by it's individual instance and return the outcome
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
        // Returns TestResult including the status, the name of the method and the stack trace
        return new TestResult(test, status, info);
    }
	
	// Single parameter run method to load the test class, and run all test methods, loading them into a final list
	public static List<TestResult> run(String testClassName) {
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		loader.setDefaultAssertionStatus(true);
		List<TestResult> results = new ArrayList<>();
		
		try {
			Class<?> type = loader.loadClass(testClassName);
			@SuppressWarnings("deprecation")
			Object testClassInstance = type.newInstance();
			// All the methods that are in the class, including ones that aren't test methods
			Method[] procedures = type.getMethods();
			// Hashmap acts as a way to order the methods based on the key that is assigned to them later
			HashMap<Integer, Method> tests = new HashMap<>();
			List<Method> beforeTests = new ArrayList<>();
			List<Method> afterTests = new ArrayList<>();
			
			// Goes through each method and finds the @Test/@Order annotations
			for (int i = 0; i < procedures.length; i++) {
				if (procedures[i].getAnnotation(Test.class) != null) {
					// If beforeTest annotation is present, add to separate beforeTests list
					if(procedures[i].isAnnotationPresent(BeforeAllTests.class)) {
						beforeTests.add(procedures[i]);
					// If afterTest annotation is present, add to separate afterTests list	
					} else if(procedures[i].isAnnotationPresent(AfterAllTests.class)) {
						afterTests.add(procedures[i]);
					// If order annotation is present, add to hashmap in the front	
					} else if(procedures[i].isAnnotationPresent(Order.class)) {
						Order value = procedures[i].getAnnotation(Order.class);
						tests.put(value.value(), procedures[i]);
					// If only the test annotation is present, add to the back of the hashmap	
					} else {
						tests.put(2147483647 - i, procedures[i]);
					}
				}
			}
			
			// Runs any methods in the beforeTests category, if there are any
			if(beforeTests != null) {
				for(Method m: beforeTests) {
					results.add(run(m, testClassInstance));
				}
			}
			
			Iterator<Entry<Integer, Method>> testIt = tests.entrySet().iterator();
			
			// Iterates through the hashmap, running each test and adding the results to the final results list
			while(testIt.hasNext()) {
				Map.Entry<Integer, Method> mapElement = (Map.Entry<Integer, Method>)testIt.next();
				Method procedure = mapElement.getValue();
				results.add(run(procedure, testClassInstance));
			}
			
			// Runs any methods in the afterTests category, if there are any
			if(afterTests != null) {
				for(Method m: afterTests) {
					results.add(run(m, testClassInstance));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Returns the final results of all the tests for EngineMain to print
		return results;
	}
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Enter class to run");
		}
	}

}
