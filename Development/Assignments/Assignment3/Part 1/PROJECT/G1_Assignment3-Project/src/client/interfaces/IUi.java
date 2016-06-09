package client.interfaces;


// TODO: Auto-generated Javadoc
/**
 * An interface that used to collect all the GUI instances.
 * Used in the GUI elements stack in the main application.
 * Helps to handle the views.
 * Helps to get control on the user operations.
 * Helps to handle window switching.
 * @author shays
 *
 */

public interface IUi {

	/**
	 * displayUserWindow connects between the java code to the fxml file.
	 */
	abstract void displayUserWindow();
	
	/**
	 * hides the current stage.
	 */
	abstract void hideWindow();
	
	/**
	 * shows the current stage.
	 */
	abstract void showWindow();
	
	/**
	 * display an error message on the screen.
	 *
	 * @param title Gets the title of the message
	 * @param information Gets extra information to display in the message
	 */
	abstract void displayErrorMessage(String title, String information);
	
	/**
	 * display information message on the screen.
	 * @param title Gets the title of the message
	 * @param information Gets extra information to display in the message
	 */
	abstract void displayMessage(String title, String information);
	
}
