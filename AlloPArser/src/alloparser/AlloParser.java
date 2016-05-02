package alloparser;


import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class AlloParser {
   
	

	
	/**
	* Main method for running the HelloWorld demo.
	*/
	public static void main(String[] args) {
		
		String urlFilm;
		
		urlFilm = getUrl("Jurassic World 2");
		
		
		getInfo(urlFilm);

		//getInfo("http://www.allocine.fr/film/fichefilm_gen_cfilm=143692.html");
			
		
		
	}
	
	public static String getUrl(String titreSearch) {
	

		Document doc = null;
		String garbage[], titre;
		
		
		titre = titreSearch;
		//replace space by + for search bar 
		titreSearch.replace(" ", "+");
		
		
		String urlSearch = "http://www.allocine.fr/recherche/?q=" + titre;
		String urlFound;
		System.out.println("Url: "+ urlSearch);
		
		
		try {
			
			doc = Jsoup.connect(urlSearch).timeout(60000).get();
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		Elements  list = doc.getElementsByTag("div");
		

		//System.out.println("Cite: "+ list.toString() + "\n");
		
		//Get Url			
		garbage = list.toString().split("alt=\""+titre+"\"");
		garbage = garbage[1].toString().split("<b>"+titre+"</b>");		
		garbage = garbage[0].toString().split("<a href=\"/film");	
		garbage = garbage[1].toString().split("\">");
		
		System.out.println("TEST 2: http://www.allocine.fr/film"+ garbage[0] + "\n");
		urlFound = "http://www.allocine.fr/film"+ garbage[0];
		return urlFound;
	
	}
	
	
	
	
	public static void getInfo(String url) {
	

		Document doc = null;
		String garbage[], actors[];
		
		
		try {
			
			doc = Jsoup.connect(url).timeout(60000).get();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


			Elements  list = doc.getElementsByTag("meta");
			Elements  list2 = doc.getElementsByTag("div");
			
			
			//System.out.println("Meta: "+ list.toString() + "\n");
			//System.out.println("Div: "+ list2.toString() + "\n");


			//Get Title			
			garbage = list.toString().split("<meta property=\"og:title\" content=\"");
			garbage = garbage[1].toString().split("\"");
			System.out.println("Film: "+ garbage[0] + "\n");

			//Get Director			
			garbage = list.toString().split("<meta property=\"video:director\" content=\"");
			garbage = garbage[1].toString().split("\"");
			System.out.println("Realisateur: "+ garbage[0] + "\n");
			
			//Get Genre			
			garbage = list2.toString().split("==\"><span itemprop=\"genre\">");
			garbage = garbage[1].toString().split("</");
			System.out.println("Genre: "+ garbage[0] + "\n");
			
			//Get Année			
			garbage = list2.toString().split("<span class=\"that\">");
			garbage = garbage[1].toString().split("</");
			System.out.println("Année: "+ garbage[0] + "\n");
			
			//Get Synopsis			
			garbage = list2.toString().split("synopsis-txt\" itemprop=\"description\">");
			garbage = garbage[1].toString().split(" </div>");
			System.out.println("Resumé: "+ garbage[0] + "\n");
			
			//Get Image URL			
			garbage = list.toString().split("property=\"og:image\" content=\"");
			garbage = garbage[1].toString().split("\"");
			System.out.println("Image: "+ garbage[0] + "\n");

			
			//Get Cast
			garbage = list2.toString().split("<span class=\"light\">Avec</span>");
			garbage = garbage[1].toString().split("</div>");
			garbage = garbage[0].toString().split("class=\"blue-link\">");	
			actors = garbage[1].toString().split("</span>");	
			System.out.println("Acteur: "+ actors[0]);
			actors = garbage[2].toString().split("</span>");
			System.out.println(", "+ actors[0]);
			actors = garbage[3].toString().split("</span>");
			System.out.println(", "+ actors[0]);
			
			
			//Get STARS !!!! TODO	
			garbage = list2.toString().split("<span class=\"stareval-note\">");
			garbage = garbage[1].toString().split("</span>");
			System.out.println("Stars Press: "+ actors[0]);
			
			
			
	
	}
	
	public static String getYear(String titreSearch) {
		
		//TODO
		
		return "todo";
	}
	
	public static String getGenre(String titreSearch) {

		//TODO
		
				return "todo";
	}
	
	public static String getDirector(String titreSearch) {

		//TODO
		
		return "todo";
	}
	
	public static String getCast(String titreSearch) {

		//TODO
		
		return "todo";
	}
	
	public static String getStars(String titreSearch) {

		//TODO
		
		return "todo";
	}
	
	
}
