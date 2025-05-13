package main;

import java.awt.Graphics2D;

import entity.Entity;
import map.MapLayer;
import map.MapTile;

public class ProcessFrontBehindEntity {
	GamePanel gp;
	public ProcessFrontBehindEntity(GamePanel gp) {
		this.gp = gp;
	}


	public void draw(Graphics2D g2, MapLayer layer, Entity entity) {
		int col = 0;
		int row = 0;
		int x, y;
		int worldX, worldY;
		entity.draw(g2);
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			worldX = col*gp.tileSize;
			worldY = row*gp.tileSize;
			x = col*gp.tileSize - gp.player.worldX + gp.player.x;
			y = row*gp.tileSize - gp.player.worldY + gp.player.y;
			int tileNum = layer.layerTileNum[row][col];
			if(tileNum != 0) {
				
				if(x >= -2*gp.tileSize && x <= gp.screenWidth + 2*gp.tileSize &&
						y >= -2* gp.tileSize && y <= gp.screenHeight + 2*gp.tileSize) {
					
					if(entity.worldY + entity.height < worldY + layer.tiles[tileNum].getHeight()) {
						layer.tiles[tileNum].draw(g2, x, y);
					}
					else {
						layer.tiles[tileNum].draw(g2, x, y);
						entity.draw(g2);
					}
				}
				
			}
			col++;
			if(col >= gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
}

