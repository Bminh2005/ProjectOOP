package main;

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
	}
	
	public void setNPC()
	{
		
	}
	
	public void setMonster()
	{
		int i = 0;
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*18;
		gp.monster[1][i].worldY = gp.tileSize*24;
		i++;
		gp.monster[1][i] = new Skeleton(gp);
		gp.monster[1][i].worldX = gp.tileSize*20;
		gp.monster[1][i].worldY = gp.tileSize*24;
		i++;
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*26;
		gp.monster[1][i].worldY = gp.tileSize*24;
		i++;
		gp.monster[1][i] = new Slime(gp);
		gp.monster[1][i].worldX = gp.tileSize*24;
		gp.monster[1][i].worldY = gp.tileSize*22;
		i++;
		//gp.monster[i] = new Slime(gp);
		//gp.monster[i].worldX = gp.tileSize*24;
		//gp.monster[i].worldY = gp.tileSize*27;
		//i++;
		
		gp.obj[0] = new OBJ_Coin_Bronze(gp);
		gp.obj[0].worldX = gp.tileSize*24;
		gp.obj[0].worldY = gp.tileSize*30;
	}
	public void setInteractiveTile()
	{
		
		
	
		
	}
}
