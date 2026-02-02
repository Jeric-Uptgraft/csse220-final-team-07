package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class: Player
 * 
 * @author Team 7
 * 
 * <br>
 * Purpose:
 * Represents the player-controlled character in the game.
 * The Player has position, movement speed, color,
 * and optional sprite image. It can move in four directions,
 * draw itself on the screen, and remain within screen bounds.
 */

public class Player {
	public static final int SIZE = 25;
	private int radius;
	private int dx = 4; // direction + speed, 4 pixels per move
	private int dy = 4; // direction + speed
	// sprite cache (shared by ALL balls)
	private Color color;
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
	private int x;
    private int y;

	public Player(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		loadSpriteOnce();
	}

	public void drawOn(Graphics2D g2) {
		// TODO: Draw the house body (a rectangle) and the roof (3 lines or a
		// Polygon)
		if (sprite != null) {
			// sprite replaces the circle
			g2.drawImage(sprite, x, y, SIZE,SIZE, null);
			} else {
		
		g2.setColor(this.color);
		Rectangle rect1 = new Rectangle(x, y, SIZE, SIZE);
		g2.draw(rect1);
		g2.fill(rect1);	
			}
	}

	
	public void setVelocity(int dx, int dy) {
	    this.dx = dx;
	    this.dy = dy;
	}

	public void stopX() { dx = 0; }
	public void stopY() { dy = 0; }

	public int getDx() { return dx; }
	public int getDy() { return dy; }

	public int getX() { return x; }
	public int getY() { return y; }

	public void setPosition(int x, int y) {
	    this.x = x;
	    this.y = y;
	}
	

	public void flip() {
		// TODO Auto-generated method stub
		 dx = -dx;
	}

	public void update() {
		// Left wall
		if (x < 0) x = 0; // clamp
		if(x > 500) x = 500;
		if(y<0) y =0;
		if(y>500) y = 500;
		
	}
	private static void loadSpriteOnce() {
		if (triedLoad) return;
		triedLoad = true;

		try {
		
		sprite = ImageIO.read(Player.class.getResource("/ui/playerimg.png"));
		} catch (IOException | IllegalArgumentException ex) {
		sprite = null; 
		}
		}

	

	
}
