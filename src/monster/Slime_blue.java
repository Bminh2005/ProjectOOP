package monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.GamePanel;
import main.SpriteSheet;

public class Slime_blue extends Monster{
	GamePanel gp;
	BufferedImage[] idle;
	BufferedImage[] up;
	BufferedImage[] down;
	BufferedImage[] right;
	BufferedImage[] left;
	BufferedImage[] dead;
	BufferedImage[] getCurrentSheet;
	String state;
	boolean flip;
	public Slime_blue(GamePanel gp) {
		// TODO Auto-generated constructor stub
		super(gp);
        this.gp = gp;
        idle = new SpriteSheet("/monster/slime_blue_idle.png", 132, 29, 4, 0, 0, 27, 24).animation;
		up = new SpriteSheet("/monster/slime_blue_up.png", 218, 40, 7, 0, 0, 27, 24).animation;
		down = new SpriteSheet("/monster/slime_blue_down.png", 218, 36, 7, 0, 0, 27, 24).animation;
		right = new SpriteSheet("/monster/slime_blue_right.png", 217, 36, 7, 0, 0, 27, 24).animation;
		left = new SpriteSheet("/monster/slime_blue_left (2).png", 197, 35, 7, 0, 0, 27, 24).animation;
		dead = new SpriteSheet("/monster/slime_blue_dead.png", 205, 33, 6, 0, 0, 27, 24).animation;
		flip = false;
		/*
		 * this.height = gp.tileSize * 3; // 70 this.width = this.height *194/114; // 96
		 * solidArea.x = this.height ; // 32 solidArea.y = this.height * 45 / 70 - 55;
		 * // 45 solidArea.width = gp.tileSize + gp.tileSize/2; solidArea.height =
		 * gp.tileSize + gp.tileSize + 10; solidAreaDefaultX = solidArea.x;
		 * solidAreaDefaultY = solidArea.y;
		 */
		this.image = idle[0];
		this.spriteNum = 0;
		this.spriteCounter = 0;
		this.speed = 1;
		this.alive = true;
        // Thông tin quái vật
        name = "Blue Slime";
        direction = "up";
        type = type_monster;
        speed = 1;
        maxHp = 25;
        hp = maxHp;

        attack = 20;
        defense = 5;
        exp = 3;
        actionLockCounter = 0;
		getCurrentSheet = up;
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
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
	public void updateDrawImage(int screenX, int screenY) {
			if (spriteNum == -1)
				spriteNum = 0;
			switch (direction) {
			case "up":
				image = up[spriteNum];
				break;
			case "down":
				image = down[spriteNum];
				break;
			case "left":
				image = left[spriteNum];
				
				break;
			case "right":
				image = right[spriteNum];
				
				break;
			}
		} 

	@Override
	public void dyingAnimation(Graphics2D g2) {
		if (dyingCounter < dead.length * 15) {
	        int frame = dyingCounter / 15; // mỗi frame giữ trong 15 tick
	        image = dead[dyingCounter++];
	        dyingCounter++;
	    } else {
	        // Kết thúc animation, biến mất
	        dying = false;
	        alive = false;
	    }
	}

	@Override
	public void updateSpriteNum() {
		switch (direction) {
		case "up":
			spriteNum = (spriteNum + 1) % up.length;
			break;
		case "down":
			spriteNum = (spriteNum + 1) % down.length;
			break;
		case "left":
			spriteNum = (spriteNum + 1) % left.length;
			break;
		case "right":
			spriteNum = (spriteNum + 1) % right.length;
			break;
		}
		}


/*	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		super.draw(g2);
		int screenX = worldX - gp.player.worldX + gp.player.x;
		int screenY = worldY - gp.player.worldY + gp.player.y;

	    int newWidth = image.getWidth() ;
	    int newHeight = image.getHeight();

	    g2.drawImage(image, 0, 0, newWidth, newHeight, null);
	}*/
	}

