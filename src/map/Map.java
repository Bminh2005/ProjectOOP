package map;

import java.awt.Graphics2D;

import main.GamePanel;

public class Map {
	private GamePanel gp;
	private MapLayer layer1;
	private MapLayer layer2;
	int x0, y0;
	public Map(GamePanel gp,String path1, String path2, int x0, int y0) {
		this.gp = gp;
		layer1 = new MapLayer(gp, path1);
		layer2 = new MapLayer(gp, path2);
		this.x0 = x0;
		this.y0 = y0;
	}
	
	public void draw(Graphics2D g2) {
		layer1.draw(gp, g2, x0, y0);
		layer2.draw(gp, g2, x0, y0);
	}
}
