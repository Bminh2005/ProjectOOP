package object;

import java.awt.Color;

import entity.Entity;
import entity.Player;
import entity.Projectile;
import main.GamePanel;

public class OBJ_ThunderBolt extends Projectile{

	
	public OBJ_ThunderBolt(GamePanel gp) {
		super(gp);
		name = "Thunder Bolt";
		speed = 5;
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
	
	public void getImage()
	{
		up1 = setup("/projectile/lightning_skill1_frame1", gp.tileSize*3, gp.tileSize);
		up2 = setup("/projectile/lightning_skill1_frame2", gp.tileSize*3, gp.tileSize);
		down1 = setup("/projectile/lightning_skill1_frame3", gp.tileSize*3, gp.tileSize);
		down2 = setup("/projectile/lightning_skill1_frame4", gp.tileSize*3, gp.tileSize);
		left1 = setup("/projectile/lightning_skill1_frame1", gp.tileSize*3, gp.tileSize);
		left2 = setup("/projectile/lightning_skill1_frame2", gp.tileSize*3, gp.tileSize);
		right1 = setup("/projectile/lightning_skill1_frame3", gp.tileSize*3, gp.tileSize);
		right2 = setup("/projectile/lightning_skill1_frame4", gp.tileSize*3, gp.tileSize);
	}
	public boolean haveResource(Player user)
	{
		boolean haveResource = false;
		if(user.mp >= useCost)
		{
			haveResource = true;
		}
		return haveResource;
	}
	public void subtractResource(Player user)
	{
		user.mp -= useCost;
	}
	public Color getParticleColor()
	{
		Color color = new Color(240,50,0);
		return color;
	}
	public int getParticleSize()
	{
		int size = 10;
		return size;
	}
	public int getParticleSpeed()
	{
		int speed = 1;
		return speed;
	}
	public int getParticleMaxLife()
	{
		int maxLife = 20;
		return maxLife;
	}
}
