package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public abstract class Entity {
	GamePanel gp;
	public int worldX;
	public int worldY;
	public int x;
	public int y;
	public int hp;
	public int mp;
	public int speed;
	public BufferedImage image;
	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	};
}
