package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import main.SpriteSheet;

public class Player extends Entity{
	KeyHandler keyH;
    int speed;
    public String state;
    private SpriteSheet playerIdle;
    private SpriteSheet playerWalk;
    private SpriteSheet playerAttack[];
    private SpriteSheet playerHurt;
    private SpriteSheet playerRun;
    private SpriteSheet playerDefend;
    private SpriteSheet playerDying;
    private int spriteNum;
    private int frameCounter;
    private boolean flip;
    private int attackType;
    private int comboAttackDelayTime;//don vi frames
    private boolean runningCountAttackDelay;
    
    
    public Player(GamePanel gp, KeyHandler keyH) {
        this.keyH = keyH;
        this.gp = gp;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
    	this.hp = 100;
		this.mp = 100;
		this.state = "NORMAL";
		this.worldX = 24* gp.tileSize;
		this.worldY = 24* gp.tileSize;
		this.speed = 3;
		this.frameCounter = 0;
		this.flip = false;
		this.attackType = 0;
		this.comboAttackDelayTime = 0;
		this.x = (gp.screenWidth/2) - (gp.tileSize/2);
		this.y = (gp.screenHeight/2) - (gp.tileSize/2);
		this.playerAttack = new SpriteSheet[3];
		this.runningCountAttackDelay = false;
		this.height = gp.tileSize;
		this.width = gp.tileSize * 53/40;
		this.solidArea = new Rectangle(17, 23, 20, 17);
		this.CollisionOn = false;
		this.direction = "up";
    }
    public void getPlayerImage() {
    	playerIdle = new SpriteSheet("/Player/IDLE.png", 672, 84, 7, 21, 23, 53, 40);
    	playerWalk = new SpriteSheet("/Player/WALK.png", 768, 84, 8, 21, 23, 53, 40);
    	playerAttack[0] = new SpriteSheet("/Player/ATTACK1.png", 576, 84, 6, 21, 23, 53, 40);
    	playerAttack[1] = new SpriteSheet("/Player/ATTACK2.png", 480, 84, 5, 21, 23, 53, 40);
    	playerAttack[2] = new SpriteSheet("/Player/ATTACK3.png", 576, 84, 6, 21, 23, 53, 40);
    	playerHurt = new SpriteSheet("/Player/HURT.png", 384, 84, 4, 21, 23, 53, 40);
    	playerRun = new SpriteSheet("/Player/RUN.png", 768, 84, 8, 21, 23, 53, 40);
    	playerDefend = new SpriteSheet("/Player/DEFEND.png", 576, 84, 6, 21, 23, 53, 40); //0->1 gio khien, 2->5 do don
    	playerDying = new SpriteSheet("/Player/DEATH.png", 1152, 84, 12, 21, 23, 53, 40);
    }
    public void update() {
    	
    	if(this.runningCountAttackDelay) {
    		this.comboAttackDelayTime++;
    		if(this.comboAttackDelayTime == 20) {
    			this.attackType = 0;
    		}
    	}
    	if(hp > 0) {
    	if(this.keyH.upPressed == true || this.keyH.downPressed == true || this.keyH.leftPressed == true || this.keyH.rightPressed == true) {
    		if(this.keyH.runPressed) {
    			this.run();
    		}
    		else {
    			this.walk();
    		}
    		this.move();
    	}
    	else if(this.keyH.attackPressed == true) {
    		this.runningCountAttackDelay = false;
    		this.comboAttackDelayTime = 0;
    		this.attack();
    		if(this.spriteNum == this.playerAttack[attackType].maxNumber) {
    			this.keyH.attackPressed = false;
    			this.attackType = (this.attackType + 1)%3;
    			this.runningCountAttackDelay = true;
    			this.idle();
    		}
    	}
    	else if(this.keyH.damagePressed == true) {
    		this.hurt();
    		if(this.spriteNum == this.playerHurt.maxNumber) {
    			this.keyH.damagePressed = false;
    			this.hp -= 5;
    			this.idle();
    		}
    	}
    	else {
    		this.idle();
    	}
    	}
    	else{
    		this.dying();
    		if(state.equals("DYING") == false) {
    			state = "DYING";
    		}
    	}
    	this.frameCounter++;
    	
    }
    
    public void idle() {
    	if(this.state.equals("IDLE") == false) {
    		this.spriteNum = this.playerIdle.maxNumber - 1;
    	}
    	this.state = "IDLE";
    	if(this.frameCounter%5 == 0) {
    		this.spriteNum = (this.spriteNum + 1)%this.playerIdle.maxNumber;
    	}
    	
    }
    
