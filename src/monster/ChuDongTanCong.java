package monster;

import entity.Entity;
import entity.Player;
import main.GamePanel;

public class ChuDongTanCong {
	private GamePanel gp;
	
	public ChuDongTanCong(GamePanel gp) {
		this.gp = gp;
	}
	
	public void QuaiVatDuoiTheoPlayer(Entity monster) {
		boolean tanCong = false;
		int warZoneWidth = 6*gp.tileSize;
		int warZoneHeight = 6*gp.tileSize;
		int safeZoneWidth = 10*gp.tileSize;
		int safeZoneHeight = 10*gp.tileSize;
		Player player = gp.player;
		if(player.worldX <= monster.worldX + warZoneWidth/2 
				&& player.worldX >= monster.worldX - warZoneWidth/2
				&& player.worldY <= monster.worldY + warZoneHeight/2
				&& player.worldY >= monster.worldY - warZoneHeight/2
				) {
			tanCong = true;
		}
		if(player.worldX < monster.worldX - safeZoneWidth/2
				|| player.worldX > monster.worldX + safeZoneWidth/2
				|| player.worldY > monster.worldY + safeZoneHeight/2
				|| player.worldY < monster.worldY - safeZoneHeight/2) {
			tanCong = false;
		}
		
		if(tanCong) {
			if(monster.worldX < player.worldX - 15) {
				monster.worldX += 3;
			}
			else if(monster.worldX > player.worldX + 15) {
				monster.worldX -= 3;
			}
			if(monster.worldY < player.worldY - 10) {
				monster.worldY += 3;
			}
			else if(monster.worldY > player.worldY + 10) {
				monster.worldY -= 3;
			}
		}
	}
}
