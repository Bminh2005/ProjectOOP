package main;

import entity.Entity;
import entity.Player;
import map.MapLayer;
import map.MapTile;

import java.awt.Rectangle;

import entity.Character;

public class CollisionChecker {
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Character entity) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		MapLayer layer = gp.currentMap.getLayer2();
		int[][] layerTileNum = layer.layerTileNum;
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			int worldX = col*gp.tileSize;
			int worldY = row*gp.tileSize;
			x = worldX - gp.player.worldX + gp.player.x;
			y = worldY- gp.player.worldY + gp.player.y;
			int tileNum = layerTileNum[row][col];
			if(worldX + gp.tileSize > entity.worldX - 5* gp.tileSize &&
					worldX - gp.tileSize < entity.worldX + 5*gp.tileSize &&
					worldY + gp.tileSize > entity.worldY - 5*gp.tileSize &&
					worldY - gp.tileSize < entity.worldY + 5*gp.tileSize) {
				if(tileNum != 0) {
					checkTile(entity, layer.tiles[tileNum], worldX, worldY);
				}
			}
			col++;
			if(col >= gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}		
	}
	
	public void checkTile(Character entity, MapTile tile, int x, int y) {
		entity.solidArea.x = entity.worldX + entity.solidAreaDefaultX;
		entity.solidArea.y = entity.worldY + entity.solidAreaDefaultY;
		Rectangle solid = new Rectangle();
		solid.x = x + tile.collisionArea.x;
		solid.y = y + tile.collisionArea.y;
		solid.height = tile.collisionArea.height;
		solid.width = tile.collisionArea.width;
		switch(entity.direction) {
		case "up":
			if(solid.intersects(entity.solidArea)) {
				while(solid.intersects(entity.solidArea)) {
					entity.solidArea.y += 1;
					entity.worldY += 1;
				}
			}
			break;
		case "down":
			if(solid.intersects(entity.solidArea)) {
				while(solid.intersects(entity.solidArea)) {
					entity.solidArea.y -= 1;
					entity.worldY -= 1;
				}
			}
			break;
		case "left":
			if(solid.intersects(entity.solidArea)) {
				while(solid.intersects(entity.solidArea)) {
					entity.solidArea.x += 1;
					entity.worldX += 1;
				}
			}
			break;
		case "right":
			entity.solidArea.x += entity.speed;
			if(solid.intersects(entity.solidArea)) {
				while(solid.intersects(entity.solidArea)) {
					entity.solidArea.x -= 1;
					entity.worldX -= 1;
				}
			}
			break;
		}
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
	}
	public int checkObject(Character entity, boolean player)
	{
		int index = 999;
		
		for(int i = 0; i < gp.obj.length; i++)
		{
			if(gp.obj[i] != null)
			{
				//Get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// Get the object's solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction)
				{
				case "up":
					entity.solidArea.y -= entity.speed;
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				
				if(entity.solidArea.intersects(gp.obj[i].solidArea))
				{
					if(gp.obj[i].collision == true)
					{
						entity.CollisionOn = true;
					}
					if(player == true)
					{
						index = i;
					}
				}
				
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
				
			}
			
		}
		
		return index;
	}
	//NPC or MONSTER
	public void checkCharacter(Character c1, Character c2) {
		c1.solidArea.x = c1.worldX + c1.solidAreaDefaultX;
		c1.solidArea.y = c1.worldY + c1.solidAreaDefaultY;
		c2.solidArea.x = c2.worldX + c2.solidAreaDefaultX;
		c2.solidArea.y = c2.worldY + c2.solidAreaDefaultY;
		
		if(c1.solidArea.intersects(c2.solidArea)) {
			switch(c1.direction) {
			case "up":
				c1.worldY += c1.speed + 1;
				
				break;
			case "down":
				c1.worldY -= c1.speed + 1;
				
				break;
			case "left":
				c1.worldX += c1.speed + 1;
				break;
			case "right":
				c1.worldX -= c1.speed + 1;
				break;
			}
			
			switch(c2.direction) {
			case "up":
				c2.worldY += c2.speed + 1;
				
				break;
			case "down":
				c2.worldY -= c2.speed + 1;
				
				break;
			case "left":
				c2.worldX += c2.speed + 1;
				break;
			case "right":
				c2.worldX -= c2.speed + 1;
				break;
			}
		}
	}
	public int checkEntity(Character entity, Character[] target)
	{
		int index = 999;
		
		for(int i = 0; i < target.length; i++)
		{
			if(target[i] != null)
			{
				//Get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// Get the object's solid area position
				target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
				
				switch(entity.direction)
				{
				case "up":
					entity.solidArea.y -= entity.speed;
					
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
						
					break;
				case "right":
					entity.solidArea.x += entity.speed;
		
					break;
				}
				if(entity.solidArea.intersects(target[i].solidArea))
				{
					if(target[i] != entity)
					{
						entity.CollisionOn = true;
						index = i;
					}
					
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
				
			}
				
		}
			
		return index;
	}
	public boolean checkPlayer(Character entity)
	{
		boolean contactPlayer = false;
		
		//Get entity's solid area position
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		
		// Get the object's solid area position
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		switch(entity.direction)
		{
		case "up":
			entity.solidArea.y -= entity.speed;
			break;
		case "down":
			entity.solidArea.y += entity.speed;
			break;
		case "left":
			entity.solidArea.x -= entity.speed;
			break;
		case "right":
			entity.solidArea.x += entity.speed;
			break;
		}
		if(entity.solidArea.intersects(gp.player.solidArea))
		{
			entity.CollisionOn = true;
			contactPlayer = true;
		}
		
		
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
	
		return contactPlayer;
	}
}