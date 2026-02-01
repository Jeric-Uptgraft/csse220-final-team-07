package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import javax.swing.JComponent;
import javax.swing.Timer;

import model.GameModel;


public class GameComponent extends JComponent {

	int tile = 20;
	private int start_x = 250;
	private int x = start_x;
	private int y = 20;
	private int step = 10;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 200;
	private GameModel model;
	Player player1 = new Player(11*tile, 10*tile, Color.RED);
	
	
	Enemy enemy1 = new Enemy(0, 0, Color.GREEN);
	
	
	Enemy enemy2 = new Enemy(25*tile, 25*tile, Color.GREEN);

	public GameComponent(GameModel model) {
	this.model = model;
	setFocusable(true);

	addKeyListener(new KeyAdapter() {
		  @Override
		  public void keyPressed(KeyEvent e) {
//		    switch (e.getKeyCode()) { //switch case for WASD movement for player. 
//		    case KeyEvent.VK_W -> player1.moveUp();
//		      case KeyEvent.VK_S -> player1.moveDown();
//		      case KeyEvent.VK_A -> player1.moveLeft();
//		      case KeyEvent.VK_D -> player1.moveRight();
		      if (e.getKeyCode() == KeyEvent.VK_W) {
			      player1.moveUp();
			    }
		      if (e.getKeyCode() == KeyEvent.VK_S) {
			      player1.moveDown();
			    }
		      if (e.getKeyCode() == KeyEvent.VK_A) {
			      player1.moveLeft();
			    }
		      if (e.getKeyCode() == KeyEvent.VK_D) {
			      player1.moveRight();
			    }
		    
		  }
		});

	
	
	
	
		
		Timer timer = new Timer(10, e -> {
			player1.update();				  
			repaint();
			});
			timer.start();
			
	}

	


	@Override
	protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;

	// Minimal placeholder to test  it’s running
	
	player1.drawOn(g2);
	enemy1.drawOn(g2);
	enemy2.drawOn(g2);

	Color currentcolor = Color.red;
	int tile = 20; //size of tile;
	
	//Grid lines setup
	g2.setColor(Color.blue);
	g2.setStroke(new BasicStroke(2));
	g2.drawRect(0, 0, 600, 600);
	
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
	
	
	
//	g2.drawLine(0, tile * 2, tile * 10, tile * 2);   //notice pattern for lines
//	g2.drawLine(tile * 10, tile * 2, tile*10,tile*5);
//	g2.drawLine(tile*10,tile*5, tile*15,tile*5);  
	g2.drawLine(tile*3,tile*3, tile*3,tile*12);
	g2.drawLine(tile*6,tile*0, tile*6,tile*12);
	g2.drawLine(tile*3,tile*15, tile*6,tile*15);
	g2.drawLine(tile*3,tile*15, tile*3,tile*21);
	g2.drawLine(tile*0,tile*21, tile*3,tile*21);
	g2.drawLine(tile*3,tile*24, tile*6,tile*24);
	g2.drawLine(tile*6,tile*24, tile*6,tile*18);
	g2.drawLine(tile*6,tile*18, tile*9,tile*18);
	g2.drawLine(tile*9,tile*18, tile*9,tile*3);
	g2.drawLine(tile*9,tile*3, tile*18,tile*3);
	g2.drawLine(tile*18,tile*3, tile*18,tile*6);
	g2.drawLine(tile*21,tile*0, tile*21,tile*6);
	g2.drawLine(tile*21,tile*9, tile*18,tile*9);
	g2.drawLine(tile*15,tile*6, tile*12,tile*6);
	g2.drawLine(tile*15,tile*6, tile*15,tile*9);
	g2.drawLine(tile*15,tile*9, tile*9,tile*9);
	g2.drawLine(tile*12,tile*12, tile*12,tile*21);
	g2.drawLine(tile*15,tile*9, tile*15,tile*18);
	g2.drawLine(tile*9,tile*21, tile*18,tile*21);
	g2.drawLine(tile*18,tile*21, tile*18,tile*15);
	g2.drawLine(tile*18,tile*15, tile*21,tile*15);
	g2.drawLine(tile*21,tile*12, tile*15,tile*12);
	g2.drawLine(tile*21,tile*18, tile*21,tile*24);
	g2.drawLine(tile*21,tile*24, tile*18,tile*24);
//	g2.drawLine(tile*18,tile*24, tile*18,tile*27);
	g2.drawLine(tile*15,tile*24, tile*12,tile*24);
	g2.drawLine(tile*12,tile*24, tile*12,tile*27);
	g2.drawLine(tile*9,tile*27, tile*9,tile*24);
	g2.drawLine(tile*15,tile*24, tile*15,tile*21);
	g2.drawLine(tile*24,tile*3, tile*24,tile*9);
	g2.drawLine(tile*24,tile*9, tile*21,tile*9);
	g2.drawLine(tile*21,tile*12, tile*24,tile*12);
	g2.drawLine(tile*24,tile*15, tile*21,tile*15);
	g2.drawLine(tile*21,tile*18, tile*24,tile*18);
	g2.drawLine(tile*27,tile*21, tile*24,tile*21);
	g2.drawLine(tile*24,tile*21, tile*24,tile*24);

	

	





	
	
	
	 g2.setColor(currentcolor); //Set back to original color
		}
	// TODO: draw based on model state
	

}

