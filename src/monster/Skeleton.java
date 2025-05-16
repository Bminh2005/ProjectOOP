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
        this.ai = new ChuDongTanCong(gp);
    }

    public void getImage()
    {
        up1 = setup("/monster/skeleton_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/monster/skeleton_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/monster/skeleton_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/monster/skeleton_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/monster/skeleton_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/monster/skeleton_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("/monster/skeleton_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/monster/skeleton_right_2",gp.tileSize,gp.tileSize);
    }
    public void getAttackImage()
    {
        attackUp1 = setup("/monster/skeleton_attack_up_1",gp.tileSize, gp.tileSize );
        attackUp2 = setup("/monster/skeleton_attack_up_2",gp.tileSize, gp.tileSize );
        attackDown1 = setup("/monster/skeleton_attack_down_1",gp.tileSize, gp.tileSize );
        attackDown2 = setup("/monster/skeleton_attack_down_2",gp.tileSize, gp.tileSize );
        attackLeft1 = setup("/monster/skeleton_attack_left_1",gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/monster/skeleton_attack_left_2",gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/monster/skeleton_attack_right_1",gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/monster/skeleton_attack_right_2",gp.tileSize * 2, gp.tileSize);
    }
    @Override
    public void setAction() {
        ai.QuaiVatDuoiTheoPlayer(this);
        ai.attackByTouch(this);
    }

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
