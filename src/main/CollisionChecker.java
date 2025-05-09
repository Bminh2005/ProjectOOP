package main;

import entity.Entity;
import map.MapLayer;
import entity.Character;

public class CollisionChecker {
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidAreaDefaultX;
		int entityRightWorldX = entityLeftWorldX + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidAreaDefaultY;
		int entityBottomWorldY = entityTopWorldY + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;		
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		System.out.println("X: " + entityLeftCol +" Y: " + entityTopRow);
		int tileNum1 = 0, tileNum2 = 0, tileNum3 = 0, tileNum4 = 0;
		MapLayer layer = gp.currentMap.getLayer2();
		if(entityTopRow >= 1)
			tileNum1 = layer.layerTileNum[entityBottomRow - 1][entityLeftCol];
		if(entityLeftCol < gp.maxWorldCol)
			tileNum2 = layer.layerTileNum[entityTopRow][entityLeftCol + 1];
		if(entityTopRow < gp.maxWorldRow)
			tileNum3 = layer.layerTileNum[entityTopRow + 1][entityLeftCol];
		if(entityLeftCol >= 1)
			tileNum4 = layer.layerTileNum[entityTopRow][entityRightCol - 1];
		
		if(layer.tiles[tileNum1].collision || layer.tiles[tileNum2].collision || layer.tiles[tileNum3].collision || layer.tiles[tileNum4].collision) {
			entity.CollisionOn = true;
			if(layer.tiles[tileNum1].collision) {
				int tileTop = entityBottomRow * gp.tileSize;
				if(entityTopWorldY <= tileTop) {
					entity.worldY = tileTop - entity.solidAreaDefaultY;
				}
			}
			if(layer.tiles[tileNum2].collision) {
				int tileRight = (entityLeftCol + 1) * gp.tileSize;
				if(entityRightWorldX >= tileRight) {
					entity.worldX = tileRight - entity.solidArea.width - entity.solidAreaDefaultX;
				}
			}
			
			if(layer.tiles[tileNum3].collision) {
				int tileBottom = (entityTopRow + 1)*gp.tileSize;
				if(entityBottomWorldY >= tileBottom) {
					entity.worldY = tileBottom - entity.solidAreaDefaultY - entity.solidArea.height;
				}
			}
			if(layer.tiles[tileNum4].collision) {
				int tileLeft = entityRightCol * gp.tileSize;
				if(entityLeftWorldX <= tileLeft) {
					entity.worldX = tileLeft - entity.solidAreaDefaultX;
				}
			}
		}
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