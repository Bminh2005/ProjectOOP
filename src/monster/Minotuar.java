package monster;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.GamePanel;
import main.SpriteSheet;

public class Minotuar extends Monster{
	BufferedImage idle[];
	BufferedImage move[];
	BufferedImage attackImage[];
	String state;
	public Rectangle attackZone;
	public int attackZoneDefaultX;
	public int attackZoneDefaultY;
	
	boolean flip;
	public Minotuar(GamePanel gp) {
		super(gp);
		idle = new SpriteSheet("/monster/minotuar/MinotuarIDLE_480x96.png", 480, 96, 5, 0, 0, 96, 70).animation;
		move = new SpriteSheet("/monster/minotuar/MinotuarMOVE_768x96.png", 768, 96, 8, 0, 0, 96, 70).animation;
		attackImage = new SpriteSheet("/monster/minotuar/MinotuarATTACK_864x96.png", 864, 96, 9, 0, 0, 96, 70).animation;
		flip = false;
		this.height = gp.tileSize*70/50; //70
		this.width = this.height*96/70; //96
		solidArea.x = this.height*38/70; //32
		solidArea.y = this.height*45/70; //45
		solidArea.width = this.height*30/70; //22
		solidArea.height = this.height*30/70; //19
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		attackZone = new Rectangle();
		attackZone.x = this.height*34/70;
		attackZone.y = 0;
		attackZoneDefaultX = attackZone.x;
		attackZoneDefaultY = attackZone.y;
		
		attackZone.width = this.height*51/70; //22
		attackZone.height = this.height*69/70;
		this.image = idle[0];
		
		this.spriteNum = 0;
		this.spriteCounter = 0;
		this.speed = 1;
		this.alive = true;
		name = "Minotuar";
        type = type_monster;
        maxHp = 25;
        hp = maxHp;
        state = "MOVE";
        attack = 10;
        defense = 0;
        exp = 2;
        direction = "up";
        actionLockCounter = 0;
	}
	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		System.out.println(move.length);
		actionLockCounter++;
		 if (actionLockCounter == 120) {
			 if(state == "MOVE") {
				 Random random = new Random();
		            int i = random.nextInt(100) + 1;

		            if (i <= 25) {
		                direction = "up";
		            } else if (i <= 50) {
		                direction = "down";
		            } else if (i <= 75) {
		                direction = "left";
		                this.flip = true;
		            } else {
		                direction = "right";
		                this.flip = false;
		            }
		            actionLockCounter = 0;
			 }
			 else {
				 direction = "idle";
			 }
	     
	            
		 }
		 System.out.println(direction);
	}
	public void updateDrawImage(int screenX, int screenY) {
		if(state == "MOVE")
		switch (direction) {
		case "up":
			image = move[spriteNum]; 
			break;
		case "down":
			image = move[spriteNum]; 
			break;
		case "left":
			image = move[spriteNum];; 
			break;
		case "right":
			image = move[spriteNum];; 
			break;
		}
		else if(state == "ATTACK") {
			image = attackImage[spriteNum];
		}
	}
	
	public void attack() {
		if (state != "ATTACK") {
			state = "ATTACK";
			spriteNum = -1;
		}
	}
	public void updateSpriteNum() {
		if (spriteCounter > 15) {
			switch(state) {
			case "MOVE":
				spriteNum = (spriteNum+1)%move.length;
				break;
			case "ATTACK":
				spriteNum = (spriteNum+1)%attackImage.length;
				break;
			case "IDLE":
				spriteNum = (spriteNum+1)%idle.length;
				break;
			}
			spriteCounter = 0;
		}
	}
	
	public void drawImage(Graphics2D g2, int screenX, int screenY) {
		g2.setColor(Color.blue);
		int range = this.attackZone.width + 2*this.attackZoneDefaultX - this.width;
		if(flip) {
			g2.drawImage(image, screenX + this.width, screenY, -this.width, this.height, null);
			g2.drawRect(screenX + this.attackZoneDefaultX - range, screenY + this.attackZoneDefaultY, this.attackZone.width, this.attackZone.height);
		}
		else {
			g2.drawImage(image, screenX, screenY, this.width, this.height, null);
			g2.drawRect(screenX + this.attackZoneDefaultX, screenY + this.attackZoneDefaultY, this.attackZone.width, this.attackZone.height);
		}
	}
}