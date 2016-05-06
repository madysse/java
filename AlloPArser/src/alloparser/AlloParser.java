package alloparser;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.Normalizer;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import VoteDVD.voteDVD;
import VoteDVD.voteDVD.aMovie;


public class AlloParser {
   
	

	
	/**
	* Main method for running the HelloWorld demo.
	*/
	/*public static void main(String[] args) {
		
		String urlFilm;
		
		urlFilm = getUrl("Persepolis");
		
		
		getInfo(urlFilm);

		//getInfo("http://www.allocine.fr/film/fichefilm_gen_cfilm=143692.html");
			
		
		
	}
	*/
	
	public static String getUrl(String titreSearch) {
	

		Document doc = null;
		String garbage[], titre, titreFirstElement[];
		
		
		titre = titreSearch;
		titreFirstElement = titre.split(" ");
		System.out.println("Titre: "+ titre);
		//replace space by + for search bar 
		titreSearch = removeAccent(titreSearch);
		titreSearch = titreSearch.replace(" ", "+");
		try {
			URLEncoder.encode(titreSearch, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("titreSearch: "+ titreSearch);
		System.out.println("titreFirstElement: "+ titreFirstElement[0]);
		
		//Configure url of the film that we looking for on Allocine webpage
		String urlSearch = "http://www.allocine.fr/recherche/?q=" + titreSearch;
		String urlFound;
		System.out.println("Url: "+ urlSearch);
		
		//Set Proxy parameters Oberthur (see at end of http://wpad/wpad.dat)
		System.setProperty("http.proxyHost", "10.200.128.20");
	    System.setProperty("http.proxyPort", "8080");
		
		try {
			
			doc = Jsoup.connect(urlSearch).timeout(60000).get();
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		Elements  list = doc.getElementsByTag("div");
		

		//System.out.println("Cite: "+ list.toString() + "\n");
		
		//Get Url				
		garbage = list.toString().split("alt=\"");		
		garbage = garbage[1].toString().split("<b>"+titreFirstElement[0]);		
		garbage = garbage[0].toString().split("<a href=\"/film");	
		garbage = garbage[1].toString().split("\">");
		
		//System.out.println("TEST 2: http://www.allocine.fr/film"+ garbage[0] + "\n");
		urlFound = "http://www.allocine.fr/film"+ garbage[0];
		return urlFound;
	
	}
	
	
	
	
	public static voteDVD.aMovie getInfo(String url) {
	

		Document doc = null;
		String garbage[], actors[];
		aMovie thisMovie = new aMovie();
		
		String returnString = " ";
		String returnStarsPress = "NC";
		String returnStarsPeople = "NC";
		
		
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
			//System.out.println("Film: "+ garbage[0] + "\n");
			thisMovie.setTitle(garbage[0]);

			//Get Director			
			garbage = list.toString().split("<meta property=\"video:director\" content=\"");
			garbage = garbage[1].toString().split("\"");
			//System.out.println("Realisateur: "+ garbage[0] + "\n");
			thisMovie.setDirector(garbage[0]);
			
			//Get Genre			
			garbage = list2.toString().split("==\"><span itemprop=\"genre\">");
			garbage = garbage[1].toString().split("</");
			//System.out.println("Genre: "+ garbage[0] + "\n");
			thisMovie.setGenre(garbage[0]);
			
			//Get Year			
			garbage = list2.toString().split("<span class=\"that\">");
			garbage = garbage[1].toString().split("</");
			//System.out.println("Année: "+ garbage[0] + "\n");
			thisMovie.setYear(garbage[0]);
						
			//Get Synopsis			
			garbage = list2.toString().split("synopsis-txt\" itemprop=\"description\">");
			garbage = garbage[1].toString().split(" </div>");
			garbage = garbage[0].toString().split("\n         ");
			garbage[1] = garbage[1].replace("\n       ", "");
			garbage[1] = garbage[1].replace("  ", " ");
			garbage[1] = garbage[1].replace("<i>", "");
			garbage[1] = garbage[1].replace("</i>", "");
			garbage[1] = garbage[1].replace("<br>", "");
			garbage[1] = garbage[1].replace("</br>", "");
			//System.out.println("Resumé: "+ garbage[1] + "\n");
			thisMovie.setResume(garbage[0]);
			
			
			//Get Image URL			
			garbage = list.toString().split("property=\"og:image\" content=\"");
			garbage = garbage[1].toString().split("\"");
			//System.out.println("Image: "+ garbage[0] + "\n");
			thisMovie.setUrlImage(garbage[0]);

			
			//Get Cast
			try{
				garbage = list.toString().split("<span class=\"light\">Avec</span>");
				garbage = garbage[1].toString().split("</div>");
				garbage = garbage[0].toString().split("class=\"blue-link\">");	
				actors = garbage[1].toString().split("</span>");	
				returnString = actors[0];
				actors = garbage[2].toString().split("</span>");
				returnString += ", " + actors[0];
				actors = garbage[3].toString().split("</span>");
				returnString += ", " + actors[0];
			}catch (ArrayIndexOutOfBoundsException  e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			thisMovie.setCasts(returnString);
			
			//Get STARS !!!! TODO	

			try{
				//Press
				garbage = list2.toString().split("<span class=\"stareval-note\">");
				garbage = garbage[1].toString().split("</span>");
				//System.out.println("Stars Press: "+ garbage[0]);
				returnStarsPress = garbage[0];
				//Spectators
				garbage = list2.toString().split("<span class=\"stareval-note\" itemprop=\"ratingValue\" content=\"");
				garbage = garbage[1].toString().split("\">");
				//System.out.println("Stars Spectators: "+ garbage[0]);
				returnStarsPeople = garbage[0];
			}catch (ArrayIndexOutOfBoundsException  e) {
				// TODO Auto-generated catch block
				//System.out.println("No Stars");
			}
			thisMovie.setStarsPress(returnStarsPress);
			thisMovie.setStarsPeople(returnStarsPeople);
			
					
			 return thisMovie;	
	}
	
	public static String getYear(String url) {
		

		Document doc = null;
		String garbage[];
		
		
		try {
			
			doc = Jsoup.connect(url).timeout(60000).get();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			Elements  list2 = doc.getElementsByTag("div");
			
			//Get Year			
			garbage = list2.toString().split("<span class=\"that\">");
			garbage = garbage[1].toString().split("</");
			return garbage[0];

	
	}
	
	public static String getGenre(String url) {

		Document doc = null;
		String garbage[];
		
		
		try {
			
			doc = Jsoup.connect(url).timeout(60000).get();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			Elements  list = doc.getElementsByTag("div");
			
			//Get Genre			
			garbage = list.toString().split("==\"><span itemprop=\"genre\">");
			garbage = garbage[1].toString().split("</");
			System.out.println("Genre: "+ garbage[0] + "\n");

			return garbage[0];
	}
	
	public static String getDirector(String url) {
		

		Document doc = null;
		String garbage[];
		
		
		try {
			
			doc = Jsoup.connect(url).timeout(60000).get();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			Elements  list = doc.getElementsByTag("meta");
			
			//Get Director			
			garbage = list.toString().split("<meta property=\"video:director\" content=\"");
			garbage = garbage[1].toString().split("\"");
			System.out.println("Realisateur: "+ garbage[0] + "\n");

			return garbage[0];
	
	}
	
	public static String getSynopsis(String url) {

		Document doc = null;
		String garbage[];
		
		
		try {
			
			doc = Jsoup.connect(url).timeout(60000).get();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Elements  list = doc.getElementsByTag("div");
			
		
		//Get Synopsis			
		garbage = list.toString().split("synopsis-txt\" itemprop=\"description\">");
		garbage = garbage[1].toString().split(" </div>");
		garbage = garbage[0].toString().split("\n         ");
		garbage[1] = garbage[1].replace("\n       ", "");
		garbage[1] = garbage[1].replace("  ", " ");
		garbage[1] = garbage[1].replace("<i>", "");
		garbage[1] = garbage[1].replace("</i>", "");
		garbage[1] = garbage[1].replace("<br>", "");
		garbage[1] = garbage[1].replace("</br>", "");
		
		return garbage[1];
		
	}
	
	public static String getCast(String url) {

		Document doc = null;
		String garbage[], actors[], returnString="";
		
		
		try {
			
			doc = Jsoup.connect(url).timeout(60000).get();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			Elements  list = doc.getElementsByTag("div");
			
		
		//Get Cast
		try{
			garbage = list.toString().split("<span class=\"light\">Avec</span>");
			garbage = garbage[1].toString().split("</div>");
			garbage = garbage[0].toString().split("class=\"blue-link\">");	
			actors = garbage[1].toString().split("</span>");	
			returnString = actors[0];
			actors = garbage[2].toString().split("</span>");
			returnString += ", " + actors[0];
			actors = garbage[3].toString().split("</span>");
			returnString += ", " + actors[0];
		}catch (ArrayIndexOutOfBoundsException  e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return returnString;
		
	}
	
	public static String getStars(String titreSearch) {

		//TODO
		
		return "todo";
	}
	
	public static String getUrlPicture(String url) {

		Document doc = null;
		String garbage[];
		
		
		try {
			
			doc = Jsoup.connect(url).timeout(60000).get();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Elements  list = doc.getElementsByTag("meta");
		
	
		//Get Image URL			
		garbage = list.toString().split("property=\"og:image\" content=\"");
		garbage = garbage[1].toString().split("\"");
		
		return garbage[0];		
	}	
		
	public static String getVersion(String titreSearch) {

		//TODO
		
		return "todo";
	}
		
	public static String removeAccent(String source) {
		return Normalizer.normalize(source, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
	}
	
	
}
