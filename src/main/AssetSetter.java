package main;

import entity.NPC;
import monster.Bat;
import monster.Minotuar;
import monster.Skeleton;
import monster.Slime;
import monster.Slime_blue;
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
		gp.npc[1][0] = new NPC(gp);
		gp.npc[1][0].worldX = gp.tileSize* 13;
		gp.npc[1][0].worldY = gp.tileSize * 7;
		
		
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
		gp.monster[2][i] = new Slime_blue(gp);
		gp.monster[2][i].worldX = gp.tileSize*27;
		gp.monster[2][i].worldY = gp.tileSize*29;
		i++;
		gp.monster[2][i] = new Slime_blue(gp);
		gp.monster[2][i].worldX = gp.tileSize*34;
		gp.monster[2][i].worldY = gp.tileSize*35;
		i++;
		gp.monster[2][i] = new Slime_blue(gp);
		gp.monster[2][i].worldX = gp.tileSize*45;
		gp.monster[2][i].worldY = gp.tileSize*46;
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
