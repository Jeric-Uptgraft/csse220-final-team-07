package ui;

import javax.swing.JFrame;

import model.GameModel;

/**
 * Class: GameWindow
 * 
 * @author Team 7
 * 
 * <br>
 * Purpose:
 * In charge of displaying the game window.
 * This class is responsible for creating the application frame,
 * initializing the game model, attaching the main GameComponent,
 * and making the window visible to the user.
 */


public class GameWindow {

	public static void show() {
		// Minimal model instance (empty for now, by design)
		GameModel model = new GameModel();


		JFrame frame = new JFrame("CSSE220 Final Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		frame.add(new GameComponent(model));


		frame.setSize(560, 580);
		frame.setLocationRelativeTo(null); // center on screen (nice UX, still minimal)
		frame.setVisible(true);
		}

}
