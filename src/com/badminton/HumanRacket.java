package com.badminton;

import java.awt.Color;
import java.awt.Graphics;

public class HumanRacket implements Racket
{
	int x;
	double y;
	double yVel;
	boolean upAccel;
	boolean downAccel;
	final double GRAVITY = 0.94;
	int player;

	public HumanRacket(int player)
	{
		upAccel = false; 
		downAccel = false;
		y = 210; 
		yVel = 0;

		if(player == 1)
		{
			x = 20;
		}
		else
		{
			x = 660;
		}
	}

	public void draw(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillRect(x, (int)y, 20, 80);
	}

	public void move() 
	{
		if(upAccel)
		{
			yVel -= 2;
		}
		else if(downAccel)
		{
			yVel += 2;
		}
		else if(!upAccel && !downAccel)
		{
			yVel *= GRAVITY;
		}

		y += yVel;

		if(y < 0)
		{
			y = 0;
		}
		
		if(y > 420)
		{
			y = 420;
		}
	}

	public void setUpAccel(boolean input)
	{
		upAccel = input;
	}

	public void setDownAccel(boolean input)
	{
		downAccel = input;
	}

	public int getY() 
	{
		return (int)y;
	}
}
