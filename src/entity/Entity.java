package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	GamePanel gp;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	
	//Position
	public int worldX, worldY;
	public String direction = "down";
	public int x;
	public int y;
	public int width;
	public int height;
	
	//Character attributes
	public int hp;
	public int mp;
	public int speed;
	public String name;
	public int maxHp;
	public int attack;
	public int defense;
	public int exp;
	
	//State
	public boolean alive = true;
	public boolean dying = false;
	public boolean invincible = false;
	public boolean hpBarOn = false;
	public int type;
	public int type_monster = 1;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean CollisionOn = false;
	public BufferedImage image;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	
	//Counter
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	public int shotAvailableCounter = 0;
	public int dyingCounter = 0;
	public int hpBarCounter = 0;
	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	};
	public BufferedImage setup(String imagePath, int width, int height)
	{
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return image;
	}
	public void update()
	{
		
	}
}