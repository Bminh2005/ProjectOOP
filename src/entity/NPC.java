package entity;

import main.GamePanel;

public class NPC extends Character{
	int dialogueIndex;
	
	public void speak() {
		if(dialogues[dialogueIndex] == null)
		{
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction)
		{
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
	
	public NPC(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		dialogueIndex = 0;
	}
	
}
