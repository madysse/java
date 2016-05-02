package main;

import alloparser.AlloParser;

public class Main {

	
	/**
	* Main method for running the HelloWorld demo.
	*/
	public static void main(String[] args) {
		
		String urlFilm;
		
		urlFilm = AlloParser.getUrl("Jurassic World 2");
		
		
		AlloParser.getInfo(urlFilm);

		//getInfo("http://www.allocine.fr/film/fichefilm_gen_cfilm=143692.html");
			
		
		
	}
	
}
