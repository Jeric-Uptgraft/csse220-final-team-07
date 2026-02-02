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
import java.awt.Rectangle;
import java.util.ArrayList;
/**
 * Class: GameComponent
 * 
 * @author Team 7
 * 
 * <br>
 * Purpose:
 * Represents the main visual component of the game. This class is responsible
 * for rendering the player, enemies, grid, and maze walls, as well as handling
 * keyboard input and driving the game loop via a timer.
 */

public class GameComponent extends JComponent {
	private ArrayList<Rectangle> walls = new ArrayList<>();
	private static final int ROWS = 30;
	private static final int COLS = 30;

	private Timer timer;

	int tile = 20;
	private int start_x = 250;
	private int x = start_x;
	private int y = 20;
	private int step = 10;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 200;
	private GameModel model;
	//Player player1 = new Player(11*tile, 10*tile, Color.RED);
	Player player1 = new Player(1 * tile, 1 * tile, Color.RED);

	
	Enemy enemy1 = new Enemy(0, 0, Color.GREEN);
	
	
	Enemy enemy2 = new Enemy(25*tile, 25*tile, Color.GREEN);
	// On all of the boundary wall points, set as rectangles, so that when the player
	// is also set as a rectangle, we can check collisions
	private void buildWalls() {
	    walls.clear();

	    walls.add(new Rectangle(tile*3 - 2, tile*3, 4, tile*9));
	    walls.add(new Rectangle(tile*6 - 2, tile*0, 4, tile*12));
	    walls.add(new Rectangle(tile*3, tile*15 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*3 - 2, tile*15, 4, tile*6));
	    walls.add(new Rectangle(tile*0, tile*21 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*3, tile*24 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*6 - 2, tile*18, 4, tile*6));
	    walls.add(new Rectangle(tile*6, tile*18 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*9 - 2, tile*3, 4, tile*15));
	    walls.add(new Rectangle(tile*9, tile*3 - 2, tile*9, 4));
	    walls.add(new Rectangle(tile*18 - 2, tile*3, 4, tile*3));
	    walls.add(new Rectangle(tile*21 - 2, tile*0, 4, tile*6));
	    walls.add(new Rectangle(tile*18, tile*9 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*12, tile*6 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*15 - 2, tile*6, 4, tile*3));
	    walls.add(new Rectangle(tile*15, tile*9 - 2, tile*6, 4));
	    walls.add(new Rectangle(tile*12 - 2, tile*12, 4, tile*9));
	    walls.add(new Rectangle(tile*15 - 2, tile*9, 4, tile*9));
	    walls.add(new Rectangle(tile*9, tile*21 - 2, tile*9, 4));
	    walls.add(new Rectangle(tile*18 - 2, tile*15, 4, tile*6));
	    walls.add(new Rectangle(tile*18, tile*15 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*15, tile*12 - 2, tile*6, 4));
	    walls.add(new Rectangle(tile*21 - 2, tile*18, 4, tile*6));
	    walls.add(new Rectangle(tile*21, tile*24 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*12, tile*24 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*12 - 2, tile*24, 4, tile*3));
	    walls.add(new Rectangle(tile*9 - 2, tile*24, 4, tile*3));
	    walls.add(new Rectangle(tile*15 - 2, tile*21, 4, tile*3));
	    walls.add(new Rectangle(tile*24 - 2, tile*3, 4, tile*6));
	    walls.add(new Rectangle(tile*21, tile*9 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*21, tile*12 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*21, tile*15 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*24 - 2, tile*21, 4, tile*3));
	    walls.add(new Rectangle(tile*15, tile*12 - 2, tile*6, 4));
	    walls.add(new Rectangle(tile*21, tile*15 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*21, tile*18 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*9,  tile*9  - 2, tile*6, 4));
	    walls.add(new Rectangle(tile*18, tile*15 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile*24, tile*21 - 2, tile*3, 4));
	    walls.add(new Rectangle(tile * 18, tile * 24 - 2, tile * 3, 4));

	}
	public GameComponent(GameModel model) {
	this.model = model;
	setFocusable(true);
	
	addKeyListener(new KeyAdapter() {
		  @Override
		  public void keyPressed(KeyEvent e) {

			  switch (e.getKeyCode()) {
		        case KeyEvent.VK_W -> player1.setVelocity(0, -3);
		        case KeyEvent.VK_S -> player1.setVelocity(0, 3);
		        case KeyEvent.VK_A -> player1.setVelocity(-3, 0);
		        case KeyEvent.VK_D -> player1.setVelocity(3, 0);
		    }
		    
		  }
		  @Override
		  public void keyReleased(KeyEvent e) {
		      switch (e.getKeyCode()) {
		          case KeyEvent.VK_W, KeyEvent.VK_S -> player1.stopY();
		          case KeyEvent.VK_A, KeyEvent.VK_D -> player1.stopX();
		      }
		  }
		});

	
	
		buildWalls();
		
		timer = new Timer(10, e -> {
		    int nextX = player1.getX() + player1.getDx();
		    int nextY = player1.getY() + player1.getDy();

		    Rectangle nextPos = new Rectangle(
		        nextX,
		        nextY,
		        Player.SIZE,
		        Player.SIZE
		    );

		    boolean blocked = false;
		    for (Rectangle wall : walls) {
		        if (nextPos.intersects(wall)) {
		            blocked = true;
		            break;
		        }
		    }

		    if (!blocked) {
		        player1.setPosition(nextX, nextY);
		    }

		    enemy1.ai();
		    enemy2.ai();
		    player1.update();
		    repaint();
		});

	    timer.start();
	    setFocusable(true);
	    requestFocus();	
	}

	


	@Override
	protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;

	// Minimal placeholder to test  it’s running
	g2.setColor(new Color(255, 0, 0, 120)); // translucent red
	for (Rectangle wall : walls) {
	    g2.fill(wall);
	}
	player1.drawOn(g2);
	enemy1.drawOn(g2);
	enemy2.drawOn(g2);

	Color currentcolor = Color.red;
	int tile = 20; //size of tile;
	g.setColor(Color.BLACK);
	
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
	
	
	
//1	g2.drawLine(0, tile * 2, tile * 10, tile * 2);   //notice pattern for lines
//1	g2.drawLine(tile * 10, tile * 2, tile*10,tile*5);
//1	g2.drawLine(tile*10,tile*5, tile*15,tile*5);  
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
//1	g2.drawLine(tile*18,tile*24, tile*18,tile*27);
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

