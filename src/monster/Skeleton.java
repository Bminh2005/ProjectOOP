package monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import entity.Player;
import main.GamePanel;
import main.SpriteSheet;

public class Skeleton extends Monster {
    private ChuDongTanCong ai;

    private int actionLockCounter = 0;
    
    public Skeleton(GamePanel gp) {
        super(gp);
        this.gp = gp;

        // Thông tin quái vật
        name = "Skeleton";
        speed = 2;
        maxHp = 10;
        hp = maxHp;
        attack = 5;
        defense = 2;
        exp = 5;
        alive = true;
        getImage();
        getAttackImage();
        this.ai = gp.quaiVatTanCong;
    }

    public void getImage()
    {
        up1 = setup("/monster/skeleton_up_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        up2 = setup("/monster/skeleton_up_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        down1 = setup("/monster/skeleton_down_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        down2 = setup("/monster/skeleton_down_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        left1 = setup("/monster/skeleton_left_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        left2 = setup("/monster/skeleton_left_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        right1 = setup("/monster/skeleton_right_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        right2 = setup("/monster/skeleton_right_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
    }
    public void getAttackImage()
    {
        attackUp1 = setup("/monster/skeleton_attack_up_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        attackUp2 = setup("/monster/skeleton_attack_up_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        attackDown1 = setup("/monster/skeleton_attack_down_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        attackDown2 = setup("/monster/skeleton_attack_down_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        attackLeft1 = setup("/monster/skeleton_attack_left_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        attackLeft2 = setup("/monster/skeleton_attack_left_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
        attackRight1 = setup("/monster/skeleton_attack_right_1", 3*gp.tileSize/4, 3*gp.tileSize/4);
        attackRight2 = setup("/monster/skeleton_attack_right_2", 3*gp.tileSize/4, 3*gp.tileSize/4);
    }
    @Override
    public void setAction() {
    	 actionLockCounter++;
         
         
         if (actionLockCounter == 120) {
             Random random = new Random();
             int i = random.nextInt(100) + 1;

             if (i <= 25) {
                 direction = "up";
             } else if (i <= 50) {
                 direction = "down";
             } else if (i <= 75) {
                 direction = "left";
             } else {
                 direction = "right";
             }
             actionLockCounter = 0;
         }
        ai.QuaiVatDuoiTheoPlayer(this);
        ai.attackByTouch(this);
    }

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
