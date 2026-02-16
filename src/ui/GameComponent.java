package ui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.Timer;

import model.GameModel;
import java.awt.Point;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class: GameComponent
 * 
 * @author Team 7
 * 
 *         <br>
 *         Purpose: Represents the main visual component of the game. This class
 *         is responsible for rendering the player, enemies, grid, and maze
 *         walls, as well as handling keyboard input and driving the game loop
 *         via a timer.
 */

public class GameComponent extends JComponent {

	private enum GameState { // based on state machine knowledge from ECE160
		PLAYING, WIN, LOSE
	}

	private GameState gameState = GameState.PLAYING;
	private ArrayList<Rectangle> walls = new ArrayList<>();
	private static final int ROWS = 30;
	private static final int COLS = 30;
	private ArrayList<Point> enemyPath1 = new ArrayList<>();
	private ArrayList<Point> enemyPath2 = new ArrayList<>();
	long now = System.currentTimeMillis();
	private final char[][] grid = new char[ROWS][COLS];

	private static Image background;
	private static boolean triedLoadBackground = false;
	private static Image wallSprite;
	private static boolean triedLoadWall = false;

	private JButton restartButton = new JButton("Restart");// initialize start button
	private JLabel endMessage = new JLabel(""); // initialize end message to be changed based on outcome of game

	private Timer timer;
	int playerLives = 3;
	int tile = 20;
	private int start_x = 250;
	private int x = start_x;
	private int y = 20;
	private int step = 10;
	int row = 0;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;

	private long lastHitMs = 0;
	private static final long HIT_COOLDOWN_MS = 1500;

	private int coinCollected = 0;

	private GameModel model;
	private JLabel lives = new JLabel("Lives: " + playerLives);
	private JLabel score = new JLabel("Score: " + coinCollected);
	private JLabel gameOver = new JLabel("");
	// Player player1 = new Player(11*tile, 10*tile, Color.RED);
	Player player1 = new Player(250, 250, Color.RED);

	Enemy enemy1 = new Enemy(0, 0, Color.GREEN);

	private ArrayList<Point> patrolTiles1 = new ArrayList<>();
	private ArrayList<Point> patrolTiles2 = new ArrayList<>();

	private ArrayList<Coin> coins = new ArrayList<>();

	Enemy enemy2 = new Enemy(25 * tile, 25 * tile, Color.GREEN);

	// On all of the boundary wall points, set as rectangles, so that when the
	// player
	// is also set as a rectangle, we can check collisions
	private void buildWalls() {
		walls.clear();

		walls.add(new Rectangle(tile * 3 - 2, tile * 3, 4, tile * 9));
		walls.add(new Rectangle(tile * 6 - 2, tile * 0, 4, tile * 12));
		walls.add(new Rectangle(tile * 3, tile * 15 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 3 - 2, tile * 15, 4, tile * 6));
		walls.add(new Rectangle(tile * 0, tile * 21 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 3, tile * 24 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 6 - 2, tile * 18, 4, tile * 6));
		walls.add(new Rectangle(tile * 6, tile * 18 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 9 - 2, tile * 3, 4, tile * 15));
		walls.add(new Rectangle(tile * 9, tile * 3 - 2, tile * 9, 4));
		walls.add(new Rectangle(tile * 18 - 2, tile * 3, 4, tile * 3));
		walls.add(new Rectangle(tile * 21 - 2, tile * 0, 4, tile * 6));
		walls.add(new Rectangle(tile * 18, tile * 9 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 12, tile * 6 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 15 - 2, tile * 6, 4, tile * 3));
		walls.add(new Rectangle(tile * 15, tile * 9 - 2, tile * 6, 4));
		walls.add(new Rectangle(tile * 12 - 2, tile * 12, 4, tile * 9));
		walls.add(new Rectangle(tile * 15 - 2, tile * 9, 4, tile * 9));
		walls.add(new Rectangle(tile * 9, tile * 21 - 2, tile * 9, 4));
		walls.add(new Rectangle(tile * 18 - 2, tile * 15, 4, tile * 6));
		walls.add(new Rectangle(tile * 18, tile * 15 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 15, tile * 12 - 2, tile * 6, 4));
		walls.add(new Rectangle(tile * 21 - 2, tile * 18, 4, tile * 6));
		walls.add(new Rectangle(tile * 21, tile * 24 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 12, tile * 24 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 12 - 2, tile * 24, 4, tile * 3));
		walls.add(new Rectangle(tile * 9 - 2, tile * 24, 4, tile * 3));
		walls.add(new Rectangle(tile * 15 - 2, tile * 21, 4, tile * 3));
		walls.add(new Rectangle(tile * 24 - 2, tile * 3, 4, tile * 6));
		walls.add(new Rectangle(tile * 21, tile * 9 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 21, tile * 12 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 21, tile * 15 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 24 - 2, tile * 21, 4, tile * 3));
		walls.add(new Rectangle(tile * 15, tile * 12 - 2, tile * 6, 4));
		walls.add(new Rectangle(tile * 21, tile * 15 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 21, tile * 18 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 9, tile * 9 - 2, tile * 6, 4));
		walls.add(new Rectangle(tile * 18, tile * 15 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 24, tile * 21 - 2, tile * 3, 4));
		walls.add(new Rectangle(tile * 18, tile * 24 - 2, tile * 3, 4));

	}

