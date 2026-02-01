package ui;
/**
 * Class: Enemy
 * @author Team 7 
 * Purpose: This class contains all of the information about the enemies in the game. It controls their movement, 
 * sprites, and collision reaction information
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy {
	private static final int SIZE = 40;

	private Color color;
	
	private int x;
    private int y;
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
	private int dx = 4; // direction + speed, 4 pixels per move
	private int dy = 4; // direction + speed
	/**
	 * Constructor for Enemies
	 * @param x for the starting x position, y for the starting y position, and color for the enemy color
	 */
	public Enemy(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		loadSpriteOnce();
	}
/**
 * Draws the enemy on the panel
 * @param g2: Graphics2d that is used to draw
 */
	public void drawOn(Graphics2D g2) {
		if (sprite != null) {
			// sprite replaces the circle
			g2.drawImage(sprite, x, y, SIZE,SIZE, null);
			} else{ g2.setColor(this.color);
		Rectangle rect1 = new Rectangle(x, y, SIZE, SIZE);
		g2.draw(rect1);
		g2.fill(rect1);	}
		
	}
	/**
	 * Loads the sprite given in the package
	 */
	private static void loadSpriteOnce() {
		if (triedLoad) return;
		triedLoad = true;

		try {
		
		sprite = ImageIO.read(Player.class.getResource("/ui/enemy.png"));
		} catch (IOException | IllegalArgumentException ex) {
		sprite = null; 
		}
		}
	/**
	 * moves enemy up
	 */
	public void moveUp()   { y -= dy; }
	/**
	 * moves enemy down
	 */
	public void moveDown()  { y += dy; }
	/**
	 * moves enemy left
	 */
	public void moveLeft()  { x -= dx; }
	/**
	 * moves enemy right
	 */
	public void moveRight() { x += dx; }
	/**
	 * checks collisions with borders
	 */
	public void update() {
		// Left wall
		if (x < 0) {
			x = 0; // clamp
		}
		if(x > 500) {
			x = 500;
		}
		if(y<0) {
			y =0;
		}
		if(y>500) {
			y = 500;
		}
		
	}
	/**
	 * decides where the enemy moves
	 */
	public void ai() { 
		double num = 10*Math.random();
		if(num < 2) moveLeft();
		if( 2 < num && num < 4) moveRight();
		if(4 < num && num < 6) moveUp();
		if(6 < num && num < 8) moveDown();
		update();
	}
}
