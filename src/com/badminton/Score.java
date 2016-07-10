package com.badminton;

public class Score {

	private String playerInitials;
	private int playerScore;

	public Score(String initials, int score){
		playerInitials = initials;
		playerScore = score;
	}

	public String getInitials(){
		return playerInitials;
	}

	public int getScore(){
		return playerScore;
	}
}
