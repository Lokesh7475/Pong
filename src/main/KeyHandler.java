package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean upPressed02, downPressed02, leftPressed02, rightPressed02;
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
//		if(code == KeyEvent.VK_A) {
//			leftPressed = true;
//		}
//		if(code == KeyEvent.VK_D) {
//			rightPressed = true;
//		}
		
		if(code == KeyEvent.VK_UP) {
			upPressed02 = true;
		}
		if(code == KeyEvent.VK_DOWN) {
			downPressed02 = true;
		}
//		if(code == KeyEvent.VK_LEFT) {
//			leftPressed02 = true;
//		}
//		if(code == KeyEvent.VK_RIGHT) {
//			rightPressed02 = true;
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
//		if(code == KeyEvent.VK_A) {
//			leftPressed = false;
//		}
//		if(code == KeyEvent.VK_D) {
//			rightPressed = false;
//		}
		
		if(code == KeyEvent.VK_UP) {
			upPressed02 = false;
		}
		if(code == KeyEvent.VK_DOWN) {
			downPressed02 = false;
		}
//		if(code == KeyEvent.VK_LEFT) {
//			leftPressed02 = false;
//		}
//		if(code == KeyEvent.VK_RIGHT) {
//			rightPressed02 = false;
//		}
	}

}
