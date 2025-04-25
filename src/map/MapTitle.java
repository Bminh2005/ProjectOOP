package map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class MapTitle {
	private BufferedImage img;
	private int height;
	private int width;
	public MapTitle(GamePanel gp, String path) {
		try {
			img = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.height = gp.tileSize;
		this.width  = gp.tileSize;
	}
	public MapTitle(GamePanel gp, String path, int height, int width) {
		try {
			img = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.width = gp.tileSize;
		this.height = gp.tileSize*height/width;
	}
	
	public void draw(Graphics2D g2, int x, int y) {
		g2.drawImage(img, x, y, width, height, null);
	}
}
