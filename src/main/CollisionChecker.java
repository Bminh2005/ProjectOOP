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
			entityTopRow = (entityTopWorldY-entity.speed)/gp.tileSize;
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
}
