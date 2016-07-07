package main;

import VoteDVD.voteDVD;
import alloparser.AlloParser;

public class Main {

	
	/**
	* Main method for running the HelloWorld demo.
	*/
	public static void main(String[] args) {
		
		String urlFilm;
		
		voteDVD.makeVote(args);
		
		voteDVD.generateHTML("filmsFormat.txt");
		
		
	}
	
}
