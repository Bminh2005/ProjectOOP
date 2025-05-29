package main;

import entity.NPC_Dialogue;
import entity.NPC_SellingItem;
import map.Teleport;
import monster.Bat;
import monster.Demon;
import monster.Minotuar;
import monster.Skeleton;
import monster.Slime;
import monster.NERC;
import monster.Snake;
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
		int i = 0;
		gp.npc[1][i] = new NPC_Dialogue(gp);
		gp.npc[1][i].worldX = gp.tileSize* 26;
		gp.npc[1][i].worldY = gp.tileSize * 21;
		i++;
		gp.npc[1][i] = new NPC_SellingItem(gp);
		gp.npc[1][i].worldX = gp.tileSize* 21;
		gp.npc[1][i].worldY = gp.tileSize * 21;
		i++;
		
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
		i++;
		gp.monster[1][i] = new Snake(gp);
		gp.monster[1][i].worldX = gp.tileSize*26;
		gp.monster[1][i].worldY = gp.tileSize*24;
		i++;
		gp.monster[1][i] = new Demon(gp);
		gp.monster[1][i].worldX = gp.tileSize*27;
		gp.monster[1][i].worldY = gp.tileSize*27;
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
		gp.monster[2][i] = new NERC(gp);
		gp.monster[2][i].worldX = gp.tileSize*27;
		gp.monster[2][i].worldY = gp.tileSize*29;
		i++;
		gp.monster[2][i] = new NERC(gp);
		gp.monster[2][i].worldX = gp.tileSize*34;
		gp.monster[2][i].worldY = gp.tileSize*35;
		i++;
		gp.monster[2][i] = new NERC(gp);
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
	public void setTeleport() {
		gp.teleportList[1][0] = new Teleport(gp,1,9,11,2,23,23);
		gp.teleportList[1][0].worldX = gp.tileSize * 9;
		gp.teleportList[1][0].worldY = gp.tileSize *11;
		gp.teleportList[1][1] = new Teleport(gp,1,38,11,3,21,34);
		gp.teleportList[1][1].worldX = gp.tileSize * 38;
		gp.teleportList[1][1].worldY = gp.tileSize* 11;
		
		gp.teleportList[2][0] = new Teleport(gp,2,24,24,1,9,10);
		gp.teleportList[2][0].worldX = gp.tileSize*24;
		gp.teleportList[2][0].worldY = gp.tileSize*24;
		gp.teleportList[2][1] = new Teleport(gp,2,30,17,3,34,18);
		gp.teleportList[2][1].worldX = gp.tileSize*30;
		gp.teleportList[2][1].worldY = gp.tileSize*17;
		
		
		gp.teleportList[3][0] = new Teleport(gp,3,21,33,1,39,11);
		gp.teleportList[3][0].worldX = gp.tileSize*21;
		gp.teleportList[3][0].worldY = gp.tileSize*33;
		gp.teleportList[3][1] = new Teleport(gp,3,33,18,2,31,17);
		gp.teleportList[3][1].worldX = gp.tileSize*33;
		gp.teleportList[3][1].worldY = gp.tileSize*18;
	}
	public void setInteractiveTile()
	{

		


	}
}
