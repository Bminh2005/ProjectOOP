package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import map.Map;
import map.TempMap;
import monster.Monster;
import monster.ChuDongTanCong;
import monster.MonsterCube;
import map.Teleport;
import java.util.*;
public class GamePanel extends JPanel implements Runnable{
	// SCREEN SETTINGS
	private ProcessFrontBehindEntity processor;
	final int originalTileSize = 16; // 16 x 16 
	final int scale  = 3;
	public final int tileSize = originalTileSize * scale; //48 x 48
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 9;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	public ArrayList<Teleport> teleportList = new ArrayList<>();
	public int num_CurrentMap = 1;
	public int maxMap = 3;
	public Map currentMap;
	public Map[] maps = new Map[maxMap];
	public Monster monster[] = new Monster[20];
	public KeyHandler keyH = new KeyHandler(this);
	Thread gameThread;
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public CollisionChecker cChecker;
	public ChuDongTanCong QuaiVatTanCong;
	MonsterCube cube;
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionsState = 5;
	public final int gameOverState = 6;
	
	private final Map MAP01 = new Map(this, "/map/layer0.txt", "/map/layer1.txt");
	private final Map MAP02 = new Map(this, "/map/layer2.txt", "/map/layer1.txt");
	int FPS = 60;
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int maxWorldHeight = maxWorldRow * tileSize;
	public final int maxWorldWidth = maxWorldRow * tileSize;
	//public ArrayList<Monster> monsters = new ArrayList<>(); 
	public Player player = new Player(this, keyH);
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		this.cChecker = new CollisionChecker(this);
		this.QuaiVatTanCong = new ChuDongTanCong(this);
		this.cube = new MonsterCube(this);
		maps[1] = MAP01;
		maps[2]= MAP02;
		currentMap = maps[1];
		teleportList.add(new Teleport(
			    1, 2, 40,   // từ Map 1 tại tile (4,42)
			    2, 30, 9    // sang Map 2 tile (30,9)
			));
		teleportList.add(new Teleport(
			    2, 30, 10,  // từ Map 2 tile (30,10)
			    1, 3, 40    // sang Map 1 tile (42,2)
			));
		processor = new ProcessFrontBehindEntity (currentMap.getLayer2(), player);
		setupGame();
	}
	
	public void setupGame()
	{
//		aSetter.setObject();
//		aSetter.setNPC();
		aSetter.setMonster();
//		aSetter.setInteractiveTile();
//		playMusic(0);
//		stopMusic();
//		gameState = titleState;
//		
//		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
//		g2 = (Graphics2D)tempScreen.getGraphics();
//		
//		if(fullScreenOn == true)
//		{
//			setFullScreen();
//		}
		
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
		if(gameState == playState)
		{
			player.update();
			for(int i = 0; i < monster.length; i++)
			{
				if(monster[i] != null)
				{
					monster[i].update();
				}
			}
			cube.update();
			//System.out.println(player.worldX +" "+ player.worldY);
			int playerCol = player.worldX/tileSize;
			int playerRow = player.worldY/tileSize;
			//System.out.println(playerCol +" "+ playerRow);
			if(playerCol == 4 && playerRow ==42) {
				System.out.println("True!");
			}
			processor = new ProcessFrontBehindEntity (currentMap.getLayer2(), player);
			for (Teleport tp : teleportList) {
		        if (num_CurrentMap == tp.fromMap &&
		            playerCol == tp.fromCol &&
		            playerRow == tp.fromRow) {
		            
		            Teleport(tp.toMap, tp.toCol, tp.toRow);
		            break;
		        }
			}
		}
		if(gameState == pauseState)
		{
			//nothing
		}
		

	}
	public void Teleport(int targetmap, int col, int row ) {
		num_CurrentMap = targetmap;
		currentMap = maps[num_CurrentMap];
		player.worldX = col * tileSize;
		player.worldY = row * tileSize;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		currentMap.draw(g2, 1);
		processor.draw(this, g2);
		//currentMap.draw(g2, 2);
		//player.draw(g2);
		if(player.hp > 0) {
			cube.draw(g2);
		}
		for(int i = 0; i < monster.length; i++)
		{
			if(monster[i] != null)
			{
				monster[i].draw(g2);
			}
		}
		//UI
		ui.draw(g2);
		
		g2.dispose();
	}

}