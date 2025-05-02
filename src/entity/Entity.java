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
	public BufferedImage image, image2, image3;
	public String description = "";
	
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
	public int maxMp;
	public int attack;
	public int defense;
	public int exp;
	public int level;
	public int nextLevel;
	public int value;
	public int coin;
	public Entity currentWeapon;
	public Entity currentShield;
	
	//State
	public boolean alive = true;
	public boolean dying = false;
	public boolean invincible = false;
	public boolean hpBarOn = false;
	public boolean CollisionOn = false;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	
	//Counter
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	public int shotAvailableCounter = 0;
	public int dyingCounter = 0;
	public int hpBarCounter = 0;
	
	//TYPE
	public int type; // 0 = player, 1 = npc, 2 = monster
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_sword = 3;
	public final int type_axe = 4;
	public final int type_shield = 5;
	public final int type_consumable = 6;
	public final int type_pickUpOnly = 7;
	
	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	};
	public void update()
	{
		
	}
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
}