package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import map.Map;
import map.TempMap;

public class GamePanel extends JPanel implements Runnable{
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16 x 16 
	final int scale  = 3;
	public final int tileSize = originalTileSize * scale; //48 x 48
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 9;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	public Map currentMap;
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	TempMap map  = new TempMap(Color.DARK_GRAY, this);
	private final Map testmap = new Map(this, "/map/layer0.txt", "/map/layer1.txt");
	int FPS = 60;
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int maxWorldHeight = maxWorldRow * tileSize;
	public final int maxWorldWidth = maxWorldRow * tileSize;
	
	public Player player = new Player(this, keyH);
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		currentMap = testmap;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		int framecount = 0;
		long timer = 0;
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/drawInterval;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			if(delta >= 1) {
				update();
				this.repaint();
				
				delta -=1;
				framecount++;
			}
			if(timer >= 1000000000) {
				//System.out.println("FPS: " + framecount);
				framecount = 0;
				timer = 0;
			}
		}
	}
	public void update() {
		
		player.update();
		
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		testmap.draw(g2, 1);
		player.draw(g2);
		testmap.draw(g2, 2);
		g2.dispose();
	}

}
