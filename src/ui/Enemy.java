

package ui;
/**
 * Class: Enemy
 * @author Team 7 
 * Purpose: This class contains all of the information about the enemies in the game. It controls their movement, 
 * sprites, and collision reaction information
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class Enemy {
	static final int SIZE = 50;

	private Color color;
	private List<Point> path;
	private int pathIndex = 0;
	private int speed = 2;
	private int step = 1; // +1 forward, -1 backward
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
	public void setPath(List<Point> p) {
	    path = p;
	    pathIndex = 0;
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
		if(x > 600) {
			x = 600;
		}
		if(y<0) {
			y =0;
		}
		if(y>600) {
			y = 600;
		}
		
	}
	/**
	 * decides where the enemy moves
	 */
	public void ai() { 
		if (path == null || path.isEmpty()) return;

	    Point target = path.get(pathIndex); //gets current Path index

	    int currentx = x + SIZE / 2; //coordinate for middle of enemy
	    int currenty = y + SIZE / 2;

	    int tx = target.x; //target x coordinate that enemy should be at
	    int ty = target.y;

	    // If close enough with respect to speed, go to next path index
	    if (Math.abs(currentx - tx) < speed && Math.abs(currenty - ty) < speed) {
	    	pathIndex += step;

	        
	        if (pathIndex >= path.size()) {
	            step = -1;                 // start going backward when at end of indexes
	            pathIndex = path.size() - 2;

	        }
	        else if (pathIndex < 0) {
	            step = 1;                  // start going forward again
	            pathIndex = 1;
	        }
	    }

	    // Move horizontally
	    if (currentx < tx)  x += speed; //move positive x direction if current x is less than target x
	       
	     else if (currentx > tx) x -= speed;

	        
	    

	    // Move vertically
	    if (currenty < ty)  y += speed;
	       
	    else if (currenty > ty) y -= speed;

	    

	    update();
	}
	public Rectangle getBounds() {
	    return new Rectangle(this.x, this.y, SIZE, SIZE); //enemy bounding box
	}
	public void setPosition(int px, int py) {
		// TODO Auto-generated method stub
		 this.x = px;
		    this.y = py;
	}
	public void resetPath() {
		pathIndex = 0;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

}
