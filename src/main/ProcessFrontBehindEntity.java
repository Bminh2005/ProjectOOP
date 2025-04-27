package main;

import java.awt.Graphics2D;

import entity.Entity;
import map.MapLayer;
import map.MapTile;

public class ProcessFrontBehindEntity {
	private MapLayer layer;
	private Entity entity;
	
	
	public ProcessFrontBehindEntity(MapLayer layer, Entity entity) {
		super();
		this.layer = layer;
		this.entity = entity;
	}


	public void draw(GamePanel gp,Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x, y;
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			x = col*gp.tileSize - gp.player.worldX + gp.player.x;
			y = row*gp.tileSize - gp.player.worldY + gp.player.y;
			int tileNum = layer.layerTileNum[row][col];
			if(tileNum != 0) {
				if(x >= -2*gp.tileSize && x <= gp.screenWidth + 2*gp.tileSize &&
						y >= -2* gp.tileSize && y <= gp.screenHeight + 2*gp.tileSize) {
					if(entity.y > y + layer.tiles[tileNum].getHeight()) {
						entity.draw(g2);
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

