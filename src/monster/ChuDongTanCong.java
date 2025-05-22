package monster;

import java.awt.Rectangle;

import entity.Entity;
import entity.Player;
import entity.Character;
import main.GamePanel;

public class ChuDongTanCong {
	GamePanel gp;
	
	public ChuDongTanCong(GamePanel gp) {
		this.gp = gp;
	}
	public void attackByTouch(Monster monster) {
		int playerLeftX = gp.player.worldX + gp.player.solidAreaDefaultX;
		int playerTopY = gp.player.worldY + gp.player.solidAreaDefaultY;
		gp.player.solidArea.x = playerLeftX;
		gp.player.solidArea.y = playerTopY;
		monster.solidArea.x = monster.worldX + monster.solidAreaDefaultX;
		monster.solidArea.y = monster.worldY + monster.solidAreaDefaultY;
		if((monster.solidArea.intersects(gp.player.solidArea)) && (gp.player.invincible == false)) {
			gp.player.invincible = true;
			gp.player.takeDamge(10);
		}
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		monster.solidArea.x = monster.solidAreaDefaultX;
		monster.solidArea.y = monster.solidAreaDefaultY;
	}
	public void QuaiVatDuoiTheoPlayer(Character monster) {
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
				monster.direction = "right";
			}
			else if(monster.worldX > player.worldX + 15) {
				monster.direction = "left";
			}
			else if(monster.worldY < player.worldY - 10) {
				monster.direction = "down";
			}
			else if(monster.worldY > player.worldY + 10) {
				monster.direction = "up";
			}
		}
	}
}
