package ui;

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

	public Enemy(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		loadSpriteOnce();
	}

	public void drawOn(Graphics2D g2) {
		if (sprite != null) {
			// sprite replaces the circle
			g2.drawImage(sprite, x, y, SIZE,SIZE, null);
			} else{ g2.setColor(this.color);
		Rectangle rect1 = new Rectangle(x, y, SIZE, SIZE);
		g2.draw(rect1);
		g2.fill(rect1);	}
		
	}
	private static void loadSpriteOnce() {
		if (triedLoad) return;
		triedLoad = true;

		try {
		
		sprite = ImageIO.read(Player.class.getResource("/ui/enemy.png"));
		} catch (IOException | IllegalArgumentException ex) {
		sprite = null; 
		}
		}
	public void moveUp()   { y -= dy; }
	public void moveDown()  { y += dy; }
	public void moveLeft()  { x -= dx; }
	public void moveRight() { x += dx; }
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
	public void ai() {
		double num = 10*Math.random();
		if(num < 2) moveLeft();
		if( 2 < num && num < 4) moveRight();
		if(4 < num && num < 6) moveUp();
		if(6 < num && num < 8) moveDown();
		update();
	}
}
