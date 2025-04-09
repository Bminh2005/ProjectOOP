package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean attackPressed, damagePressed, diePressed;
	public boolean runPressed;
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode(); // Get the key code of the pressed key
        if (code == KeyEvent.VK_W) {
            upPressed = true; // Set upPressed to true if 'W' is pressed
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true; // Set downPressed to true if 'S' is pressed
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true; // Set leftPressed to true if 'A' is pressed
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true; // Set rightPressed to true if 'D' is pressed
        }

        // Hanh dong

		if (code == KeyEvent.VK_J)
            attackPressed = true;
     
		if (code == KeyEvent.VK_I)
            damagePressed = true;
		
		if (code == KeyEvent.VK_K)
            diePressed = true;
		if (code == KeyEvent.VK_SHIFT) {
			runPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		 int code = e.getKeyCode(); // Get the key code of the released key
	        if (code == KeyEvent.VK_W) {
	            upPressed = false; // Set upPressed to false if 'W' is released
	        }
	        if (code == KeyEvent.VK_S) {
	            downPressed = false; // Set downPressed to false if 'S' is released
	        }
	        if (code == KeyEvent.VK_A) {
	            leftPressed = false; // Set leftPressed to false if 'A' is released
	        }
	        if (code == KeyEvent.VK_D) {
	            rightPressed = false; // Set rightPressed to false if 'D' is released
	        }
	            
	        if (code == KeyEvent.VK_I) {
	        	//damagePressed = false;
	        }
	            
	        if (code == KeyEvent.VK_K) {
	        	diePressed = false;
	        }
	        
	        if (code == KeyEvent.VK_SHIFT) {
				runPressed = false;
			}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
