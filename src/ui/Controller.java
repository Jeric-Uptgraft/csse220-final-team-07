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
	
	    // Check if shift is held and they have energy
	public void keyPressed(KeyEvent e) {
	    if (!component.isPlaying()) return;

	    // Determine if we can sprint: Shift is down and not exhausted and has some stamina
	    boolean wantToSprint = e.isShiftDown() && !player.isExhausted() && player.getStamina() > 0;
	    
	    player.setSprinting(wantToSprint);
	    int speed = wantToSprint ? 4 : 3;

	    // Handle mid-movement Shift
	    if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
	        int vx = (int) Math.signum(player.getDx()) * speed;
	        int vy = (int) Math.signum(player.getDy()) * speed;
	        player.setVelocity(vx, vy);
	    }

	    // Directional keys
	    if (e.getKeyCode() == KeyEvent.VK_W) player.setVelocity(0, -speed);
	    if (e.getKeyCode() == KeyEvent.VK_S) player.setVelocity(0, speed);
	    if (e.getKeyCode() == KeyEvent.VK_A) player.setVelocity(-speed, 0);
	    if (e.getKeyCode() == KeyEvent.VK_D) player.setVelocity(speed, 0);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
	        player.setSprinting(false);
	        // If they were exhausted, let go of shift resets them so they can sprint again later
	        player.setExhausted(false); 
	        
	        int vx = (int) Math.signum(player.getDx()) * 3;
	        int vy = (int) Math.signum(player.getDy()) * 3;
	        player.setVelocity(vx, vy);
	    }

	        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S)
	            player.stopY();
	        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D)
	            player.stopX();
	    }
}
