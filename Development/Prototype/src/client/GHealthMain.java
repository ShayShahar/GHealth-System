package client;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class GHealthMain {
	
	final public static int DEFAULT_PORT = 5551;
	public static GHealthClient clientConnect;
	
	public GHealthMain(String host, int port){
		
		try{
			clientConnect = new GHealthClient(host, port);
		}
		catch(IOException e){
			System.out.println("Error: Can't setup connection!  Terminate client.");
			System.exit(1);
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	private void readData(){
		try {
			BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
					
			String message;
			while (true) {
				
				message = consoleReader.readLine();

				@SuppressWarnings("unchecked")
				ArrayList input = new ArrayList(Arrays.asList(message.split(" ")));
				if (((String) input.get(0)).compareToIgnoreCase("update") == 0
						|| ((String) input.get(0)).compareToIgnoreCase("show") == 0) {
					clientConnect.handleMessageFromClient(input);
				} else if (((String) input.get(0)).compareToIgnoreCase("exit") == 0) {
					JOptionPane
					.showMessageDialog(null,"You are out of the system.","GHealth System", JOptionPane.INFORMATION_MESSAGE);
					System.exit(1);
				}

			}
		} catch (Exception ex) {
			System.out.println("Unexpected error while reading from console!");
		}
	
	}
	
	public static void main(String[] args) {

		String host = "";

		try {
			host = JOptionPane.showInputDialog("Enter host IP");
		} catch (ArrayIndexOutOfBoundsException e) {
			host = "localhost";
		}
		GHealthMain gHealthSystem = new GHealthMain(host, DEFAULT_PORT);

		if (gHealthSystem != null) {
			System.out.println("Connection to " + host + " succeeded!");
			JOptionPane
					.showMessageDialog(
							null,
							"Enter \"Show\" to view File table.\nEnter \"Update Physician_Name Physician_Family NEW_SPECIALIZATION\" "
							+ "for insert new data to Physician table.\nEnter \"Exit\" for termineting.",
							"GHealth System", JOptionPane.INFORMATION_MESSAGE);

			gHealthSystem.readData(); // Wait for console data.
		}

	}

}
