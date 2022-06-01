package bcUnitTest.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import bcUnitTest.api.Order;
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
			HashMap<Integer, Method> tests = new HashMap<>();
			
			for (int i = 0; i < procedures.length; i++) {
				if (procedures[i].getAnnotation(Test.class) != null) {
					if(procedures[i].isAnnotationPresent(Order.class)) {
						Order value = procedures[i].getAnnotation(Order.class);
						tests.put(value.value(), procedures[i]);
					} else {
						tests.put(2147483647 - i, procedures[i]);
					}
				}
			}
			
			Iterator<Entry<Integer, Method>> testIt = tests.entrySet().iterator();
			
			while(testIt.hasNext()) {
				Map.Entry<Integer, Method> mapElement = (Map.Entry<Integer, Method>)testIt.next();
				Method procedure = mapElement.getValue();
				results.add(run(procedure, testClassInstance));
			}
			
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
