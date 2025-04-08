package map;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class TempMap {
	Color color;
	GamePanel gp;
	public TempMap(Color color, GamePanel gp) {
		this.color = color;
		this.gp = gp;
	}
	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.fillRect(-gp.player.worldX + gp.player.x, -gp.player.worldY + gp.player.y, 500, 500);
	}
}
