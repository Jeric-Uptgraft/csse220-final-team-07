package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

import model.GameModel;

public class GameComponent extends JComponent {

	
	
	private GameModel model;


	public GameComponent(GameModel model) {
	this.model = model;
	
	}


	@Override
	protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;

	// Minimal placeholder to test  it’s running
	

	
	Color currentcolor = Color.red;
	
	//Grid lines setup
	g2.setColor(Color.blue);
	g2.setStroke(new BasicStroke(2));
	g2.drawRect(0, 0, 600, 600);
	int tile = 20; //size of tile;

	// draw grid lines
	for (int x = 0; x <= 600; x += tile) {
	    g2.drawLine(x, 0, x, 600);   // vertical grid lines draws lines across window with varying y components
	}
	for (int y = 0; y <= 600; y += tile) {
	    g2.drawLine(0, y, 600, y);   // horizontal
	}
	
	//maze borders setup;
	g2.setColor(Color.black);
	g2.setStroke(new BasicStroke(4));
	
	
	
	g2.drawLine(0, tile * 2, tile * 10, tile * 2);   //notice pattern for lines
	g2.drawLine(tile * 10, tile * 2, tile*10,tile*5);
	g2.drawLine(tile*10,tile*5, tile*15,tile*5);  
	 g2.setColor(currentcolor); //Set back to original color
		}
	// TODO: draw based on model state
	}

