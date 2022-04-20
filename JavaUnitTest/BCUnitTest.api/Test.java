package bcUnitTest.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

//Acts as our test annotation in the framework
public @interface Test {

	// TODO: Create a run class to handle the tests
	
}
