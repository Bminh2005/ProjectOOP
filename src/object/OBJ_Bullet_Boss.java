package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Bullet_Boss extends Projectile{

	public OBJ_Bullet_Boss(GamePanel gp) {
		super(gp);
		name = "Dark ball";
		speed = 8;
		maxHp = 80;
		hp = maxHp;
		attack = 15;
		useCost = 2;
		solidArea.x = 12;
		solidArea.y = 12;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 12;
		solidArea.height = 12;
		alive = false;
		getImage();
		
	}
	public void getImage() {
		
	}

}
