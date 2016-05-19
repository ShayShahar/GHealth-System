package client.interfaces;


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

	abstract void displayUserWindow();
	abstract void hideWindow();
	abstract void showWindow();
	abstract void displayErrorMessage(String title, String information);
	abstract void displayMessage(String title, String information);
	
}
