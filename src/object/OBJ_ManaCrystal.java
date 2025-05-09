package object;

import entity.Entity;
import entity.Item;
import entity.Player;
import main.GamePanel;

public class OBJ_ManaCrystal extends Item{

	GamePanel gp;
	
	public OBJ_ManaCrystal(GamePanel gp) {
		super(gp);
		
		type = type_pickUpOnly;
		name = "Mana Crystal";
		value = 1;
		down1 = setup("/objects/manacrystal_full", 3*gp.tileSize/4, 3*gp.tileSize/4);
		image = setup("/objects/manacrystal_full", 3*gp.tileSize/4, 3*gp.tileSize/4);
		image2 = setup("/objects/manacrystal_blank", 3*gp.tileSize/4, 3*gp.tileSize/4);
	}
	public void use(Player entity)
	{
		gp.ui.addMessage("Mana + " + value);
		entity.mp += value;
	}
}
