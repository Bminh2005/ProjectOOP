package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	public KeyHandler keyH;
	public GamePanel gp;
    int speed;
    public String state;
    public Color image;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.keyH = keyH;
        this.gp = gp;
        setDefaultValues();
    }
    public void setDefaultValues() {
		this.hp = 100;
		this.mp = 100;
		this.state = "NORMAL";
		this.worldX = 0;
		this.worldY = 0;
		this.speed = 3;
		this.x = (gp.screenWidth/2) - (gp.tileSize/2);
		this.y = (gp.screenHeight/2) - (gp.tileSize/2);
    }
    public void update() {
    	if(this.keyH.upPressed == true || this.keyH.downPressed == true || this.keyH.leftPressed == true || this.keyH.rightPressed == true) {
    		this.move();
    	}
    	else if(this.keyH.attackPressed == true) {
    		this.attack();
    	}
    	else if(this.keyH.damagePressed == true) {
    		this.damaged();
    	}
    	else {
    		this.idle();
    	}
    	if(hp <= 0) {
    		this.dying();
    		if(state.equals("DYING") == false) {
    			state = "DYING";
    		}
    	}
    }
    public void idle() {
    	this.state = "NORMAL";
    }
    public void move() {
    	if(this.keyH.upPressed == true || this.keyH.downPressed == true || this.keyH.leftPressed == true || this.keyH.rightPressed == true) {
    		System.out.println("Player is moving!");
    		this.state ="NORMAL";
    		if(this.keyH.upPressed == true) {
    			this.worldY -= this.speed;
    		}
    		if(this.keyH.downPressed == true) {
    			this.worldY += this.speed;
    		}
    		if(this.keyH.leftPressed == true) {
    			this.worldX -= this.speed;
    		}
    		if(this.keyH.rightPressed == true) {
    			this.worldX += this.speed;
    		}
    	}
    }
    
    public void attack() {
    	System.out.println("Player is attacking!");
		this.state = "ATTACKING";
    }
    public void damaged() {
    	System.out.println("Player is injured!");
    	hp -= 1;
    	System.out.println("HP = " + this.hp);
    	this.state = "DAMAGED";
    }
    
    public void dying() {
    	System.out.println("Player is died!");
    	this.state = "DYING";
    }
    
    public void draw(Graphics2D g2) {
        switch (state) {
        case "NORMAL":
        	this.image = Color.WHITE;
        	break;
        case "ATTACKING":
        	this.image = Color.RED;
        	break;
        case "DYING":
        	if (image.getBlue() >= 0) {
                image = new Color(0,0,Math.max(image.getBlue() - 10, 0));
            }
        	break;
        case "DAMAGED":
        	this.image = Color.BLUE;
        }
        g2.setColor(this.image);
        g2.fillRect(this.worldX, this.worldY, gp.tileSize, gp.tileSize);
        // Display the player's current state as text
        //g2.setColor(Color.BLACK);
        //g2.drawString("State: " + state, x, y - 10);
    }
}
