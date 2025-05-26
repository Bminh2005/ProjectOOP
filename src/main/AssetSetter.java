package main;

import monster.Bat;
import monster.Minotuar;
import monster.Skeleton;
import monster.Slime;
import object.OBJ_Coin_Bronze;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp)
	{
		this.gp = gp;
	}

	public void setObject()
	{
		int i = 0;
		gp.obj[1][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[1][i].worldX = gp.tileSize*24;
		gp.obj[1][i].worldY = gp.tileSize*30;
	}

	public void setNPC()
	{

	}

	public void setMonster()
	{
		//Map 01
		int i = 0;
		
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*21;
		gp.monster[1][i].worldY = gp.tileSize*21;
		i++;
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*20;
		gp.monster[1][i].worldY = gp.tileSize*21;
		i++;
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*20;
		gp.monster[1][i].worldY = gp.tileSize*20;
		i++;
		gp.monster[1][i] = new Bat(gp);
		gp.monster[1][i].worldX = gp.tileSize * 27;  
		gp.monster[1][i].worldY = gp.tileSize * 21;
		i++;
		gp.monster[1][i] = new Minotuar(gp);
		gp.monster[1][i].worldX = gp.tileSize*27;
		gp.monster[1][i].worldY = gp.tileSize*21;
		gp.monster[1][i].worldX = gp.tileSize*26;
		gp.monster[1][i].worldY = gp.tileSize*24;
		i++;
		
		
		gp.monster[1][i] = new Skeleton(gp);
		gp.monster[1][i].worldX = gp.tileSize*21;
		gp.monster[1][i].worldY = gp.tileSize*24;
		i++;
		
		//Map 02
		i = 0;
		gp.monster[2][i] = new Slime(gp);
		gp.monster[2][i].worldX = gp.tileSize*26;
		gp.monster[2][i].worldY = gp.tileSize*24;
		i++;
		
		//Map 03
		i = 0;
		gp.monster[3][i] = new Slime(gp);
		gp.monster[3][i].worldX = gp.tileSize*24;
		gp.monster[3][i].worldY = gp.tileSize*22;
		i++;

		//gp.monster[i] = new Slime(gp);
		//gp.monster[i].worldX = gp.tileSize*24;
		//gp.monster[i].worldY = gp.tileSize*27;
		//i++;

		

	}
	public void setInteractiveTile()
	{




	}
}
