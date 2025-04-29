package map;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;

public class MapLayer {
	public MapTile[] tiles;
	public int layerTileNum[][];
	String path;
    public MapLayer(GamePanel gp, String path){
    	this.path = path;
    	tiles = new MapTile[100];
    	layerTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];
    	getImage(gp);
    	loadLayer(gp);
    }
    public void getImage(GamePanel gp) {
    	tiles[0] = null;
    	tiles[1] = new MapTile(gp, "/tiles/grand.png", false);
    	tiles[2] = new MapTile(gp, "/tiles/lane1.png", false);
    	tiles[3] = new MapTile(gp, "/tiles/lane2.png", false);
    	tiles[4] = new MapTile(gp, "/tiles/ngatu1.png", false);
    	tiles[5] = new MapTile(gp, "/tiles/ngatu2.png", false);
    	tiles[6] = new MapTile(gp, "/tiles/ngatu3.png", false);
    	tiles[7] = new MapTile(gp, "/tiles/ngatu4.png", false);
    	tiles[8] = new MapTile(gp, "/tiles/lane11.png", false);
    	tiles[9] = new MapTile(gp, "/tiles/lane12.png", false);
    	tiles[10] = new MapTile(gp, "/tiles/tree0.png", 48, 63, true);
    	tiles[11] = new MapTile(gp, "/tiles/tree1.png", true);
    	tiles[12] = new MapTile(gp, "/tiles/tree2.png", 28, 45, true);
    	tiles[13] = new MapTile(gp, "/tiles/rock1.png", true);
    	tiles[14] = new MapTile(gp, "/tiles/tree3.png", 80, 96, true);
    	tiles[15] = new MapTile(gp, "/tiles/tile006.png", false);
    	//tiles[16] = new MapTile(gp,"tiles/hut.png");
    	/**
    	
    	
    	tiles[16] = new MapTile(gp, null);
    	tiles[17] = new MapTile(gp, null);
    	tiles[18] = new MapTile(gp, null);
    	tiles[19] = new MapTile(gp, null);
    	tiles[20] = new MapTile(gp, null);
    	tiles[21] = new MapTile(gp, null);
    	tiles[22] = new MapTile(gp, null);
    	tiles[23] = new MapTile(gp, null);
    	tiles[24] = new MapTile(gp, null);
    	tiles[25] = new MapTile(gp, null);
    	tiles[26] = new MapTile(gp, null);
    	tiles[27] = new MapTile(gp, null);
    	tiles[28] = new MapTile(gp, null);
    	tiles[29] = new MapTile(gp, null);
    	tiles[30] = new MapTile(gp, null);
    	tiles[31] = new MapTile(gp, null);
    	tiles[32] = new MapTile(gp, null);
    	tiles[33] = new MapTile(gp, null);
    	tiles[34] = new MapTile(gp, null);
    	tiles[35] = new MapTile(gp, null);
    	tiles[36] = new MapTile(gp, null);
    	tiles[37] = new MapTile(gp, null);
    	tiles[38] = new MapTile(gp, null);
    	tiles[39] = new MapTile(gp, null);
    	tiles[40] = new MapTile(gp, null);
    	tiles[41] = new MapTile(gp, null);
    	tiles[42] = new MapTile(gp, null);
    	tiles[43] = new MapTile(gp, null);
    	tiles[44] = new MapTile(gp, null);
    	tiles[45] = new MapTile(gp, null);
    	tiles[46] = new MapTile(gp, null);
    	tiles[47] = new MapTile(gp, null);
    	tiles[48] = new MapTile(gp, null);
    	tiles[49] = new MapTile(gp, null);
    	tiles[50] = new MapTile(gp, null);
    	**/
    }
    public void loadLayer(GamePanel gp) {
		try {
			InputStream is = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			while (col < gp.maxWorldCol && row < gp.maxWorldRow ) {
				String line = br.readLine();
				String numbers[] = line.split(" ");
				while (col < gp.maxWorldCol) {
					int num = Integer.parseInt(numbers[col]);
					layerTileNum[row][col] = num;
					col++;
				}
				if (col >= gp.maxWorldCol) {
					col = 0;
				}
				row++;
			}
			br.close();
		} catch (Exception e) {
			
		}
	}
    public void draw(GamePanel gp,Graphics2D g2) {
    	int col = 0;
		int row = 0;
		int x, y;
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			x = col*gp.tileSize - gp.player.worldX + gp.player.x;
			y = row*gp.tileSize - gp.player.worldY + gp.player.y;
			int titleNum = layerTileNum[row][col];
			if(titleNum != 0) {
				if(x >= -2*gp.tileSize && x <= gp.screenWidth + 2*gp.tileSize &&
						y >= -2* gp.tileSize && y <= gp.screenHeight + 2*gp.tileSize)
				tiles[titleNum].draw(g2, x, y);
			}
			col++;
			if(col >= gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}		
	}

}
