package com.badminton;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Badminton extends Applet implements Runnable, KeyListener{

	final int WIDTH = 700, HEIGHT = 500;
	Thread thread;
	HumanRacket p1;
	AIRacket p2;
	Ball b1;
	boolean gameStarted;
	Graphics gfx;
	Image img;

	/*
	 * INIT
	 */
	public void init(){
		this.resize(WIDTH, HEIGHT);
		gameStarted = false;
		this.addKeyListener(this);
		p1 = new HumanRacket(1);
		b1 = new Ball();
		p2 = new AIRacket(2, b1);
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
		if(b1.getX() < -10 || b1.getX() > 710){
			gfx.setColor(Color.red);
			gfx.drawString("GAME OVER", 350, 250);
		}else{
			p1.draw(gfx);
			b1.draw(gfx);
			p2.draw(gfx);
		}
		if(!gameStarted){
			gfx.setColor(Color.white);
			gfx.drawString("BADMINTON", 340, 100);
			gfx.drawString("Press Enter to Begin...", 310, 130);
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
				b1.checkRacketCollision(p1, p2);
			}

			repaint();
			try {
				Thread.sleep(10);
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
		}else if(e.getKeyCode() == KeyEvent.VK_ENTER){
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
