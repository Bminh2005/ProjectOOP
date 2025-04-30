package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
	GamePanel gp;
	public int worldX;
	public int worldY;
	public int x;
	public int y;
	public int hp;
	public int mp;
	public int speed;
	public String name;
	public int maxHp;
	public int attack;
	public int defense;
	public int exp;
	public int width;
	public int height;
	public String direction;
	public boolean CollisionOn;
	public BufferedImage image;
	public Rectangle solidArea;
	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	};
	public void update()
	{
		
	}
}
