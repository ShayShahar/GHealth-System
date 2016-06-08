
import org.junit.*;
import client.control.ClientConnectionController;
import client.control.CreateClientController;
import junit.framework.TestCase;

/**
 * The Class LoginTest.
 * This class extends JUnit TestCase and check the functionality of auto creation of medical file
 */

public class MediaFileTest extends TestCase{

	CreateClientController clientCtrlr;
	
	private String avialableID = "123456789";
	private String notAvialableID = "123456788";
	private String invalidId = "a123vdh";
	private String EmptyUserName = "";

	
	/**
	 * Initialize client connection and create an instance of CreateClientController class.
	 */
	@Before
	public void setUp(){
		
		clientCtrlr = new CreateClientController();
		
		ClientConnectionController.connectToServer(ClientConnectionController.DEFAULT_IP, ClientConnectionController.DEFAULT_PORT);
			
	}
	
	/**
	 * Clean created client from db.
	 */
	@AfterClass
	public void tearDown(){
		
		ClientConnectionController.connectToServer(ClientConnectionController.DEFAULT_IP, ClientConnectionController.DEFAULT_PORT);
		clientCtrlr.deleteClient(notAvialableID);

	}

	/**
	 * Test create client when client is already available
	 */
	@Test
	public void testClientFound() {
		
		
		String execpted = "ClientFound";
		String result;
		
 		clientCtrlr.createClient("Israel", "Israeli", avialableID, "Haifa", "050-99344321", "Clalit", "none");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		result = clientCtrlr.returnMsg;
		
 		assertTrue(execpted.equalsIgnoreCase(result));
	
	}
	
	/**
	 * Test create client when client is not available yet.
	 */
	@Test
	public void testClientNotFound() {

 		clientCtrlr.createClient("Israel", "Israeli", notAvialableID, "Haifa", "050-99344321", "Clalit", "none");

 		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
 		
 		String execpted = "MedicalFileCreated";
 		String result = clientCtrlr.returnMsg;
		
 		assertTrue(execpted.equalsIgnoreCase(result));
 		 		
	}
	
	
	/**
	 * Test create client when the id is not 9 digits
	 */
	@Test
	public void testInvalidId() {

 		clientCtrlr.createClient("Israel", "Israeli", invalidId, "Haifa", "050-99344321", "Clalit", "none");

 		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
 		
 		String execpted = "Failed";
 		String result = clientCtrlr.returnMsg;
		
 		assertTrue(execpted.equalsIgnoreCase(result));
 		
	}
	
	
	/**
	 * Test create client when the id is empty.
	 */
	@Test
	public void testEmptyField() {

 		clientCtrlr.createClient("Israel", "Israeli", EmptyUserName, "Haifa", "050-99344321", "Clalit", "none");

 		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
 		
 		String execpted = "Failed";
 		String result = clientCtrlr.returnMsg;
		
 		assertTrue(execpted.equalsIgnoreCase(result));
 		
	}
	
	
}
