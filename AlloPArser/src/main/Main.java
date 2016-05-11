package main;

import VoteDVD.voteDVD;
import alloparser.AlloParser;

public class Main {

	
	/**
	* Main method for running the HelloWorld demo.
	*/
	public static void main(String[] args) {
		
		String urlFilm;
		
		//urlFilm = AlloParser.getUrl("Les ripoux");
		//urlFilm = AlloParser.getUrl("Jurassic Park");
		//urlFilm = AlloParser.getUrl("Inception");
		urlFilm = AlloParser.getUrl("Avatar");
		//urlFilm = AlloParser.getUrl("UN SAC DE BILLES");
		
		
		//AlloParser.getInfo(urlFilm);

		//getInfo("http://www.allocine.fr/film/fichefilm_gen_cfilm=143692.html");
			
		  
		//System.out.println("Director: "+ AlloParser.getDirector(urlFilm));

		//System.out.println("Casts: "+ AlloParser.getCast(urlFilm));
		//System.out.println("Resumé: "+ AlloParser.getSynopsis(urlFilm));
		//System.out.println("Url Image: "+ AlloParser.getUrlPicture(urlFilm));
		
		voteDVD.makeVote(args);
		
		voteDVD.generateHTML("filmsFormat.txt");
		
		
	}
	
}
