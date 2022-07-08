package main;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable{
	
	private GameScreen gameScreen;
	
	private BufferedImage img;

	private long lastUpdate;
	private double timePerUpdate;
	
	private int updates;
	private long lastTimeUPS;
	
	private Thread gameThread;

	public Game() {
		
		timePerUpdate = 1000000000.0 /60.0;

		
		importImg();
		
		setSize(640, 640);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		gameScreen = new GameScreen(img);
		add(gameScreen);
		setVisible(true);

	}
	
	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/spriteatlas.png");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void start() {
		gameThread = new Thread(this);
		
		gameThread.start();
	}
	
	private void callUPS() {
		
		if(System.currentTimeMillis() - lastTimeUPS >= 1000) {
			System.out.println("UPS: " + updates);
			updates = 0;
			lastTimeUPS = System.currentTimeMillis();
		}
	}
	
	private void updateGame() {
		updates++;
		lastUpdate = System.nanoTime();
//		System.out.println("Game Updated!");
	}

	public static void main(String[] args) {
		System.out.println("My tower defence game!");
		
		Game game = new Game();
		game.start();
	}

	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0 /120.0;
		long lastFrame = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();
		
		int frames = 0;
		int updates = 0;

		while (true) {
			
			//Render
			if (System.nanoTime() - lastFrame >= timePerFrame) {
				lastFrame = System.nanoTime();
				repaint();
				frames++;
			}
			//Update
			if(System.nanoTime() - lastUpdate >= timePerUpdate) {
				updateGame();
				updates++;
			}
			
			if(System.currentTimeMillis() - lastTimeCheck >= 1000) {
				System.out.println("FPS: "+ frames + "/UPS: " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
		}
		
	}
	
//	frames++;
//	if(System.currentTimeMillis() - lastTime >= 1000) {
//		System.out.println("FPS: " + frames);
//		frames = 0;
//		lastTime = System.currentTimeMillis();
//	}

}