	public GameComponent(GameModel model) {
		this.model = model;
		setFocusable(true);
		setLayout(null);
		// position the text for lives, score, end message, and restart button
		setLayout(null);

		Font statsFont = new Font("Arial", Font.BOLD, 24);

		lives.setBounds(200, 10, 200, 30);
		score.setBounds(200, 40, 200, 30);

		lives.setHorizontalAlignment(JLabel.CENTER);
		score.setHorizontalAlignment(JLabel.CENTER);

		lives.setFont(statsFont);
		score.setFont(statsFont);

		lives.setForeground(Color.BLACK);
		score.setForeground(Color.BLACK)
		endMessage.setBounds(0, 200, 600, 60);
		endMessage.setHorizontalAlignment(JLabel.CENTER);
		endMessage.setFont(new Font("Arial", Font.BOLD, 48));
		endMessage.setForeground(Color.RED);
		endMessage.setVisible(false);

		restartButton.setBounds(240, 300, 120, 40);
		restartButton.setVisible(false);

		restartButton.addActionListener(e -> restartGame());

		add(lives);
		add(score);
		add(endMessage);
		add(restartButton);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (gameState != GameState.PLAYING) return;
//			  switch (e.getKeyCode()) {
//		        case KeyEvent.VK_W -> player1.setVelocity(0, -3);
//		        case KeyEvent.VK_S -> player1.setVelocity(0, 3);
//		        case KeyEvent.VK_A -> player1.setVelocity(-3, 0);
//		        case KeyEvent.VK_D -> player1.setVelocity(3, 0);
				// }
				if (e.getKeyCode() == KeyEvent.VK_W)
					player1.setVelocity(0, -3);
				if (e.getKeyCode() == KeyEvent.VK_S)
					player1.setVelocity(0, 3);
				if (e.getKeyCode() == KeyEvent.VK_A)
					player1.setVelocity(-3, 0);
				if (e.getKeyCode() == KeyEvent.VK_D)
					player1.setVelocity(3, 0);

			}
			// @Override
//		  public void keyReleased(KeyEvent e) {
//		      switch (e.getKeyCode()) {
//		          case KeyEvent.VK_W, KeyEvent.VK_S -> player1.stopY();
//		          case KeyEvent.VK_A, KeyEvent.VK_D -> player1.stopX();
//		      }
//		  }
		});

		loadLevel("LEVEL1.txt");
		// initializeCoins();
		// loadEnemyPath();
		// enemy1.setPath(enemyPath1);
		// enemy2.setPath(enemyPath2);

		timer = new Timer(10, e -> {
			int nextX = player1.getX() + player1.getDx();
			int nextY = player1.getY() + player1.getDy();

			Rectangle nextPos = new Rectangle(nextX, nextY, Player.SIZE, Player.SIZE);

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
			for (int i = 0; i < coins.size(); i++) {
				// 1. Get the specific coin at index i
				Coin currentCoin = coins.get(i);

				// 2. Check if that specific coin's bounds hit the player
				if (currentCoin.getBounds().intersects(player1.getBounds())) {
					coins.remove(i);
					coinCollected++;
					// System.out.println(coinCollected);
					break;
				}

				long now = System.currentTimeMillis();
				if ((player1.getBounds().intersects(enemy1.getBounds())
						|| player1.getBounds().intersects(enemy2.getBounds())) && now - lastHitMs > HIT_COOLDOWN_MS) { // player
																														// only
																														// gets
																														// hit
																														// once
																														// about
																														// 1.50
																														// seconds
																														// to
																														// avoid
																														// freezing

					if (player1.getBounds().intersects(enemy1.getBounds()) // if either enemy intersects(collides)
																			// player rectangles
							|| player1.getBounds().intersects(enemy2.getBounds())) {

						// resets player to center - placeholder

						lives.setText("Lives: " + playerLives);

						lastHitMs = now;
						playerLives--;

					}

					if (playerLives <= 0) {
					    gameState = GameState.LOSE;
					    timer.stop();// sets player back to middle if lose all lives

					    endMessage.setText("YOU LOSE");
					    endMessage.setVisible(true);
					    restartButton.setVisible(true);
					}
					if (coins.size() == 0 && gameState == GameState.PLAYING) {
					    gameState = GameState.WIN;
					    timer.stop();

					    endMessage.setText("YOU WIN!");
					    endMessage.setVisible(true);
					    restartButton.setVisible(true);
					}

				}
				repaint();
			}
		});

		timer.start();
		setFocusable(true);
		requestFocus();
	}

	private void initializeCoins() {
		coins.add(new Coin(78, 135, 25, Color.YELLOW));
		coins.add(new Coin(18, 135, 25, Color.YELLOW));
		coins.add(new Coin(18, 375, 25, Color.YELLOW));
		coins.add(new Coin(78, 325, 25, Color.YELLOW));
		coins.add(new Coin(198, 500, 25, Color.YELLOW));
		coins.add(new Coin(258, 435, 25, Color.YELLOW));
		coins.add(new Coin(138, 135, 25, Color.YELLOW));
		coins.add(new Coin(245, 135, 25, Color.YELLOW));
		coins.add(new Coin(198, 275, 25, Color.YELLOW));
		coins.add(new Coin(258, 275, 25, Color.YELLOW));
		coins.add(new Coin(245, 25, 25, Color.YELLOW));
		coins.add(new Coin(500, 135, 25, Color.YELLOW));
		coins.add(new Coin(435, 135, 25, Color.YELLOW));
		coins.add(new Coin(500, 445, 25, Color.YELLOW));
		coins.add(new Coin(435, 445, 25, Color.YELLOW));
		coins.add(new Coin(265, 500, 25, Color.YELLOW));
		coins.add(new Coin(325, 195, 25, Color.YELLOW));
		coins.add(new Coin(318, 375, 25, Color.YELLOW));
		coins.add(new Coin(258, 195, 25, Color.YELLOW));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		loadBackgroundOnce();
		loadWallSpriteOnce();

		if (background != null) {
			g2.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		} else {
			// fallback so you can tell it's failing
			g2.setColor(Color.DARK_GRAY);
			g2.fillRect(0, 0, getWidth(), getHeight());
		}

		// Minimal placeholder to test it’s running
		g2.setColor(new Color(255, 0, 0, 120)); // translucent red

//	for (Rectangle wall : walls) {
//	    g2.fill(wall);
//	}
		for (Rectangle wall : walls) {

			if (wallSprite != null) {
				g2.drawImage(wallSprite, wall.x, wall.y, wall.width, wall.height, null);
			} else {
				// fallback if image fails
				g2.setColor(Color.GRAY);
				g2.fill(wall);
			}
		}

		player1.drawOn(g2);
		enemy1.drawOn(g2);
		enemy2.drawOn(g2);
		for (Coin coin : coins) {
			coin.drawOn(g2);
		}

		Color currentcolor = Color.red;
		int tile = 20; // size of tile;
		g.setColor(Color.BLACK);

		// Grid lines setup
		// g2.setColor(Color.blue);
		// g2.setStroke(new BasicStroke(2));
		// g2.drawRect(0, 0, 600, 600);

		
		lives.setText("Lives: " + playerLives);
		score.setText("Score: " + coinCollected);
		

		// draw grid lines
//	for (int x = 0; x <= 600; x += tile) {
//	    g2.drawLine(x, 0, x, 600);   // vertical grid lines draws lines across window with varying y components
//	}
//	for (int y = 0; y <= 600; y += tile) {
//	    g2.drawLine(0, y, 600, y);   // horizontal
//	}

		// maze borders setup;
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(4));

		g2.setColor(currentcolor); // Set back to original color
	}
	// TODO: draw based on model state

	private void loadLevel(String filename) {
		walls.clear();
		coins.clear();
		patrolTiles1.clear();
		patrolTiles2.clear();
		// optional: if you want score to reset per level load
		coinCollected = 0;

		int enemyCount = 0;

		// default everything to empty
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				grid[r][c] = '.';
			}
		}

		int r = 0;
		try (Scanner scanner = new Scanner(new File(filename))) {
			while (scanner.hasNextLine() && r < ROWS) {
				String line = scanner.nextLine();

				while (line.length() < COLS)
					line += ".";
				if (line.length() > COLS)
					line = line.substring(0, COLS);

				for (int c = 0; c < COLS; c++) {
					char ch = line.charAt(c);
					grid[r][c] = ch;

					int px = c * tile;
					int py = r * tile;

					switch (ch) {
					case '*': // wall
						walls.add(new Rectangle(px, py, tile, tile));
						break;

					case 'B': // enemy1 patrol tile
						patrolTiles1.add(new Point(c, r));
						break;

					case 'D': // enemy2 patrol tile
						patrolTiles2.add(new Point(c, r));
						break;

					case 'P': // player spawn
						player1.setPosition(px, py);
						break;

					case 'C': // coin spawn (centered in tile)
						coins.add(new Coin(px + tile / 2 - 12, py + tile / 2 - 12, 25, Color.YELLOW));
						break;

					case 'E': {
						int ex = px + tile / 2 - Enemy.SIZE / 2;
						int ey = py + tile / 2 - Enemy.SIZE / 2;

						if (enemyCount == 0)
							enemy1.setPosition(ex, ey);
						else if (enemyCount == 1)
							enemy2.setPosition(ex, ey);

						enemyCount++;
						break;
					}

					default:
						// '.' or anything else
						break;
					}
				}
				r++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		ArrayList<Point> path1 = new ArrayList<>();
		for (Point t : patrolTiles1) {
			int cx = t.x * tile + tile / 2;
			int cy = t.y * tile + tile / 2;
			path1.add(new Point(cx, cy));
		}
		enemy1.setPath(path1);

		ArrayList<Point> path2 = new ArrayList<>();
		for (Point t : patrolTiles2) {
			int cx = t.x * tile + tile / 2;
			int cy = t.y * tile + tile / 2;
			path2.add(new Point(cx, cy));
		}
		enemy2.setPath(path2);

	}

	private static void loadBackgroundOnce() {
		if (triedLoadBackground)
			return;
		triedLoadBackground = true;

		try {
			background = ImageIO.read(GameComponent.class.getResource("/ui/back3.png"));
		} catch (IOException | IllegalArgumentException ex) {
			background = null; // safe fallback
		}
	}

	private static void loadWallSpriteOnce() {
		if (triedLoadWall)
			return;
		triedLoadWall = true;

		try {
			wallSprite = ImageIO.read(GameComponent.class.getResource("/ui/wall.png"));
		} catch (IOException | IllegalArgumentException e) {
			wallSprite = null; // fallback safe
		}
	}
	private void restartGame() {
		playerLives = 3;
		coinCollected = 0;
		gameState = GameState.PLAYING;
		endMessage.setVisible(false);
		restartButton.setVisible(false);
		loadLevel("Level2.txt");
		timer.start();
		
	}

}
