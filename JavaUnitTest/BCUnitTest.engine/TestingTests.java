package bcUnitTest.engine;


import bcUnitTest.api.AfterAllTests;
import bcUnitTest.api.Assert;
import bcUnitTest.api.BeforeAllTests;
import bcUnitTest.api.Order;
import bcUnitTest.api.Test;

public class TestingTests {
	
	@Order(1)
	@Test
	public void intTest() {
		// This test should pass
		Assert.equals(2, 2);
	}
	
	@Order(2)
	@Test
	public void intTest2() {
		// This test should fail
		Assert.equals(3, 2);
	}
	
	@Test
	public void doubleTest2() {
		// This test should fail
		Assert.equals(3.2, 4.2);
	}
	
	@BeforeAllTests
	@Test
	public void doubleTest() {
		// This test should pass
		Assert.equals(3.2, 3.2);
	}
	
	@Order(3)
	@Test
	public void stringTest() {
		// This test should pass
		Assert.equals("Pass", "Pass");
	}
	
	@Order(4)
	@Test
	public void stringTest2() {
		// This test should fail
		Assert.equals("Pass", "Fail");
	}
	
	public void testSkipped() {
        // This test should skip all together
    }
}












