package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.BackGround;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16 x 16 
	final int scale  = 3;
	public final int tileSize = originalTileSize * scale; //48 x 48
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 9;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	int FPS = 60;
	TileManager tileM = new TileManager(this);
	Player player = new Player(this, keyH);
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
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
				System.out.println("FPS: " + framecount);
				framecount = 0;
				timer = 0;
			}
		}
	}
	public void update() {
		
		//player.update();
		
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		//player.draw(g2);
		g2.dispose();
	}

}
