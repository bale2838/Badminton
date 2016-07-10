package com.badminton;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Badminton extends Applet implements Runnable, KeyListener{

	private final int WIDTH = 700, HEIGHT = 500;
	private Thread thread;
	private HumanRacket p1;
	private AIRacket p2;
	private Ball b1;
	private boolean gameStarted;
	private Graphics gfx;
	private Image img;
	private int score;
	private Image title;
	private Image by;
	private Image pressSpace;
	private int refreshRate;
	//private HighScore highScoreList;

	/*
	 * INIT
	 */
	public void init(){
		this.resize(WIDTH, HEIGHT);
		gameStarted = false;
		//highScoreList = new HighScore();
		//score = 0;
		refreshRate = 10;
		p1 = new HumanRacket(1);
		b1 = new Ball();
		p2 = new AIRacket(2, b1);
		this.addKeyListener(this);
		title = getImage(getCodeBase(), "title.jpg");
		by =  getImage(getCodeBase(), "by_minh_and_teo.jpg");
		pressSpace = getImage(getCodeBase(), "press_space.jpg");
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
	}

	/*
	 * PAINT
	 */
	public void paint(Graphics g){
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		gfx.setColor(Color.white);
		//gfx.drawString("SCORE: " + score, 600, 20);
		//gfx.drawString("x: " + b1.getX(), 600, 30);
		//gfx.drawString("y: " + b1.getY(), 600, 40);

		if(b1.getX() < -10 || b1.getX() > 710){
			gfx.setColor(Color.red);
			gfx.drawString("GAME OVER", 200, 250);
			b1.xVel = 0;//prevent score from inc
			b1.yVel = 0;//prevent score from inc
			//FIXME: Add scoring system
			//highScoreList.addScore("MAD", 500);
		}else{
			p1.draw(gfx);
			b1.draw(gfx);
			p2.draw(gfx);
		}
		if(!gameStarted){
			if(title != null && by != null && pressSpace != null){
				//FIXME: Make title float
				gfx.drawImage(title, 225, 100, this);
				gfx.drawImage(by, 315, 160, this);
				gfx.drawImage(pressSpace, 300, 315, this);

			}
			//gfx.setColor(Color.white);
			//gfx.drawString("BADMINTON", 340, 100);
			//gfx.drawString("Press Enter to Begin...", 310, 185);
		}

		g.drawImage(img, 0, 0, this);
	}

	/*
	 * UPDATE
	 */
	public void update(Graphics g){
		paint(g);
	}

	/*
	 * RUN
	 */
	public void run() {
		for(;;){
			if(gameStarted){
				p1.move();
				b1.move();
				p2.move();
				if(b1.checkRacketCollision(p1, p2)){
					//FIXME: Add sounds
					//play(getCodeBase(), "hit.wav");
					if(b1.getX() <= 50){
						score++;
					}
				}
			}

			repaint();
			try {
				Thread.sleep(refreshRate);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}



	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			p1.setUpAccel(true);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			p1.setDownAccel(true);
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			gameStarted = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			p1.setUpAccel(false);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			p1.setDownAccel(false);
		}
	}

	public void keyTyped(KeyEvent arg0) {

	}
}
