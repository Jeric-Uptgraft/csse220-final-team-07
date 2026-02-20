package ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Class: Controller
 * @author Team 7
 * <br>Purpose: Translates keyboard input into player movement.
 */
public class Controller extends KeyAdapter {

	private final Player player;
	private final GameComponent component;

	public Controller(Player player, GameComponent component) {
		this.player = player;
		this.component = component;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!component.isPlaying()) return;
		if (e.getKeyCode() == KeyEvent.VK_W)
			player.setVelocity(0, -3);
		if (e.getKeyCode() == KeyEvent.VK_S)
			player.setVelocity(0, 3);
		if (e.getKeyCode() == KeyEvent.VK_A)
			player.setVelocity(-3, 0);
		if (e.getKeyCode() == KeyEvent.VK_D)
			player.setVelocity(3, 0);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S)
			player.stopY();
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D)
			player.stopX();
	}
}
