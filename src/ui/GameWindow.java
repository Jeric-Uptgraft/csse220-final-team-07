package ui;

import javax.swing.JFrame;

import model.GameModel;

public class GameWindow {

	public static void show() {
		// Minimal model instance (empty for now, by design)
		GameModel model = new GameModel();


		JFrame frame = new JFrame("CSSE220 Final Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		frame.add(new GameComponent(model));


		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null); // center on screen (nice UX, still minimal)
		frame.setVisible(true);
		}

}
