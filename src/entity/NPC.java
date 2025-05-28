package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class NPC extends Character{

	public NPC(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		    collision = true;
		    CollisionOn = false;
		    solidArea = new Rectangle();
		    solidArea.x = 22; // = 17*gp.tileSize/40
			solidArea.y = 26; // = 22*gp.tileSize/40
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
			solidArea.width = 22; // = 21*gp.tileSize/40
			solidArea.height = 22; //= 18*gp.tileSize/40
	        getImage(); 
	        setDialogue();
	}
	public void getImage() {
		image =  setup("/npc/npc_ghost_1",4*gp.tileSize/3,4*gp.tileSize/3);
		image2 = setup("/npc/npc_ghost_2",4*gp.tileSize/3,4*gp.tileSize/3);
		image3 = setup("/npc/npc_ghost_3",4*gp.tileSize/3,4*gp.tileSize/3);
		image4 = setup("/npc/npc_ghost_4",4*gp.tileSize/3,4*gp.tileSize/3);
		 
	}
	public void setDialogue() {

        dialogues[0] = "Recently, Hi Binh Gold! \n Chao ca lo nha minh ! \n This word has been conquered by terrible monster! \n There are two doors leading to different word!";

    }
	public void speak() {
        if (dialogues[dialogues_index] == null) {
        	dialogues_index = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogues_index];
        dialogues_index++;
    }
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		int screenX = worldX - gp.player.worldX + gp.player.x;
		int screenY = worldY - gp.player.worldY + gp.player.y;
		g2.drawImage(image, screenX, screenY, null);
		if (gp.testMode) {
	        g2.setColor(Color.RED);
	        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	    }

	}
	@Override
	public void update() {
		gp.cChecker.checkTile(this);
		CollisionOn = false;
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
		 spriteCounter++;
		    if (spriteCounter > 15) {
		        if (spriteNum == 1) image = image2;
		        else if (spriteNum == 2) image = image3;
		        else if (spriteNum == 3) image = image4;
		        else image = setup("/npc/npc_ghost_1",4*gp.tileSize/3,4*gp.tileSize/3);

		        spriteNum++;
		        if (spriteNum > 4) spriteNum = 1;
		        spriteCounter = 0;
		    }
		}
	}
