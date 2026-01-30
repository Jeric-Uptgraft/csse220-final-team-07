package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player {
	private static final int SIZE = 40;
	private int radius;
	private int dx = 4; // direction + speed, 4 pixels per move
	private int dy = 4; // direction + speed
	// sprite cache (shared by ALL balls)
	private Color color;
	
	private int x;
    private int y;

	public Player(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public void drawOn(Graphics2D g2) {
		// TODO: Draw the house body (a rectangle) and the roof (3 lines or a
		// Polygon)
		g2.setColor(this.color);
		Rectangle rect1 = new Rectangle(x, y, SIZE, SIZE);
		g2.draw(rect1);
		g2.fill(rect1);	
		
	}

	
	public void moveUp()   { y -= dx; }
	public void moveDown()  { y += dy; }
	public void moveLeft()  { x -= dx; }
	public void moveRight() { x += dy; }
	
	
	

	public void flip() {
		// TODO Auto-generated method stub
		 dx = -dx;
	}


	

	
}
