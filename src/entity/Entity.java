package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	// === Game reference ===
	// Tham chieu toi GamePanel
	GamePanel gp;

	// === Image & Sprite ===
	// Anh sprite cho nhan vat va vat pham
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage titleImage;
	public BufferedImage image, image2, image3;
<<<<<<< HEAD
	
	// === Position & Size ===
	// Vi tri trong the gioi va kich thuoc hien thi
	public int worldX, worldY;
	public int x, y;
	public int width, height;
	public String direction = "down";

	// === Character Stats ===
	// Thuoc tinh nhan vat: mau, mana, cong, thu, kinh nghiem, cap do
	public String name;
	public int level;
	public int nextLevel;
	public int exp;
	public int nextLevelExp;

	public int hp, maxHp;
	public int mp, maxMp;

	public int attack;
	public int attackValue;
	public int defense;
	public int defenseValue;
	public int strength;
	public int dexterity;

	public int speed;
	public int value;
	public int coin;
	public int useCost;

	// === Equipment & Items ===
	// Vu khi, la chan va du an (dan, lua, etc.)
	public Entity currentWeapon;
	public Entity currentShield;
	public Projectile projectile;

	// === Entity State ===
	// Cac trang thai hoat dong cua nhan vat
	public boolean alive = true;
	public boolean dying = false;
	public boolean invincible = false;
	public boolean hpBarOn = false;
	public boolean collision = false;
	public boolean CollisionOn = false;

	// === Hitbox (Collision) ===
	// Vung va cham cua nhan vat
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;

	// === Counters & Timers ===
	// Dem thoi gian cho hoat anh, trang thai, cooldown
	public int spriteCounter = 0;
	public int spriteNum = 1;

	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	public int shotAvailableCounter = 0;
	public int dyingCounter = 0;
	public int hpBarCounter = 0;

	// === Entity Types ===
	// Phan loai doi tuong (nguoi choi, quai, vu khi, vat pham,...)
	public int type;
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_sword = 3;
	public final int type_axe = 4;
	public final int type_shield = 5;
	public final int type_consumable = 6;
	public final int type_pickUpOnly = 7;

	// === Misc ===
	// Khac: mo ta vat pham
	public String description = "";

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

=======

	// === Position & Size ===
	// Vi tri trong the gioi va kich thuoc hien thi
	public int worldX, worldY;
	public int x, y;
	public int width, height;
	public String direction = "down";

	// === Character Stats ===
	// Thuoc tinh nhan vat: mau, mana, cong, thu, kinh nghiem, cap do
	public String name;
	public int level;
	public int nextLevel;
	public int exp;
	public int nextLevelExp;

	public int hp, maxHp;
	public int mp, maxMp;

	public int attack;
	public int attackValue;
	public int defense;
	public int defenseValue;
	public int strength;
	public int dexterity;

	public int speed;
	public int value;
	public int coin;
	public int useCost;

	// === Equipment & Items ===
	// Vu khi, la chan va du an (dan, lua, etc.)
	public Entity currentWeapon;
	public Entity currentShield;
	public Projectile projectile;

	// === Entity State ===
	// Cac trang thai hoat dong cua nhan vat
	public boolean alive = true;
	public boolean dying = false;
	public boolean invincible = false;
	public boolean hpBarOn = false;
	public boolean collision = false;
	public boolean CollisionOn = false;

	// === Hitbox (Collision) ===
	// Vung va cham cua nhan vat
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;

	// === Counters & Timers ===
	// Dem thoi gian cho hoat anh, trang thai, cooldown
	public int spriteCounter = 0;
	public int spriteNum = 1;

	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	public int shotAvailableCounter = 0;
	public int dyingCounter = 0;
	public int hpBarCounter = 0;

	// === Entity Types ===
	// Phan loai doi tuong (nguoi choi, quai, vu khi, vat pham,...)
	public int type;
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_sword = 3;
	public final int type_axe = 4;
	public final int type_shield = 5;
	public final int type_consumable = 6;
	public final int type_pickUpOnly = 7;

	// === Misc ===
	// Khac: mo ta vat pham
	public String description = "";

	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public void use(Entity entity)
	{
		
	}
>>>>>>> branch 'main' of https://github.com/Bminh2005/ProjectOOP.git
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.x;
		int screenY = worldY - gp.player.worldY + gp.player.y;
		g2.drawImage(image, screenX, screenY, null);
	};

	public void update() {

	}

	public BufferedImage setup(String imagePath, int width, int height) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}