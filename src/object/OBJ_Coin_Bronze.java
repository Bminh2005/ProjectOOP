package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Bronze extends Entity {

    GamePanel gp;

    public OBJ_Coin_Bronze(GamePanel gp) {
        this.gp = gp;

        type = type_pickUpOnly;
        name = "Bronze Coin";
        value = 1; 

        down1 = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize);
        image = down1;
    }

    public void use(Entity entity) {
        gp.ui.addMessage("Gold + " + value);
        gp.player.coin += value;
    }
}
