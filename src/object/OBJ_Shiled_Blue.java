package object;

import java.util.Random;

import entity.Item;
import main.GamePanel;

public class OBJ_Shiled_Blue extends Item {
	
	public OBJ_Shiled_Blue(GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name = "Blue Shield";
		down1 = setup("/objects/shield_blue",3*gp.tileSize/4, 3*gp.tileSize/4);
		defenseValue = 5;
		description = "[" + name + "]\nA shiny blue shield.";
		
	}
}
