package unittests;

import org.junit.Test;
import client.control.ClientConnectionController;
import client.control.UserController;
import junit.framework.TestCase;

/**
 * The Class LoginTest.
 * This class extends JUnit TestCase and check the functionality of UserController login method.
 */
public class LoginTest extends TestCase{
	
	UserController userController;
	
	private String CorrectPassword = "11";
	private String WrongPassword = "abc";
	private String CorrectUserName = "shay";
	private String WrongUserName = "yakir123";
	private String EmptyUserName = "";
	private String LoggedUserName = "dummy"; //dummy user always logged-in in the db
	private String LoggedUserPassword = "dummy";

	
	/**
	 * Initialize client connection and create instance of UserController class
	 */
	public void setUp(){
		
		userController = new UserController();
		ClientConnectionController.connectToServer(ClientConnectionController.DEFAULT_IP, ClientConnectionController.DEFAULT_PORT);
				
	}
	
	/**
	 * Test login with correct user name and password.
	 */
	@Test
	public void testLoginSuccess() {
		
		String execpted = "LoginSuccess";
		String result;
		
		userController.validateUser(CorrectUserName, CorrectPassword);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		result = userController.returnMsg;
		
 		assertTrue(execpted.equalsIgnoreCase(result));
	
		
	}
	
	/**
	 * Test a case for wrong password and available user name
	 */
	@Test
	public void testWrongPassword(){
		
		String execpted = "WrongPassword";
		String result;
		
		userController.validateUser(CorrectUserName, WrongPassword);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		result = userController.returnMsg;
		
 		assertTrue(execpted.equalsIgnoreCase(result));
		
	}
	
	
	/**
	 * Test a case for unavailable user name in the db
	 */
	@Test
	public void testWrongUserName(){
		
		String execpted = "WrongUser";
		String result;
		
		userController.validateUser(WrongUserName, WrongPassword);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		result = userController.returnMsg;
		
 		assertTrue(execpted.equalsIgnoreCase(result));
		
	}
	
	/**
	 * Test a case for empty user name field.
	 */
	@Test
	public void testEmptyUserName(){
		
		String execpted = "WrongUser";
		String result;
		
		userController.validateUser(EmptyUserName, WrongPassword);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		result = userController.returnMsg;
		
 		assertTrue(execpted.equalsIgnoreCase(result));
		
	}
	
	
	/**
	 * Test a case for empty password field.
	 */
	@Test
	public void testEmptyPassword(){
		
		String execpted = "WrongUser";
		String result;
		
		userController.validateUser(EmptyUserName, EmptyUserName);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		result = userController.returnMsg;
		
 		assertTrue(execpted.equalsIgnoreCase(result));
		
	}
	
	
	/**
	 * Test a case for a already logged-in  user name field.
	 * The test try to log-in with the dummy user (a user that declared in db which always logged-in)
	 */
	@Test
	public void testAlreadyLogin(){
		
		String execpted = "AlreadyLogin";
		String result;
		
		userController.validateUser(LoggedUserName, LoggedUserPassword);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		result = userController.returnMsg;
		
 		assertTrue(execpted.equalsIgnoreCase(result));
		
	}
	

}
