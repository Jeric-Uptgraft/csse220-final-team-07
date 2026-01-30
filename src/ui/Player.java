package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player {
	private static final int SIZE = 40;

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
}
