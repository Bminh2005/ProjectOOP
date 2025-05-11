package object;

import entity.Entity;
import entity.Item;
import entity.Player;
import main.GamePanel;

public class OBJ_ManaCrystal extends Item{
	public OBJ_ManaCrystal(GamePanel gp) {
		super(gp);
		
		type = type_pickUpOnly;
		name = "Mana Crystal";
		value = 5;
		down1 = setup("/objects/manacrystal_full", 3*gp.tileSize/4, 3*gp.tileSize/4);
		image = setup("/objects/manacrystal_full", 3*gp.tileSize/4, 3*gp.tileSize/4);
		image2 = setup("/objects/manacrystal_blank", 3*gp.tileSize/4, 3*gp.tileSize/4);
	}
	public void use(Player entity)
	{
		gp.ui.addMessage("Mana + " + value);
		if(entity.mp + value > entity.maxMp) {
			entity.mp = entity.maxMp;
		}
		else entity.mp += value;
	}
}
