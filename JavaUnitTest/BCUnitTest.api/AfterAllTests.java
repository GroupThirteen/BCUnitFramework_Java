package bcUnitTest.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

// Acts as our test annotation in the framework, even though it doesn't directly do anything
public @interface AfterAllTests {
	
}
