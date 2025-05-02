package monster;

import entity.Entity;
import main.GamePanel;


import java.util.Random;

public class Slime extends Monster {

    public Slime(GamePanel gp) {
        super(gp);
        this.gp = gp;

        // Thông tin quái vật
        name = "Green Slime";
        direction = "up";
        type = type_monster;
        speed = 1;
        maxHp = 5;
        hp = maxHp;
        attack = 5;
        defense = 0;
        exp = 2;
        //projectile = new OBJ_Rock(gp);

        //solidArea.setBounds(3, 18, 42, 30); // Cập nhật vung va cham

        getImage(); // Lấy ảnh cho quái vật
    }

    public void getImage() {
        up1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
//        actionLockCounter++;
//        if (actionLockCounter == 120) {
//            Random random = new Random();
//            int i = random.nextInt(100) + 1;
//
//            if (i <= 25) {
//                direction = "up";
//            } else if (i <= 50) {
//                direction = "down";
//            } else if (i <= 75) {
//                direction = "left";
//            } else {
//                direction = "right";
//            }
//            actionLockCounter = 0;
//        }
//
//        int i = new Random().nextInt(100) + 1;
//        if (i > 99 && projectile.alive == false && shotAvailableCounter == 30) {
//            projectile.set(worldX, worldY, direction, true, this);
//            gp.projectileList.add(projectile);
//            shotAvailableCounter = 0;
//        }
    }

    @Override
    public void checkDrop() {
//        int i = new Random().nextInt(100) + 1;
//
//        if (i < 50) {
//            dropItem(new OBJ_Coin_Bronze(gp));
//        } else if (i < 75) {
//            dropItem(new OBJ_Heart(gp));
//        } else {
//            dropItem(new OBJ_ManaCrystal(gp));
//        }
    }
}
