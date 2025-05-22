package monster;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.GamePanel;
import main.SpriteSheet;

public class Minotuar extends Monster{
	SpriteSheet idle;
	SpriteSheet move;
	SpriteSheet attackImage;
	String state;
	String direction;
	boolean flip;
	public Minotuar(GamePanel gp) {
		super(gp);
		idle = new SpriteSheet("/monster/minotuar/MinotuarIDLE_480x96.png", 480, 96, 5, 0, 0, 96, 70);
		move = new SpriteSheet("/monster/minotuar/MinotuarMOVE_768x96.png", 768, 96, 8, 0, 0, 96, 70);
		attackImage = new SpriteSheet("/monster/minotuar/MinotuarATTACK_864x96.png", 864, 96, 9, 0, 0, 96, 70);
		state = "MOVE";
		direction = "right";
		flip = false;
		this.height = gp.tileSize*70/50; //70
		this.width = this.height*96/70; //96
		solidArea.x = this.height*38/70; //32
		solidArea.y = this.height*45/70; //45
		solidArea.width = this.height*30/70; //22
		solidArea.height = this.height*30/70; //19
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		
		this.spriteNum = 0;
		this.spriteCounter = 0;
		this.speed = 1;
		this.alive = true;
		
		name = "Minotuar";
        type = type_monster;
        maxHp = 25;
        hp = maxHp;
        
        attack = 10;
        defense = 0;
        exp = 2;
        
	}
	public void setAction() {
		if(gp.keyH.runPressed == false) {
		if(gp.keyH.upPressed) {
			this.direction = "up";
		}
		if(gp.keyH.downPressed) {
			this.direction = "down";
		}
		if(gp.keyH.leftPressed) {
			this.direction = "left";
		}
		if(gp.keyH.rightPressed) {
			this.direction = "right";
		}
		}
		System.out.println("DIRECTION:" + this.direction);
	}
	
	public void updateSpriteNum() {
		if(this.spriteCounter > 15) {
				spriteNum = (spriteNum + 1)%this.move.maxNumber;
			spriteCounter = 0;
		}
	}
	public void update() {
        gp.cChecker.checkTile(this);
		CollisionOn = false;
		gp.cChecker.checkObject(this, false);
//		gp.cChecker.checkPlayer(this);
//		if (this.type == type_monster && contactPlayer == true) {
//			damagePlayer(attack);
//		}
		this.setAction();
		if (CollisionOn == false) {

			switch (direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
		System.out.println("DANG UPDATE DAY");
		spriteCounter++;
		if(this.spriteCounter >= 10) {
			spriteNum = (spriteNum + 1)%this.move.maxNumber;
			spriteCounter = 0;
		}
		
		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 40) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
//		if (shotAvailableCounter < 30) {
//			shotAvailableCounter++;
//		}
	}
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.x;
		int screenY = worldY - gp.player.worldY + gp.player.y;
		
		
		
				switch(direction) {
				case "right":
					this.flip = false;
					break;
				case "left":
					this.flip = true;
					break;
				}
			this.image = this.move.animation[this.spriteNum];
			
			if(this.flip) {
				g2.drawImage(image, screenX + this.width, screenY, -this.width, this.height, null);
			}
			else {
				g2.drawImage(image, screenX, screenY, this.width, this.height, null);
			}
			g2.setColor(Color.red);
			g2.drawRect(screenX + this.solidAreaDefaultX, screenY + this.solidAreaDefaultY, this.solidArea.width, this.solidArea.height);
	}
	
	

}
