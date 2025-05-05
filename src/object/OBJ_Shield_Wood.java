package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity{
	
	GamePanel gp;
	
	public OBJ_Shield_Wood(GamePanel gp)
	{
		super(gp);
		
		type = type_shield;
		name = "Wood Shield";
		down1 = setup("/objects/shield_wood", 3*gp.tileSize/4, 3*gp.tileSize/4);
//		defenseValue = 1;
		description = "[" + name + "]\nMade by wood.";
	}
}
