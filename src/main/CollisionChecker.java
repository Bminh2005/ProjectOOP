package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entityLeftWorldX + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entityTopWorldY + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		int tileNum1;
		int tileNum2;
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY- 2*entity.speed)/gp.tileSize;
			tileNum1 = gp.currentMap.getLayer2().layerTileNum[entityTopRow][entityLeftCol];
			tileNum2 = gp.currentMap.getLayer2().layerTileNum[entityTopRow][entityRightCol];
			if(tileNum1!= 0 || tileNum2 !=0) {
				if(tileNum1!=0) {
					if(gp.currentMap.getLayer2().tiles[tileNum1].collision) {
						entity.CollisionOn = true;
					}
				}
				else {
					if(tileNum2!=0) {
						if(gp.currentMap.getLayer2().tiles[tileNum2].collision) {
							entity.CollisionOn = true;
						}
					}
				}
			}
			else entity.CollisionOn = false;
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY+entity.speed)/gp.tileSize;
			tileNum1 = gp.currentMap.getLayer2().layerTileNum[entityBottomRow][entityLeftCol];
			tileNum2 = gp.currentMap.getLayer2().layerTileNum[entityBottomRow][entityRightCol];
			if(tileNum1!= 0 || tileNum2 !=0) {
				if(tileNum1!=0) {
					if(gp.currentMap.getLayer2().tiles[tileNum1].collision) {
						entity.CollisionOn = true;
					}
				}
				else {
					if(tileNum2!=0) {
						if(gp.currentMap.getLayer2().tiles[tileNum2].collision) {
							entity.CollisionOn = true;
						}
					}
				}
			}
			else entity.CollisionOn = false;
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.currentMap.getLayer2().layerTileNum[entityBottomRow][entityLeftCol];
			tileNum2 = gp.currentMap.getLayer2().layerTileNum[entityTopRow][entityLeftCol];
			if(tileNum1!= 0 || tileNum2 !=0) {
				if(tileNum1!=0) {
					if(gp.currentMap.getLayer2().tiles[tileNum1].collision) {
						entity.CollisionOn = true;
					}
				}
				else {
					if(tileNum2!=0) {
						if(gp.currentMap.getLayer2().tiles[tileNum2].collision) {
							entity.CollisionOn = true;
						}
					}
				}
			}
			else entity.CollisionOn = false;
			break;
		case "right":
			entityRightCol = (entityRightWorldX +entity.speed)/gp.tileSize;
			tileNum1 = gp.currentMap.getLayer2().layerTileNum[entityBottomRow][entityRightCol];
			tileNum2 = gp.currentMap.getLayer2().layerTileNum[entityTopRow][entityRightCol];
			if(tileNum1!= 0 || tileNum2 !=0) {
				if(tileNum1!=0) {
					if(gp.currentMap.getLayer2().tiles[tileNum1].collision) {
						entity.CollisionOn = true;
					}
				}
				else {
					if(tileNum2!=0) {
						if(gp.currentMap.getLayer2().tiles[tileNum2].collision) {
							entity.CollisionOn = true;
						}
					}
				}
			}
			else entity.CollisionOn = false;
			break;
		}
	}
	public int checkObject(Entity entity, boolean player)
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
	public int checkEntity(Entity entity, Entity[] target)
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
	public boolean checkPlayer(Entity entity)
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