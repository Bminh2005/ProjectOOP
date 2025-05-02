package monster;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public abstract class Monster extends Entity {
	
	public int spriteNum = 1;
	
	GamePanel gp;
    public Monster(GamePanel gp) {
        super(gp);
        solidArea.x = 3;
    	solidArea.y = 18;
    	solidArea.width = 42;
    	solidArea.height = 30;
    	solidAreaDefaultX = solidArea.x;
    	solidAreaDefaultY = solidArea.y;
    	
    }

    @Override
    public void update() {
    	setAction();
		
		CollisionOn = false;
		gp.cChecker.checkTile(this);
//		gp.cChecker.checkObject(this, false);
//		gp.cChecker.checkEntity(this, gp.npc);
//		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == type_monster && contactPlayer == true)
		{
			damagePlayer(attack);
		}
		
		if (!CollisionOn) {
	        switch (direction) {
	            case "up": worldY -= speed; break;
	            case "down": worldY += speed; break;
	            case "left": worldX -= speed; break;
	            case "right": worldX += speed; break;
	        }
	    }
		
		spriteCounter++;
		if(spriteCounter > 15)
		{
			if(spriteNum == 1)
			{
				spriteNum = 2;
			}
			else if(spriteNum == 2)
			{
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
		if(invincible == true)
		{
			invincibleCounter++;
			if(invincibleCounter > 40)
			{
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if(shotAvailableCounter < 30)
		{
			shotAvailableCounter++;
		}
    }
    public void damagePlayer(int attack)
	{
		if(gp.player.invincible == false)
		{
			//we can give damage
//			gp.playSE(6);
			
			int damage = attack - gp.player.defense;
			if(damage < 0)
			{
				damage = 0;
			}
			gp.player.hp -= damage;
			
			gp.player.invincible = true;
		}
	}
    public void takeDamage(int n) {
    	this.hp -= n;
    	if(this.hp < 0) this.hp =0;
    }

    // Phuong thuc abstract cho hành động của quái vật
    public abstract void setAction();

    // Phuong thuc kiểm tra việc rơi đồ
    public abstract void checkDrop();
    public void dyingAnimation(Graphics2D  g2)
	{
		dyingCounter++;
		int i = 5;
		
		if(dyingCounter <= i)
		{
			changeAlpha(g2,0f);
		}
		if(dyingCounter > i && dyingCounter <= i*2)
		{
			changeAlpha(g2,1f);
		}
		if(dyingCounter > i*2 && dyingCounter <= i*3)
		{
			changeAlpha(g2,0f);
		}
		if(dyingCounter > i*3 && dyingCounter <= i*4)
		{
			changeAlpha(g2,1f);
		}
		if(dyingCounter > i*4 && dyingCounter <= i*5)
		{
			changeAlpha(g2,0f);
		}
		if(dyingCounter > i*5 && dyingCounter <= i*6)
		{
			changeAlpha(g2,1f);
		}
		if(dyingCounter > i*6 && dyingCounter <= i*7)
		{
			changeAlpha(g2,0f);
		}
		if(dyingCounter > i*7 && dyingCounter <= i*8)
		{
			changeAlpha(g2,1f);
		}
		if(dyingCounter > i*8)
		{
			alive = false;
		}
	}
	public void changeAlpha(Graphics2D g2, float alphaValue)
	{
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
    public void draw(Graphics2D g2)
	{
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.x;
		int screenY = worldY - gp.player.worldY + gp.player.y;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.x 
		   && worldX - gp.tileSize < gp.player.worldX + gp.player.x 
		   && worldY + gp.tileSize > gp.player.worldY - gp.player.y 
		   && worldY - gp.tileSize < gp.player.worldY + gp.player.y)
		{
			switch(direction) {
		    case "up":    image = (spriteNum == 1) ? up1 : up2; break;
		    case "down":  image = (spriteNum == 1) ? down1 : down2; break;
		    case "left":  image = (spriteNum == 1) ? left1 : left2; break;
		    case "right": image = (spriteNum == 1) ? right1 : right2; break;
			}

			
			//MONSTER HP BAR
			if(type == 2 && hpBarOn == true)
			{	
				double oneScale = (double)gp.tileSize/maxHp;
				double hpBarValue = oneScale*hp;
				
				g2.setColor(new Color(35,35,35));
				g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);
				
				g2.setColor(new Color(255, 0, 30));
				g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
				
				hpBarCounter++;
				
				if(hpBarCounter > 600)
				{
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}
			
			
			if(invincible == true)
			{
				hpBarOn = true;
				hpBarCounter = 0;
				changeAlpha(g2, 0.4F);
			}
			if(dying == true)
			{
				dyingAnimation(g2);
			}
			
			g2.drawImage(image, screenX, screenY, null);
			
			changeAlpha(g2, 1F);
		}
	}
}