    public void run() {
    	if(this.state.equals("RUN") == false) {
    		this.spriteNum = this.playerRun.maxNumber -1;
    		this.frameCounter = 0;
    		this.speed = 5;
    	}
    	System.out.println("Player is running!");
    	this.state ="RUN";
    	if(this.frameCounter%5 == 0) {
    		this.spriteNum = (this.spriteNum + 1)%this.playerRun.maxNumber;
    	}
    	
    }
    public void walk() {
    	if(this.state.equals("WALK") == false) {
    		this.spriteNum = this.playerWalk.maxNumber - 1;
    		this.frameCounter = 0;
    		this.speed = 3;
    	}
    	System.out.println("Player is walking!");
    	this.state ="WALK";
    	if(this.frameCounter%5 == 0) {
    		this.spriteNum = (this.spriteNum + 1)%this.playerWalk.maxNumber;
    	}
    	
    }
    public void move() {
    	boolean overx = this.worldX >= gp.maxWorldWidth - (gp.screenWidth + this.width)*10/20 || this.worldX <= (gp.screenWidth - this.width)*10/20;
    	boolean overy = this.worldY >= gp.maxWorldHeight - (gp.screenHeight + this.height)*10/20 || this.worldY <= (gp.screenHeight - this.height)*10/20;
    	if(this.keyH.upPressed == true) {
	    	direction = "up";
	    	this.CollisionOn = false;
		    gp.cChecker.checkTile(this);
	    	if(!this.CollisionOn) {
	    	if(overy) this.y -= this.speed;
	    	this.worldY -= this.speed;
	    	}
	    }
	    if(this.keyH.downPressed == true) {
	    	direction = "down";
	    	this.CollisionOn = false;
		    gp.cChecker.checkTile(this);
	    	if(!this.CollisionOn) {
	    	if(overy) this.y += this.speed;
	    	this.worldY += this.speed;
	    	}
	    }
	    if(this.keyH.leftPressed == true) {
	    	direction = "left";
	    	this.CollisionOn = false;
		    gp.cChecker.checkTile(this);
	    	if(!this.CollisionOn) {
	    	if(overx) this.x -= this.speed;
	    	this.worldX -= this.speed;
	    	this.flip = true;
	    	}
	    	
	    }
	    if(this.keyH.rightPressed == true) {
	    	direction = "right";
	    	this.CollisionOn = false;
		    gp.cChecker.checkTile(this);
	    	if(!this.CollisionOn) {
	    	if(overx) this.x += this.speed;
	    	this.worldX += this.speed;
	    	this.flip = false;
	    	}
	    }
	    
    }
    	//System.out.println(this.worldX +" "+ this.worldY);
    
    public void attack() {
    	if(this.state.equals("ATTACKING") == false) {
    		this.spriteNum = -1;
    		this.frameCounter = 0;
    	}
    	System.out.println("Player is attacking!");
		this.state = "ATTACKING";
		if(this.frameCounter%20 == 0) {
			if(flip) this.worldX -= 1;
			else this.worldX += 1;
		}
		if(this.frameCounter%5 == 0) {
			//System.out.println(this.spriteNum);
			this.spriteNum++;
		}
    }
    public void hurt() {
    	System.out.println("Player is injured!");
    	if(this.state.equals("HURT") == false) {
    		this.spriteNum = -1;
    		this.frameCounter = 0;
    	}
    	if(this.flip) this.worldX += 1;
    	else this.worldX -= 1;
    	System.out.println("HP = " + this.hp);
    	this.state = "HURT";
    	if(this.frameCounter%5 == 0) {
    		this.spriteNum++;
		}
    }
    
    public void dying() {
    	if(this.state.equals("DYING") == false) {
    		this.spriteNum = -1;
    		this.frameCounter = 0;
    	}
    	System.out.println("Player is died!");
    	this.state = "DYING";
    	if(this.frameCounter%10 == 0) {
    		if(this.spriteNum < this.playerDying.maxNumber - 1) {
    			this.spriteNum++;
    		}
		}
    	
    }
    
    public void draw(Graphics2D g2) {
        switch (state) {
        case "IDLE":
        	this.image = this.playerIdle.animation[this.spriteNum];
        	break;
        case "WALK":
        	this.image = this.playerWalk.animation[this.spriteNum];
        	break;
        case "ATTACKING":
        	this.image = this.playerAttack[this.attackType].animation[this.spriteNum];
        	break;
        case "DYING":
        	this.image = this.playerDying.animation[this.spriteNum];
        	break;
        case "HURT":
        	this.image = this.playerHurt.animation[this.spriteNum];
        	break;
        case "RUN":
        	this.image = this.playerRun.animation[this.spriteNum];
        	break;
        }
        if(flip) {
        	g2.drawImage(image, x + gp.tileSize*53/40, y, -this.width, this.height, null);
        }
        else {
        	g2.drawImage(image, x, y, this.width, this.height, null);
        }
        // Display the player's current state as text
        //g2.setColor(Color.BLACK);
        //g2.drawString("State: " + state, x, y - 10);
    }
}
