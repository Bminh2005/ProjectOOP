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
    	getImage(gp);
    	loadLayer(gp);
    }
    public void getImage(GamePanel gp) {
    	titles[0] = new MapTitle(gp, null);
    	titles[1] = new MapTitle(gp, null);
    	titles[2] = new MapTitle(gp, null);
    	titles[3] = new MapTitle(gp, null);
    	titles[4] = new MapTitle(gp, null);
    	titles[5] = new MapTitle(gp, null);
    	titles[6] = new MapTitle(gp, null);
    	titles[7] = new MapTitle(gp, null);
    	titles[8] = new MapTitle(gp, null);
    	titles[9] = new MapTitle(gp, null);
    	titles[10] = new MapTitle(gp, null);
    	titles[11] = new MapTitle(gp, null);
    	titles[12] = new MapTitle(gp, null);
    	titles[13] = new MapTitle(gp, null);
    	titles[14] = new MapTitle(gp, null);
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
    }
    public void loadLayer(GamePanel gp) {
		try {
			InputStream is = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			while (col < gp.maxScreenCol && row < gp.maxScreenRow ) {
				String line = br.readLine();
				String numbers[] = line.split(" ");
				while (col < gp.maxScreenCol) {
					int num = Integer.parseInt(numbers[col]);
					layerTileNum[row][col] = num;
					col++;
				}
				if (col >= gp.maxScreenCol) {
					col = 0;
				}
				row++;
			}
			br.close();
		} catch (Exception e) {
			
		}
	}
    public void draw(GamePanel gp,Graphics2D g2, int x0, int y0) {
    	int col = 0;
		int row = 0;
		int x, y;
		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
			x = col*gp.tileSize - gp.player.worldX + gp.player.x + x0;
			y = row*gp.tileSize - gp.player.worldY + gp.player.y + y0;
			int titleNum = layerTileNum[row][col];
			titles[titleNum].draw(g2, x, y);
			col++;
			if(col == gp.maxScreenCol) {
				col = 0;
				row++;
			}
		}		
	}
}
