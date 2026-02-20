package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class: Coin
 * @author Team 7 
 * Purpose: This class contains the functionality for Coins. It defines the characteristics, implements their sprite,
 * and implements getBounds() functionality for collision behavior.
 */
public class Coin {
	// Constants
    private static final Color BORDER_COLOR = Color.BLACK;
    private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
    	
	// Instance fields
    private int x, y;
    private double circleDiameter;
    private Color color;
	
    public Coin() {
    	this(0, 0, 30.0, Color.YELLOW);
    }

	public Coin(int x, int y, double circleDiameter, Color Color) {
		this.x = x;
	    this.y = y;
	    this.circleDiameter = circleDiameter;
	    this.color = Color;
	    loadSpriteOnce();
	    
	    
	    
	}
	
	public void drawOn(Graphics2D g2) {
        // Draw the circle
       
        if (sprite != null) {
            // Draw the image at the coin's x, y coordinates
            g2.drawImage(sprite, x, y, (int)circleDiameter, (int)circleDiameter, null);
        } else {
            // Fallback: if the image fails to load, draw the yellow circle
            Ellipse2D.Double sunCircle = new Ellipse2D.Double(x, y, circleDiameter, circleDiameter);
            g2.setColor(color);
            g2.fill(sunCircle);
            g2.setColor(BORDER_COLOR);
            g2.draw(sunCircle);
        }
      
    }
	// BAD: If diameter is used incorrectly or x/y are 0
	public Rectangle getBounds() {
	    // Adding (int) before the variables converts them from double to int
	    return new Rectangle((int)this.x, (int)this.y, (int)this.circleDiameter, (int)this.circleDiameter);
	}
	
	
	private static void loadSpriteOnce() {
		if (triedLoad) return;
		triedLoad = true;

		try {
		
		sprite = ImageIO.read(Player.class.getResource("/ui/coin-sprite-png-4.png"));
		} catch (IOException | IllegalArgumentException ex) {
		sprite = null; 
		}
		}
	
	
}
