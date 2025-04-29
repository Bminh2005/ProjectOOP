package monster;

import java.awt.Graphics2D;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import main.SpriteSheet;
//import object.OBJ_Coin_Bronze;
//import object.OBJ_Heart;
//import object.OBJ_ManaCrystal;

public class Skeleton extends Monster {

    private SpriteSheet skeletonWalk;
    private SpriteSheet skeletonAttack[];
    private SpriteSheet skeletonDeath;

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

        skeletonAttack = new SpriteSheet[4];  
        getImage();  
    }

    public void getImage() {
        // walk
        skeletonWalk = new SpriteSheet("/monster/skeleton_walk.png", 768, 84, 8, 21, 23, 53, 40);

        // attack
        skeletonAttack[0] = new SpriteSheet("/monster/skeleton_attack.png", 576, 84, 6, 21, 23, 53, 40);
        
        //death
        skeletonDeath = new SpriteSheet("/monster/skeleton_death.png", 1152, 84, 12, 21, 23, 53, 40);
    }
    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            int i = new java.util.Random().nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }

            if (isPlayerInRange()) {
                attackPlayer(); 
            }

            actionLockCounter = 0;
        }
    }
// check vị trí player
    public boolean isPlayerInRange() {
        int playerX = gp.player.worldX;
        int playerY = gp.player.worldY;
        int distance = Math.abs(worldX - playerX) + Math.abs(worldY - playerY);

        return distance < 50;
    }

    public void attackPlayer() {
        gp.player.hp -= attack;
        System.out.println("Skeleton attacks Player! Player's HP: " + gp.player.hp);
    }

@Override
public void checkDrop() {
    Random random = new Random();
    int i = random.nextInt(100) + 1;

    if (i < 50) { 
        dropItem(new OBJ_Coin_Bronze(gp));
        dropItem(new OBJ_Coin_Bronze(gp)); // drop 2 coins
    } else if (i < 80) { 
        dropItem(new OBJ_Heart(gp));
    } else { 
        dropItem(new OBJ_ManaCrystal(gp));
    }
  }
}