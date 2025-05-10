package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class MapTile {
	private BufferedImage img;
	private GamePanel gp;
	private int height;
	private int width;
	public Rectangle collisionArea;
	public boolean collision; // Nếu true, không thể đi qua
	
	public MapTile(GamePanel gp) {
		this.gp = gp;
		img = null;
		height = gp.tileSize;
		width = gp.tileSize;
		collision = false;
		collisionArea = new Rectangle();
	}
	public MapTile(GamePanel gp, String path) {
		this.gp = gp;
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.height = gp.tileSize;
		this.width  = gp.tileSize;
		this.collisionArea = new Rectangle();
		this.collision = false;
	}
	public MapTile(GamePanel gp, String path, int width, int height) {
		this.gp = gp;
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.collisionArea = new Rectangle();
		this.width  = gp.tileSize + gp.tileSize/2;
		this.height = gp.tileSize*height/width;
		
		this.collision = false;
	}
	public MapTile(GamePanel gp, String path, boolean collision) {
		this.gp = gp;
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.height = gp.tileSize;
		this.width  = gp.tileSize;
		if(collision) {
			this.collision = true;
			this.collisionArea = new Rectangle(0, 0, this.width, this.height);
		}
	}

	public MapTile(GamePanel gp, String path, int width, int height, boolean collision) {
		this.gp = gp;
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.width = gp.tileSize + gp.tileSize/2;
		this.height = this.width * height / width;
		if(collision) {
			this.collision = true;
			this.collisionArea = new Rectangle(0, 0, this.width, this.height);
		}
	}
	
	public MapTile(GamePanel gp, String path, int width, int height, boolean collision, int x, int y, int cwidth, int cheight) {
		this.gp = gp;
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.width = gp.tileSize + gp.tileSize/2;
		this.height = this.width * height / width;
		if(collision) {
			this.collision = true;
			this.collisionArea = new Rectangle(x, y, cwidth, cheight);
		}
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void draw(Graphics2D g2, int x, int y) {
		g2.drawImage(img, x, y, this.width, this.height, null);
		if(collision) {
			g2.setColor(Color.RED);
			g2.drawRect(x, y, gp.tileSize, gp.tileSize);
		}
		// Cập nhật vị trí vùng va chạm theo vị trí hiện tại
		//collisionArea.x = x;
		//collisionArea.y = y;
	}
}
