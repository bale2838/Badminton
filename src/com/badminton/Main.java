package com.badminton;

public class Main {

	public static void main(String[] args) {
		HighScore hs = new HighScore();
		hs.addScore("BLAH", 5667);
		System.out.println("Score Added");
		System.exit(0);
	}

}
