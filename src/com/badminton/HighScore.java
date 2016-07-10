package com.badminton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HighScore {

	private ArrayList<Score> highScores;

	public HighScore(){
		highScores = new ArrayList<Score>();
	}

	public void addScore(String name, int score){
		//load scores from disk
		highScores = loadAndGetScores();
		highScores.add(new Score(name.toUpperCase(), score));

		//sort in descending order
		ScoreComparator comparator = new ScoreComparator();
		Collections.sort(highScores, comparator);

		//only keep 10 scores
		if(highScores.size() > 10){
			highScores.remove(highScores.size()-1);
		}

		//update scores.txt file
		recordScores(highScores);
	}

	private void recordScores(ArrayList<Score> scores){
		try {
			PrintStream ps = new PrintStream("scores.txt");
			for(int i = 0; i < scores.size(); i++){
				ps.println(scores.get(i).getInitials().toUpperCase() + " " + scores.get(i).getScore());
			}
			ps.flush();
			ps.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		printArray(highScores);
	}

	public ArrayList<Score> getScores(){
		return loadAndGetScores();
	}

	private ArrayList<Score> loadAndGetScores(){
		highScores = new ArrayList<>();
		File file = new File("scores.txt");

		if(file.exists()){
			try {
				Scanner	inFile = new Scanner(file);
				while(inFile.hasNext()){
					String line  = inFile.nextLine();
					String delims = "[ ]+";
					String[] tokens = line.split(delims);
					highScores.add(new Score(tokens[0].toUpperCase(), Integer.parseInt(tokens[1])));
				}
				inFile.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return highScores;
	}

	private static void printArray(ArrayList<Score> array){
		System.out.println("--PRINTING HIGH SCORES--");
		for(int i = 0; i < array.size(); i++){
			System.out.println(array.get(i).getInitials() + " " + array.get(i).getScore());
		}
	}
}
