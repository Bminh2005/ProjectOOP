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
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class Player extends Entity {
	KeyHandler keyH;
	int speed;
	public String state;
	public ArrayList<Entity> inventory = new ArrayList<>();

	// PLAYER'S SAITAMA
	private float saitama;
	private final float MAX_SAITAMA = 100f;
	private final float MIN_SAITAMA_TO_RUN = 50f;
	private final float SAITAMA_DECREASE_RATE = 20f;
	private final float SAITAMA_RECOVER_RATE = 10f;
	private boolean tired;

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

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		// SOLID AREA
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		setDefaultValues();
		getPlayerImage();
		currentWeapon = new OBJ_Sword_Normal(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		System.out.println(this.state);
	}

	public void setDefaultValues() {
		// Status
		tired = false;
		saitama = MAX_SAITAMA;
		maxHp = 20;
		hp = this.maxHp;
		maxMp = 10;
		mp = this.maxMp;
		attack = 10;
		defense = 10;
		speed = 3;
		level = 1;
		state = "NORMAL";
		attackType = 0;
		exp = 0;
		coin = 0;
		nextLevel = level + 1;
		projectile = new OBJ_Fireball(gp);
		// Position
		worldX = 24 * gp.tileSize;
		worldY = 24 * gp.tileSize;
		frameCounter = 0;
		flip = false;

		// Attributes
		comboAttackDelayTime = 0;
		x = (gp.screenWidth / 2) - (gp.tileSize / 2);
		y = (gp.screenHeight / 2) - (gp.tileSize / 2);
		playerAttack = new SpriteSheet[3];
		runningCountAttackDelay = false;
		height = gp.tileSize;
		width = gp.tileSize * 53 / 40;
		solidArea = new Rectangle(17, 23, 32, 32);
		CollisionOn = false;
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

	public void update() {
		// System.out.println("SAITAMA = " + this.saitama);
		// System.out.println("SPEED = " + this.speed);
		if (this.runningCountAttackDelay) {
			this.comboAttackDelayTime++;
			if (this.comboAttackDelayTime == 20) {
				this.attackType = 0;
			}
		}
		if (this.invincible) {
			this.invincibleCounter++;
			if (this.invincibleCounter >= 60) {
				this.invincible = false;
				this.invincibleCounter = 0;
			}
		}
		if (hp > 0) {

			if (this.state.equals("RUN") == false) {
				if (this.saitama < this.MAX_SAITAMA)
					this.saitama += this.SAITAMA_RECOVER_RATE * 1f / 60f;
			}
			if (this.saitama >= this.MIN_SAITAMA_TO_RUN)
				this.tired = false;
			if (this.keyH.upPressed == true || this.keyH.downPressed == true || this.keyH.leftPressed == true
					|| this.keyH.rightPressed == true) {
				if (this.saitama <= 0)
					this.tired = true;
				if (this.keyH.runPressed && this.tired == false) {
					this.run();
				} else {

					this.walk();
				}
				this.move();
			} else if (this.keyH.attackPressed == true) {
				this.runningCountAttackDelay = false;
				this.comboAttackDelayTime = 0;
				this.attack();
				System.out.println(this.spriteNum);
				if (this.state.equals("ATTACKING") && this.spriteNum == 3 && this.frameCounter % 4 == 0) {
					this.checkAttackonMonster();
				}

				if (this.spriteNum == this.playerAttack[attackType].maxNumber) {
					this.keyH.attackPressed = false;
					this.attackType = (this.attackType + 1) % 3;
					this.runningCountAttackDelay = true;
					this.idle();
				}
			} else if (this.keyH.damagePressed == true) {
				this.hurt();
				if (this.spriteNum == this.playerHurt.maxNumber) {
					this.keyH.damagePressed = false;
					this.hp -= 5;
					this.idle();
				}
			} else {
				this.idle();
			}
		} else {
			this.dying();
			if (state.equals("DYING") == false) {
				state = "DYING";
			}
		}
		this.frameCounter++;
		if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30 && projectile.haveResource(this) == true)
		{
			//SET DEFAULT COORDINATES, DIRECTION AND USER
			projectile.set(worldX, worldY, direction, true, this);
			
			//SUBTRACT THE COST (MANA, AMMO ETC.)
			projectile.subtractResource(this);
			
			//ADD IT TO THE LIST
			gp.projectileList.add(projectile);
			
			shotAvailableCounter = 0;
			
//			gp.playSE(10);
		}
		if(shotAvailableCounter < 30)
		{
			shotAvailableCounter++;
		}
	}

	public void idle() {
		if (this.state.equals("IDLE") == false) {
			this.spriteNum = this.playerIdle.maxNumber - 1;
		}
		this.state = "IDLE";
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
		boolean overx = this.worldX >= gp.maxWorldWidth - (gp.screenWidth + this.width) * 10 / 20
				|| this.worldX <= (gp.screenWidth - this.width) * 10 / 20;
		boolean overy = this.worldY >= gp.maxWorldHeight - (gp.screenHeight + this.height) * 10 / 20
				|| this.worldY <= (gp.screenHeight - this.height) * 10 / 20;
		if (this.keyH.upPressed == true) {
			direction = "up";
			this.CollisionOn = false;
			gp.cChecker.checkTile(this);
			if (!this.CollisionOn) {
				if (overy)
					this.y -= this.speed;
				this.worldY -= this.speed;
			}
		}
		if (this.keyH.downPressed == true) {
			direction = "down";
			this.CollisionOn = false;
			gp.cChecker.checkTile(this);
			if (!this.CollisionOn) {
				if (overy)
					this.y += this.speed;
				this.worldY += this.speed;
			}
		}
		if (this.keyH.leftPressed == true) {
			direction = "left";
			this.CollisionOn = false;
			gp.cChecker.checkTile(this);
			if (!this.CollisionOn) {
				if (overx)
					this.x -= this.speed;
				this.worldX -= this.speed;
				this.flip = true;
			}

		}
		if (this.keyH.rightPressed == true) {
			direction = "right";
			this.CollisionOn = false;
			gp.cChecker.checkTile(this);
			if (!this.CollisionOn) {
				if (overx)
					this.x += this.speed;
				this.worldX += this.speed;
				this.flip = false;
			}
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
			if (flip)
				this.worldX -= 1;
			else
				this.worldX += 1;
		}
		if (this.frameCounter % 5 == 0) {
			// System.out.println(this.spriteNum);
			this.spriteNum++;
		}
	}

	public void hurt() {
		System.out.println("Player is injured!");
		if (this.state.equals("HURT") == false) {
			this.spriteNum = -1;
			this.frameCounter = 0;
		}
		this.hp -= 5;
		if (this.flip)
			this.worldX += 1;
		else
			this.worldX -= 1;
		System.out.println("HP = " + this.hp);
		this.state = "HURT";
		if (this.frameCounter % 5 == 0) {
			this.spriteNum++;
		}
	}

	public void dying() {
		if (this.state.equals("DYING") == false) {
			this.spriteNum = -1;
			this.frameCounter = 0;
			gp.gameState = gp.gameOverState;
		}
		System.out.println("Player is died!");
		this.state = "DYING";
		if (this.frameCounter % 10 == 0) {
			if (this.spriteNum < this.playerDying.maxNumber - 1) {
				this.spriteNum++;
			}
		}

	}

	public void checkAttackonMonster() {
		int range = 10;
		if (this.state.equals("ATTACKING")) {
			Rectangle attackzone = new Rectangle(this.worldX + this.solidArea.x, this.worldY + this.solidArea.y,
					this.solidArea.width, this.solidArea.height);
			if (flip)
				attackzone.x -= range;
			else
				attackzone.x += range;
			attackzone.width += range;
			System.out.println(attackzone.x + " " + attackzone.y + " " + attackzone.width + " " + attackzone.height);
			for (int i = 0; i < gp.monster.length; i++) {
				Monster m = gp.monster[i];
				if (m != null && m.hp > 0) {
					Rectangle monsterArea = new Rectangle(m.worldX + m.solidArea.x, m.worldY + m.solidArea.y,
							m.solidArea.width, m.solidArea.height);
					System.out.println(monsterArea.x + " " + monsterArea.y);
					// System.out.println(monsterArea.x +" " + monsterArea.y+ " "+ monsterArea.width
					// +" "+ monsterArea.height);
					if (attackzone.intersects(monsterArea)) {
						System.out.println("Monster is attacked!");
						m.takeDamage(this.attack - gp.monster[i].defense);
					}
				}
			}
		}
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
//		inventory.add(new OBJ_Key(gp));
	}

	public void selectItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();

		if (itemIndex < inventory.size()) {
			Entity selectedItem = inventory.get(itemIndex);

			if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
				currentWeapon = selectedItem;
//				attack = getAttack();
//				getPlayerAttackImage();
			}
			if (selectedItem.type == type_shield) {
				currentShield = selectedItem;
//				defense = getDefense();
			}
			if (selectedItem.type == type_consumable) {
//				selectedItem.use(this);
				inventory.remove(itemIndex);
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
			this.image = this.playerAttack[this.attackType].animation[this.spriteNum];
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
		// Display the player's current state as text
		// g2.setColor(Color.BLACK);
		// g2.drawString("State: " + state, x, y - 10);
	}
}