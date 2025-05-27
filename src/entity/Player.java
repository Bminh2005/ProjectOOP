package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import main.SpriteSheet;
import monster.Monster;
import object.OBJ_Fireball;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class Player extends Character {
	KeyHandler keyH;
	int speed;
	public String state;
	public ArrayList<Item> inventory = new ArrayList<>();
	
	// PLAYER'S SAITAMA
	public float saitama;
	public final float MAX_SAITAMA = 100f;
	public final float MIN_SAITAMA_TO_RUN = 50f;
	private final float SAITAMA_DECREASE_RATE = 30f;
	private final float SAITAMA_RECOVER_RATE = 10f;
	public boolean tired;

	private final int DEFAULT_SPEED = 3;
	private final int TIRED_SPEED = 2;
	private final int RUN_SPEED = 5;

	// PLAYER'S IMAGE
	private SpriteSheet playerIdle;
	private SpriteSheet playerWalk;
	public SpriteSheet playerAttack[];
	private SpriteSheet playerHurt;
	private SpriteSheet playerRun;
	private SpriteSheet playerDefend;
	private SpriteSheet playerDying;

	// PROCESS FRAMES
	private int spriteNum;
	private int frameCounter;
	private boolean flip;
	private int attackType;
	private int comboAttackDelayTime;// don vi frames
	private boolean runningCountAttackDelay;
	private boolean hurting = false;
	public int maxInventorySize = 20;
	
	public Item currentWeapon;
	public Item currentShield;
	public Projectile projectile;
	
	
	public int level;
	public int nextLevel;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public int defaultScreenX;
	public int defaultScreenY;
	

	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		// SOLID AREA
		solidArea = new Rectangle();
		solidArea.x = 21; // = 17*gp.tileSize/40
		solidArea.y = 26; // = 22*gp.tileSize/40
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 22; // = 21*gp.tileSize/40
		solidArea.height = 22; //= 18*gp.tileSize/40
		//(31, 14, 22, 26) FIX 40 --> 48 (37, 17, 30, 32)
		attackZone = new Rectangle(37, 17, 40, 40);
		attackZoneDefaultX = attackZone.x;
		attackZoneDefaultY = attackZone.y;
		setDefaultValues();
		getPlayerImage();
		currentWeapon = new OBJ_Sword_Normal(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		System.out.println(this.state);
	}

	public void setDefaultValues() {

		// === Status & Stats ===
		// Trang thai va chi so co ban
		tired = false;
		saitama = MAX_SAITAMA;

		maxHp = 20;
		hp = this.maxHp;

		maxMp = 10;
		mp = this.maxMp;

		attack = 10;
		defense = 5;

		strength = 1;
		dexterity = 1;

		speed = 3;
		level = 1;
		state = "NORMAL";
		attackType = 0;

		exp = 0;
		nextLevelExp = 5;
		coin = 0;
		nextLevel = level + 1;

		// === Projectile ===
		// Dan ban hoac lua
		projectile = new OBJ_Fireball(gp);

		// === World Position ===
		// Vi tri trong the gioi game
		worldX = 24 * gp.tileSize;
		worldY = 24 * gp.tileSize;

		// === Screen Position & Animation ===
		// Vi tri hien thi tren man hinh va hoat anh
		x = (gp.screenWidth / 2) - (gp.tileSize / 2);
		y = (gp.screenHeight / 2) - (gp.tileSize / 2);
		defaultScreenX = x;
		defaultScreenY = y;
		
		frameCounter = 0;
		flip = false;

		// === Combat Animation ===
		// Cac sprite tan cong va dem thoi gian combo
		playerAttack = new SpriteSheet[3];
		comboAttackDelayTime = 0;
		runningCountAttackDelay = false;

		// === Size & Collision ===
		// Kich thuoc va vung va cham
		height = gp.tileSize;
		width = gp.tileSize * 53 / 40;

		//solidArea = new Rectangle(17, 23, 32, 32);
		CollisionOn = false;

		// === Initial Direction ===
		// Huong di chuyen ban dau
		direction = "up";
	}

	public void getPlayerImage() {
		playerIdle = new SpriteSheet("/Player/IDLE.png", 672, 84, 7, 21, 23, 53, 40);
		playerWalk = new SpriteSheet("/Player/WALK.png", 768, 84, 8, 21, 23, 53, 40);
		playerAttack[0] = new SpriteSheet("/Player/ATTACK1.png", 576, 84, 6, 21, 23, 53, 40);
		playerAttack[1] = new SpriteSheet("/Player/ATTACK2.png", 480, 84, 5, 21, 23, 53, 40);
		playerAttack[2] = new SpriteSheet("/Player/ATTACK3.png", 576, 84, 6, 21, 23, 53, 40);
		playerHurt = new SpriteSheet("/Player/HURT.png", 384, 84, 4, 21, 23, 53, 40);
		playerRun = new SpriteSheet("/Player/RUN.png", 768, 84, 8, 21, 23, 53, 40);
		playerDefend = new SpriteSheet("/Player/DEFEND.png", 576, 84, 6, 21, 23, 53, 40); // 0->1 gio khien, 2->5 do don
		playerDying = new SpriteSheet("/Player/DEATH.png", 1152, 84, 12, 21, 23, 53, 40);
		titleImage = playerIdle.getSpriteNum(0);
	}

	public int getAttack() {
//		attackArea = currentWeapon.attackArea;
		return attack = attack + strength * currentWeapon.attackValue;
	}

	public int getDefense() {
		return defense = defense + dexterity * currentShield.defenseValue;
	}

	public void update() {
		refreshStatus();
		// System.out.println("SAITAMA = " + this.saitama);
		// System.out.println("SPEED = " + this.speed);
		
		boolean overx = this.worldX >= gp.maxWorldWidth - (gp.screenWidth + this.width) * 10 / 20
				|| this.worldX <= (gp.screenWidth - this.width) * 10 / 20;
		boolean overy = this.worldY >= gp.maxWorldHeight - (gp.screenHeight + this.height) * 10 / 20
				|| this.worldY <= (gp.screenHeight - this.height) * 10 / 20;
		int dx = this.worldX;
		int dy = this.worldY;
		gp.cChecker.checkTile(this);
		if (this.runningCountAttackDelay) {
			this.comboAttackDelayTime++;
			if (this.comboAttackDelayTime == 20) {
				this.attackType = 0;
			}
		}
		
		if (hp > 0) {
			if (this.invincible) {
				this.invincibleCounter++;
				if (this.invincibleCounter >= 60) {
					this.invincible = false;
					this.state = "IDLE";
					this.invincibleCounter = 0;
				}
			}
			if (hurting) {
				this.hurt();
			}
			
			else {
			if (this.state.equals("RUN") == false) {
				if (this.saitama < this.MAX_SAITAMA)
					this.saitama += this.SAITAMA_RECOVER_RATE * 1f / 60f;
			}
			if (this.saitama >= this.MIN_SAITAMA_TO_RUN)
				this.tired = false;
			if (this.keyH.attackPressed == true) {
					this.runningCountAttackDelay = false;
					this.comboAttackDelayTime = 0;
					this.attack();
					System.out.println(this.spriteNum);
					if (this.state.equals("ATTACKING") && this.spriteNum == 3 && this.frameCounter % 4 == 0) {
						this.checkAttackonMonster();
					}

					if (this.spriteNum == this.playerAttack[attackType].maxNumber - 1) {
						this.keyH.attackPressed = false;
						this.attackType = (this.attackType + 1) % 3;
						this.runningCountAttackDelay = true;
						this.idle();
					}
				}
			else if (this.keyH.upPressed == true || this.keyH.downPressed == true || this.keyH.leftPressed == true
					|| this.keyH.rightPressed == true) {
				
				if (this.saitama <= 0)
					this.tired = true;
				if (this.keyH.runPressed && this.tired == false) {
					this.run();
				} else {
					
					this.walk();
				}
				this.CollisionOn = false;
				gp.cChecker.checkEntity(this, gp.monster[gp.num_CurrentMap]);
				if(CollisionOn == false)
					this.move();
				int objIndex = gp.cChecker.checkObject(this, true);
				this.pickUpObject(objIndex);
				
				
			} 
			else this.idle();
		}
		} else {
			this.dying();
		}
		
		if (gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30
				&& projectile.haveResource(this) == true) {
			// SET DEFAULT COORDINATES, DIRECTION AND USER
			projectile.set(worldX, worldY + 10, direction, true, this);

			// SUBTRACT THE COST (MANA, AMMO ETC.)
			projectile.subtractResource(this);

			// ADD IT TO THE LIST
			gp.projectileList.add(projectile);

			shotAvailableCounter = 0;

//			gp.playSE(10);
		}
		if (shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}
		dx = this.worldX - dx;
		dy = this.worldY - dy;
		if(overx) this.x += dx;
		if(overy) this.y += dy;
		this.frameCounter++;
		refreshStatus();
	}

	public void idle() {
		if (this.state.equals("IDLE") == false) {
			this.spriteNum = -1;
			this.frameCounter = 0;
			this.state = "IDLE";
		}
		if (this.frameCounter % 5 == 0) {
			this.spriteNum = (this.spriteNum + 1) % this.playerIdle.maxNumber;
		}

	}

	public void run() {
		if (this.state.equals("RUN") == false) {
			this.spriteNum = this.playerRun.maxNumber - 1;
			this.frameCounter = 0;
			this.speed = this.RUN_SPEED;
		}
		System.out.println("Player is running!");
		this.state = "RUN";
		if (this.saitama > 0) {
			this.saitama -= this.SAITAMA_DECREASE_RATE * 1f / 60f;
		} else {
			this.saitama = 0;
		}

		if (this.frameCounter % 5 == 0) {
			this.spriteNum = (this.spriteNum + 1) % this.playerRun.maxNumber;
		}

	}

	public void walk() {
		if (this.state.equals("WALK") == false) {
			this.spriteNum = this.playerWalk.maxNumber - 1;
			this.frameCounter = 0;
			this.speed = this.DEFAULT_SPEED;
		}
		if (this.tired) {
			this.speed = this.TIRED_SPEED;
		} else
			this.speed = this.DEFAULT_SPEED;
		// System.out.println("Player is walking!");
		this.state = "WALK";
		if (this.frameCounter % 5 == 0) {
			this.spriteNum = (this.spriteNum + 1) % this.playerWalk.maxNumber;
		}

	}

	public void move() {
		if (this.keyH.leftPressed == true) {
			direction = "left";
			
				this.worldX -= this.speed;
				this.flip = true;

		}
		else if (this.keyH.rightPressed == true) {
			direction = "right";
				this.worldX += this.speed;
				this.flip = false;
				
		}
		else if (this.keyH.upPressed == true) {
			direction = "up";
				this.worldY -= this.speed;
		}
		else if (this.keyH.downPressed == true) {
			 direction = "down";
				
				this.worldY += this.speed;
		}
		
	}


	// System.out.println(this.worldX +" "+ this.worldY);

	public void attack() {
		if (this.state.equals("ATTACKING") == false) {
			this.spriteNum = -1;
			this.frameCounter = 0;
		}
		// System.out.println("Player is attacking!");
		this.state = "ATTACKING";
		if (this.frameCounter % 20 == 0) {
			gp.playSE(4);
			if (flip)
				this.worldX -= 3;
			else
				this.worldX += 3;
		}
		if (this.frameCounter % 5 == 0) {
			// System.out.println(this.spriteNum);
			this.spriteNum++;
			if(this.spriteNum == this.playerAttack[attackType].maxNumber) {
				this.spriteNum = 0;
			}
		}
	}

	public void hurt() {
		System.out.println("Player is injured!");
		if(this.state.equals("HURT") == false) {
			this.frameCounter = 0;
			this.spriteNum = -1;
			this.state = "HURT";
		}
		if (this.frameCounter % 5 == 0) {
			this.spriteNum++;	
		}
		System.out.println("-----------------------------" + spriteNum);
		if (this.spriteNum == this.playerHurt.maxNumber) {
			this.spriteNum = 0;
			hurting = false;
			this.state = "IDLE";		
		}
		if (this.flip)
			this.worldX += 1;
		else
			this.worldX -= 1;
		System.out.println("HP = " + this.hp);
		
	}

	public void dying() {
		if (this.state.equals("DYING") == false) {
			this.spriteNum = -1;
			this.frameCounter = 0;
			this.state = "DYING";
		}
		if (this.frameCounter % 10 == 0) {
			this.spriteNum++;
			if(this.spriteNum == this.playerDying.maxNumber - 1) {
				gp.gameState = gp.gameOverState;
			}
		}
		System.out.println("----------------------------"+ this.spriteNum);
		

	}

	public void damageMonsterByProjectile(int i, int attack) {
		if (i != 999) {

			if (gp.monster[gp.num_CurrentMap][i].invincible == false) {
//				gp.playSE(5);

				int damage = attack - gp.monster[gp.num_CurrentMap][i].defense;
				if (damage < 0) {
					damage = 0;
				}
				gp.monster[gp.num_CurrentMap][i].hp -= damage;
				gp.ui.addMessage(damage + " damage!");

				gp.monster[gp.num_CurrentMap][i].invincible = true;
				gp.monster[gp.num_CurrentMap][i].damageReaction();

				if (gp.monster[gp.num_CurrentMap][i].hp <= 0) {
					gp.monster[gp.num_CurrentMap][i].dying = true;
					gp.ui.addMessage("Killed the " + gp.monster[gp.num_CurrentMap][i].name + "!");
					gp.ui.addMessage("Exp + " + gp.monster[gp.num_CurrentMap][i].exp);
					exp += gp.monster[gp.num_CurrentMap][i].exp;
					checkLevelUp();
				}
			}
		}
	}

	public void contactMonster(int i) {
		if (i != 999) {
			if (invincible == false && gp.monster[gp.num_CurrentMap][i].dying == false) {
//				gp.playSE(6);

				int damage = gp.monster[gp.num_CurrentMap][i].attack - defense;
				if (damage < 0) {
					damage = 0;
				}
				hp -= damage;
				invincible = true;
			}

		}
	}

	public void checkAttackonMonster() {
		int range = this.attackZone.width + 2*this.attackZoneDefaultX - this.width;
		if (this.state.equals("ATTACKING")) {
			this.attackZone.x = this.worldX + this.attackZoneDefaultX;
			this.attackZone.y = this.worldY + this.attackZoneDefaultY;
			if (flip) {
				attackZone.x -= range;
			}
			System.out.println(attackZone.x + " " + attackZone.y + " " + attackZone.width + " " + attackZone.height);
			for (int i = 0; i < gp.monster[gp.num_CurrentMap].length; i++) {
				Monster m = gp.monster[gp.num_CurrentMap][i];
				if (m != null && m.hp > 0) {
					Rectangle monsterArea = new Rectangle(m.worldX + m.solidArea.x, m.worldY + m.solidArea.y,
							m.solidArea.width, m.solidArea.height);
					System.out.println(monsterArea.x + " " + monsterArea.y);
					// System.out.println(monsterArea.x +" " + monsterArea.y+ " "+ monsterArea.width
					// +" "+ monsterArea.height);
					if (attackZone.intersects(monsterArea)) {
						m.takeDamage(this.attack);
						gp.monster[gp.num_CurrentMap][i].damageReaction();
						if (gp.monster[gp.num_CurrentMap][i].hp <= 0) {
							gp.monster[gp.num_CurrentMap][i].dying = true;
							gp.ui.addMessage("Killed the " + gp.monster[gp.num_CurrentMap][i].name + "!");
							gp.ui.addMessage("Exp + " + gp.monster[gp.num_CurrentMap][i].exp);
							exp += gp.monster[gp.num_CurrentMap][i].exp;
							checkLevelUp();
						}
					}
				}
			}
			this.attackZone.x = this.attackZoneDefaultX;
			this.attackZone.y = this.attackZoneDefaultY;
		}
	}
	
	public void takeDamge(int damage) {
		if(damage - defense > 0) {
			this.hp -= (damage - defense);
		}
		this.hurting = true;
		
		
	}
	public void setDefaultPositions() {
		worldX = gp.tileSize * 24;
		worldY = gp.tileSize * 24;
		direction = "down";
	}

	public void restoreLifeAndMana() {
		hp = maxHp;
		mp = maxMp;
		invincible = false;
	}

	public void setItems() {
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new OBJ_Potion_Red(gp));
		inventory.add(new OBJ_Potion_Red(gp));
		inventory.add(new OBJ_Shield_Blue(gp));
	}

	public void selectItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();

		if (itemIndex < inventory.size()) {
			Item selectedItem = inventory.get(itemIndex);

			if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
				currentWeapon = selectedItem;
				attack = getAttack();
//				getPlayerAttackImage();
			}
			if (selectedItem.type == type_shield) {
				currentShield = selectedItem;
				defense = getDefense();
			}
			if (selectedItem.type == type_consumable) {
				selectedItem.use(this);
				inventory.remove(itemIndex);
			}
		}
	}

	public void checkLevelUp() {
		if (exp >= nextLevelExp) {
			level++;
			nextLevelExp = nextLevelExp * 2;
			maxHp += 4;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();

//			gp.playSE(8);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You are level " + level + " now!\n" + "You feel stronger!";
		}
	}
	public void refreshStatus() {
		if(gp.keyH.refreshPressed == true) {
			setDefaultValues();
		if(keyH.refreshPressed == true) {
			restoreLifeAndMana();
		}
	}
	}
	public void draw(Graphics2D g2) {
		switch (state) {
		case "IDLE":
			this.image = this.playerIdle.animation[this.spriteNum];
			break;
		case "WALK":
			this.image = this.playerWalk.animation[this.spriteNum];
			break;
		case "ATTACKING":
			this.image = this.playerAttack[this.attackType].animation[this.spriteNum%this.playerAttack[this.attackType].maxNumber];
			break;
		case "DYING":
			this.image = this.playerDying.animation[this.spriteNum];
			break;
		case "HURT":
			this.image = this.playerHurt.animation[this.spriteNum];
			break;
		case "RUN":
			this.image = this.playerRun.animation[this.spriteNum];
			break;
		}
		if (flip) {
			g2.drawImage(image, x + gp.tileSize * 53 / 40, y, -this.width, this.height, null);
		} else {
			g2.drawImage(image, x, y, this.width, this.height, null);
		}
		g2.setColor(Color.RED);
		g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
		g2.setColor(Color.BLUE);
		if(flip) {
			int range = this.attackZone.width + 2*this.attackZoneDefaultX - this.width;
			g2.drawRect(x + attackZone.x - range, y + attackZone.y, attackZone.width, attackZone.height);
		}
		else g2.drawRect(x + attackZone.x, y + attackZone.y, attackZone.width, attackZone.height);
	}
	public void pickUpObject(int i)
	{
		if(i != 999)
		{
			if(gp.obj[gp.num_CurrentMap][i] != null) {
				gp.playSE(1);
			//PICKUP ONLY ITEMS
				if(gp.obj[gp.num_CurrentMap][i].type == type_pickUpOnly)
				{
					gp.obj[gp.num_CurrentMap][i].use(this);
					gp.obj[gp.num_CurrentMap][i] = null;
				}
				//INVENTORY ITEMS
				else
				{
					String text;
					
					if(inventory.size() != maxInventorySize)
					{
						inventory.add(gp.obj[gp.num_CurrentMap][i]);
	//					gp.playSE(1);
						text = "Got a " + gp.obj[gp.num_CurrentMap][i].name + "!";
					}
					else
					{
						text = "You cannot carry any more!";
					}
					gp.ui.addMessage(text);
					gp.obj[i] = null;
				}
			}
		}
	}
}