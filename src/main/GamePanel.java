package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	Random rn = new Random();
	
	// Screen Settings
	
	final int ORIGINAL_TILE_SIZE = 16;	// 16x16 tile
	final int SCALE = 3;
	
	final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;	// 48x48 tile
	
	final int MAX_SCREEN_COL = 16;
	final int MAX_SCREEN_ROW = 12;
	
//	final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;	//768 pixels
//	final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;	//576 pixels
	
	final int SCREEN_WIDTH = 1400;
	final int SCREEN_HEIGHT = 750;
	
	//FPS
	
	final int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	//	Set player default position
	
	int playerX = 100;
	int playerY = SCREEN_HEIGHT>>1;
	
	int player02X = SCREEN_WIDTH-100;
	int player02Y = SCREEN_HEIGHT>>1;
	
	int ballX = SCREEN_WIDTH>>1;
	int ballY = SCREEN_HEIGHT>>1;
	
	int ballWidth = TILE_SIZE>>1;
	int ballHeight = TILE_SIZE>>1;
	
	int blockWidth = TILE_SIZE>>1;
	int blockHeight = TILE_SIZE<<1;
	
	int partitionWidth = blockWidth;
	int partitionHeight = SCREEN_HEIGHT;
	
	int partitionX = SCREEN_WIDTH>>1;
	int partitionY = 0;
	
	int scorePlayer = 0;
	int scorePlayer02 = 0;
	
	int playerSpeed = 5;
	int ballXSpeed = 10;
	int ballYSpeed = 0;
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);	
		this.setDoubleBuffered(true);		// to improve performance
		
		this.addKeyListener(keyH);
	
		this.setFocusable(true);
			
	}
	
	public void startGameThread(){
		gameThread = new Thread(this);
		gameThread.start();
	}

//	@Override
//	public void run() {
//		
//		double drawInterval = 1000000000/FPS;	// 0.01666 seconds
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		while(gameThread != null) {
//			
//			
//			// 1. UPDATE : Update information such as character positions
//			update();
//			
//			// 2. DRAW : draw the screen with updated information
//			repaint();	// to call paintComponent method
//			
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				Thread.sleep((long)remainingTime/1000000);
//				
//				nextDrawTime += drawInterval;
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
	
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double deltaTime = 0;
		
		long lastTime = System.nanoTime();
		long currentTime;
		
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			deltaTime += (currentTime - lastTime)/drawInterval;
			timer +=(currentTime - lastTime);
			lastTime = currentTime; 
			
			if(deltaTime >= 1) {
				update(deltaTime);
				
				repaint();
				
				deltaTime--;
				drawCount++;
				
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS = "+drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
	}
	
	public void update(double dt) {
		if(keyH.upPressed && playerY > 0) {
			playerY -= playerSpeed*dt;
		}
		else if(keyH.downPressed && (playerY + blockHeight) < SCREEN_HEIGHT) {
			playerY += playerSpeed*dt;
		}
//		else if(keyH.rightPressed) {
//			playerX += playerSpeed;
//		}
//		else if(keyH.leftPressed) {
//			playerX -= playerSpeed;
//		}
		
		if(keyH.upPressed02 && player02Y > 0) {
			player02Y -= playerSpeed*dt;
		}
		else if(keyH.downPressed02 && (player02Y + blockHeight) < SCREEN_HEIGHT) {
			player02Y += playerSpeed*dt;
		}
//		else if(keyH.rightPressed02) {
//			player02X += playerSpeed;
//		}
//		else if(keyH.leftPressed02) {
//			player02X -= playerSpeed;
//		}
		
		
		if((ballX < (playerX + blockWidth) && ballX > playerX) && (ballY < (playerY + blockHeight) && (ballY + ballWidth) > playerY)) {
			ballXSpeed *= -1;
			ballYSpeed += 1;
		}
		else if(((ballX+(ballWidth)) >= (player02X) && ballX < (player02X + blockWidth)) && (ballY < (player02Y + blockHeight) && (ballY + ballWidth) > player02Y)) {
			ballXSpeed *= -1;
			ballYSpeed += 1;
		}
		
		ballX += ballXSpeed*dt;
		ballY += ballYSpeed*dt;
		
		if(ballX <  playerX) {
			ballX = SCREEN_WIDTH>>1;
			ballY = SCREEN_HEIGHT>>1;
			

			ballYSpeed = 3;

			if(rn.nextInt(2) == 1)
				ballXSpeed *= -1;
			if(rn.nextInt(2) == 1)
				ballYSpeed *= -1;
			scorePlayer02++;
			
			
			
		}
		else if(ballX > player02X) {
			ballX = SCREEN_WIDTH>>1;
			ballY = SCREEN_HEIGHT>>1;
			
			ballYSpeed = 3;

			if(rn.nextInt(2) == 1)
				ballXSpeed *= -1;
			if(rn.nextInt(2) == 1)
				ballYSpeed *= -1;
			
			scorePlayer++;
			
		}
		
		if((ballY+ballHeight) >= SCREEN_HEIGHT) {
			ballYSpeed *= -1;
		}
		else if(ballY <= 0) {
			ballYSpeed *= -1;
		}
	}
	
	public void paintComponent(Graphics g) {	// this is built in method in java
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;	// Graphics 2d has more functions

		g2.setColor(Color.RED);
		
		g2.fillRect(partitionX, partitionY, partitionWidth, partitionHeight);
		
		g2.setColor(Color.YELLOW);
		
		g2.drawString("Player 1 Score : "+scorePlayer, 200, 50);
		g2.drawString("Player 2 Score : "+scorePlayer02, SCREEN_WIDTH-200, 50);
		
		g2.setColor(Color.CYAN);
		
		g2.fillRect(playerX, playerY, blockWidth, blockHeight);
		g2.fillOval(ballX, ballY, ballWidth, ballHeight);
		g2.fillRect(player02X, player02Y, blockWidth, blockHeight);
		
		
		g2.dispose();	// to save memory
	}
}
