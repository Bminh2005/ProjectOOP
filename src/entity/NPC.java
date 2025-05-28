package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class NPC extends Character{

	public NPC(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		    collision = true;
	        getImage(); 
	        setDialogue();
	}
	public void getImage() {
<<<<<<< HEAD
		image = setup("/monster/skeleton_down_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
		/*
		 * image2 = setup("/npc/npc_ghost_2.png",4*gp.tileSize/3,4*gp.tileSize/3);
		 * image3 = setup("/npc/npc_ghost_3.png",4*gp.tileSize/3,4*gp.tileSize/3);
		 * image4 = setup("/npc/npc_ghost_4.png",4*gp.tileSize/3,4*gp.tileSize/3);
		 */
=======
		image =  setup("/npc/npc_ghost_1",4*gp.tileSize/3,4*gp.tileSize/3);
		image2 =  setup("/npc/npc_ghost_1",4*gp.tileSize/3,4*gp.tileSize/3);
		image3 =  setup("/npc/npc_ghost_1",4*gp.tileSize/3,4*gp.tileSize/3);
		image4 =  setup("/npc/npc_ghost_1",4*gp.tileSize/3,4*gp.tileSize/3);
>>>>>>> branch 'main' of https://github.com/Bminh2005/ProjectOOP
		
	}
	public void setDialogue() {

        dialogues[0] = "Recently, Hi Binh Gold! \n Chao ca lo nha minh !";

    }
	public void speak() {
        if (dialogues[dialogues_index] == null) {
        	dialogues_index = 0;
        }
        gp.ui.currentDialogue = dialogues[0];
        dialogues_index++;
    }
}
