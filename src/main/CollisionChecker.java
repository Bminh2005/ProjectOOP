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