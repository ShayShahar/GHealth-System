package client.interfaces;



public interface IUi {

	abstract void displayUserWindow();
	abstract void hideWindow();
	abstract void showWindow();
	abstract void closeWindow();
	abstract void displayErrorMessage(String title, String information);
	abstract void displayMessage(String title, String information);
	
}
