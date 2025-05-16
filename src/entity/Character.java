package entity;

import java.awt.image.BufferedImage;

import main.GamePanel;

public class Character extends Entity{
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage attackUp1,attackUp2, attackDown1,attackDown2,attackLeft1,attackLeft2,attackRight1,attackRight2;
	public int hp, maxHp;
	public int mp, maxMp;
	public int attack;
	public int defense;
	public int strength;
	public int speed;
	public Character(GamePanel gp) {
		super(gp);
	}
	
}
