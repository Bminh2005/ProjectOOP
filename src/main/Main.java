package main;

import javax.swing.JFrame;

public class Main {
	public static JFrame window;
	
	public static void main(String[] args) {
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Warrior Adventure");
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		
		gamePanel.startGameThread();
	}
}
