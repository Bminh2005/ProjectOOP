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
	private ChuDongTanCong ai;
	public Minotuar(GamePanel gp) {
		super(gp);
		idle = new SpriteSheet("/monster/minotuar/MinotuarIDLE_480x96.png", 480, 96, 5, 0, 0, 96, 70);
		move = new SpriteSheet("/monster/minotuar/MinotuarMOVE_768x96.png", 768, 96, 8, 0, 0, 96, 70);
		attackImage = new SpriteSheet("/monster/minotuar/MinotuarATTACK_864x96.png", 864, 96, 9, 0, 0, 96, 70);
		flip = false;
		this.height = gp.tileSize*70/50; //70
		this.width = this.height*96/70; //96
		solidArea.x = this.height*38/70 - 5; //32
		solidArea.y = this.height*45/70 - 6; //45
		solidArea.width = this.height*30/70; //22
		solidArea.height = this.height*30/70; //19
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		this.image = idle.getSpriteNum(0);
		
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
        direction = "down";
        
        this.ai = gp.quaiVatTanCong;
        
	}
	public void setAction() {
		actionLockCounter++;
        
	       
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }
            actionLockCounter = 0;
        }
	}
	
	public void updateSpriteNum() {
		if(this.spriteCounter > 15) {
				spriteNum = (spriteNum + 1)%this.move.maxNumber;
			spriteCounter = 0;
		}
	}
	public void damagePlayer(int attack) {
		if (gp.player.invincible == false) {
			// we can give damage
//			gp.playSE(6);
			gp.player.takeDamge(attack);

			gp.player.invincible = true;
		}
	}


	public void takeDamage(int playerAttack) {
		this.hp -= playerAttack;
		gp.ui.addMessage(playerAttack + " damage!");
		invincible = true;
		if (this.hp <= 0) {
			dying = true;
//			checkDrop();
		}
	}
	

}
