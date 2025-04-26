package map;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;

public class MapLayer {
	public MapTitle[] titles;
	private int layerTileNum[][];
	String path;
    public MapLayer(GamePanel gp, String path){
    	this.path = path;
    	titles = new MapTitle[100];
    	layerTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];
    	getImage(gp);
    	loadLayer(gp);
    }
    public void getImage(GamePanel gp) {
    	titles[0] = null;
    	titles[1] = new MapTitle(gp, "/tiles/grand.png");
    	titles[2] = new MapTitle(gp, "/tiles/lane1.png");
    	titles[3] = new MapTitle(gp, "/tiles/lane2.png");
    	titles[4] = new MapTitle(gp, "/tiles/ngatu1.png");
    	titles[5] = new MapTitle(gp, "/tiles/ngatu2.png");
    	titles[6] = new MapTitle(gp, "/tiles/ngatu3.png");
    	titles[7] = new MapTitle(gp, "/tiles/ngatu4.png");
    	titles[8] = new MapTitle(gp, "/tiles/lane11.png");
    	titles[9] = new MapTitle(gp, "/tiles/lane12.png");
    	titles[10] = new MapTitle(gp, "/tiles/tree0.png", 48, 63);
    	titles[11] = new MapTitle(gp, "/tiles/tree1.png");
    	titles[12] = new MapTitle(gp, "/tiles/tree2.png", 28, 45);
    	titles[13] = new MapTitle(gp, "/tiles/rock1.png");
    	titles[14] = new MapTitle(gp, "/tiles/tree3.png", 80, 96);
    	/**
    	
    	titles[15] = new MapTitle(gp, null);
    	titles[16] = new MapTitle(gp, null);
    	titles[17] = new MapTitle(gp, null);
    	titles[18] = new MapTitle(gp, null);
    	titles[19] = new MapTitle(gp, null);
    	titles[20] = new MapTitle(gp, null);
    	titles[21] = new MapTitle(gp, null);
    	titles[22] = new MapTitle(gp, null);
    	titles[23] = new MapTitle(gp, null);
    	titles[24] = new MapTitle(gp, null);
    	titles[25] = new MapTitle(gp, null);
    	titles[26] = new MapTitle(gp, null);
    	titles[27] = new MapTitle(gp, null);
    	titles[28] = new MapTitle(gp, null);
    	titles[29] = new MapTitle(gp, null);
    	titles[30] = new MapTitle(gp, null);
    	titles[31] = new MapTitle(gp, null);
    	titles[32] = new MapTitle(gp, null);
    	titles[33] = new MapTitle(gp, null);
    	titles[34] = new MapTitle(gp, null);
    	titles[35] = new MapTitle(gp, null);
    	titles[36] = new MapTitle(gp, null);
    	titles[37] = new MapTitle(gp, null);
    	titles[38] = new MapTitle(gp, null);
    	titles[39] = new MapTitle(gp, null);
    	titles[40] = new MapTitle(gp, null);
    	titles[41] = new MapTitle(gp, null);
    	titles[42] = new MapTitle(gp, null);
    	titles[43] = new MapTitle(gp, null);
    	titles[44] = new MapTitle(gp, null);
    	titles[45] = new MapTitle(gp, null);
    	titles[46] = new MapTitle(gp, null);
    	titles[47] = new MapTitle(gp, null);
    	titles[48] = new MapTitle(gp, null);
    	titles[49] = new MapTitle(gp, null);
    	titles[50] = new MapTitle(gp, null);
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
		int x, y, worldX, worldY;
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			x = col*gp.tileSize+ - gp.player.worldX + gp.player.x;
			y = row*gp.tileSize - gp.player.worldY + gp.player.y;
			int titleNum = layerTileNum[row][col];
			if(titleNum != 0) {
				if(x >= -2*gp.tileSize && x <= gp.screenWidth + 2*gp.tileSize &&
						y >= -2* gp.tileSize && y <= gp.screenHeight + 2*gp.tileSize)
				titles[titleNum].draw(g2, x, y);
			}
			col++;
			if(col >= gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}		
	}

}
