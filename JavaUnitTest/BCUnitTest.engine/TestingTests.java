package bcUnitTest.engine;


import java.util.logging.Logger;
import bcUnitTest.api.Order;
import bcUnitTest.api.Test;

public class TestingTests {

	private static final String TEST_CLASS_NAME = "bcUnitTest.engine.TestingTests";
	
	private static final Logger INVOCATION_LOGGER = Logger.getLogger(TEST_CLASS_NAME);
	
	private static boolean relay(boolean flag) {
		return flag;
	}
	
	@Order(value = 2)
	@Test
	public void testPassed() {
		INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@Test");
        String msg = "This test should pass";
        System.out.println(msg);
        assert relay(true) : msg;
	}
	
	
	@Test
    public void testFailed() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@Test");
        String msg = "This test should fail";
        System.out.println(msg);
        assert relay(false) : msg;
    }
	
	public void testSkipped() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@Test");
        String msg = "This test should skip";
        System.out.println(msg);
        assert relay(false) : msg;
    }
	
	@Order(value = 1)
	@Test
    public void testError() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@Test");
        String msg = "This test should cause an error";
        System.out.println(msg);
        throw new RuntimeException(msg);
    }
}
