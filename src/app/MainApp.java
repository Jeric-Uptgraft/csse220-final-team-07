package app;

import javax.swing.SwingUtilities;

import ui.GameWindow;

/**
 * Class: MainApp
 * @author Team 7
 * <br>Purpose: Top level class for CSSE220 Project containing main method 
 * Entry point for the final project.
 */
public class MainApp {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
		new MainApp().run();
		System.out.println("SETUP BRANCH LIKE THIS");

		});
		System.out.println("testing");
		}
	
	public void run() {
		GameWindow.show();
		// Hint: MainApp should not contain game logic or drawing code
		}
	
}