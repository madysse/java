package VoteDVD;


import java.io.*;
import java.nio.charset.Charset;

import alloparser.AlloParser;


public class voteDVD  {
	
		
	public static void makeVote(String[] args) {


		//Put Film name in txt resume
		formatInputFile(args);
		
		//Create Html from txt resume
		//generateHTML(args);

		
	}
	
	public static void generateHTML(String[] args){
			
		String inputFile;	
		String titreAnneeGenre, realActeurs, lienImage, resume;
		
		System.out.println("Online");

		inputFile = ""+ args[0]; 
		
		System.setProperty( "file.encoding", "ISO8859_15" );
		
		try {
	        	//Create HTML file
				File MyFile = new File("ce_ot_vote.html"); 
				MyFile.delete();
			
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
																		
				PrintWriter outputFile = new PrintWriter(MyFile);
			
				
				//Get Date of today
				String format = "dd-MM-yyyy"; 

				java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
				java.util.Date date = new java.util.Date(); 

				System.out.println( formater.format( date ) ); 				
								
				outputFile.print("<html>\n<head>\n");
				outputFile.print("<title>CE-OT DVD Vote </title>\n</head>\n");
				outputFile.print("<body bgcolor=\"#F7DC80\">\n");
				outputFile.print("<h1> <CENTER> CE OT - Section DVD </CENTER> <br><br></h1>\n");
				outputFile.print("<B><form name=\"myform\" action=\"mailto:CE.DVD@oberthur.com?subject=VoteResult\" method=\"POST\">\n");
				outputFile.print("<div align=\"left\"><br></B>\n");
				outputFile.print("<CENTER>\n");
				outputFile.print("<TABLE BORDER=0 WIDTH=\"90%\">\n");
				
				//Date Auto
				outputFile.print("<input type=\"Hidden\" name=\"sentdate\" value=\"" + formater.format( date ) + "\">\n");

					
				
				boolean flagCouleur=false;
				boolean flagBlueRay=false;
				String stringCouleur;
				String stringTemp[];
				int i=0;
				
				//Boucle sur l'ensemble des films que contient le fichier
				while((titreAnneeGenre = br.readLine()) != null) 
	            {
					if((realActeurs = br.readLine()) == null) 
						return;
					if((lienImage = br.readLine()) == null) 
						return;
					if((resume = br.readLine()) == null) 
						return;
					
					if(flagCouleur)
					{
						flagCouleur=false;
						stringCouleur="<TD bgcolor=\"#FFFBCE\">\n";
					}
					else
					{
						flagCouleur=true;
						stringCouleur="<TD bgcolor=\"#FFF488\">\n";
					}
					
					if(titreAnneeGenre.endsWith("DVD_BLUERAY"))
					{
						flagBlueRay=true;
						
						stringTemp = titreAnneeGenre.split("DVD_BLUERAY");
						
						titreAnneeGenre = stringTemp[0];
					}
					
					
					outputFile.print("<TR>\n");
					outputFile.print(stringCouleur);
					outputFile.print("<CENTER>\n");
					outputFile.print("<IMG SRC=\"http://ce-dvd.nanterre.oberthurcs.com/sondage_cedvd/Vote_2009_04/ACC_pan.gif\">\n");
					outputFile.print("<input type=\"checkbox\" name=\""+i+"\" value=\""+i+"\" > \n");
					outputFile.print("</CENTER>\n");
					outputFile.print("</TD>\n");
					
					outputFile.print(stringCouleur);
					if (lienImage.indexOf("http") != -1)
						outputFile.print("<IMG SRC=\"" + lienImage + "\"Width=100>\n");
					else
						outputFile.print("ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR \n\n\n");
					
					outputFile.print("</TD>\n");
					outputFile.print(stringCouleur);
					if(flagBlueRay)
						outputFile.print("<B>" + titreAnneeGenre + "       - en BluRay et DVD <IMG SRC=\"http://ce-dvd.nanterre.oberthurcs.com/sondage_cedvd/BluRay.png\"Width=30><IMG SRC=\"http://ce-dvd.nanterre.oberthurcs.com/sondage_cedvd/DVD.png\"Width=30><br></B>\n");
					else
						outputFile.print("<B>" + titreAnneeGenre + "<br></B>\n");
						
				
					outputFile.print("<I>" + realActeurs + "<br></I>\n");

					outputFile.print("<TABLE>\n");
					outputFile.print("<TR>\n");
					outputFile.print(stringCouleur);
					outputFile.print("<U>\n");
					outputFile.print("Résumé:\n");
					outputFile.print("</TD></U>\n");
					outputFile.print(stringCouleur);
					
					outputFile.print(resume);
					
					outputFile.print("</TD>\n");
					outputFile.print("</TR>\n");
					outputFile.print("</TABLE>\n");
					outputFile.print("<hr width=\"80%\"size=3>\n");
					outputFile.print("</TR>\n");	
					
					flagBlueRay=false;
					
					i++;
	            }
				
				

				outputFile.print("</TABLE>\n");
				outputFile.print("</CENTER>\n");
				outputFile.print("<br><input type=\"submit\" value=\"Envoyez vos choix\">   <br> N'hésitez pas, pendant les sessions, à nous soumettre des idées de DVD pour les prochains votes.<br>\n");
				outputFile.print("</div></form>\n");
				outputFile.print("<br><hr><br>\n");
				outputFile.print("<form name=\"erase\"action=\"mailto:CE.DVD@oberthurcs.com?subject=Delete\">\n");
				outputFile.print("Si vous ne souhaitez plus recevoir ce genre de message, vous pouvez nous le faire savoir en cliquant sur le bouton:<br><br>\n");
				outputFile.print("<input type=\"submit\" value=\"Supprimez moi de la liste de diffusion\">\n");
				outputFile.print("</form>\n");
				outputFile.print("<FORM name=suggestion action=mailto:CE.DVD@oberthurcs.com?subject=Suggestion>\n");
				outputFile.print("Pour des compléments d'information:\n");
				outputFile.print("<BR>\n");
				outputFile.print("<BR>\n");
				outputFile.print("<INPUT type=submit value=\"Demande d'information\">\n");
				outputFile.print("<BR><BR><BR><BR>\n");
				outputFile.print("</FORM>\n");
				outputFile.print("</BODY>\n");
				outputFile.print("</HTML>\n");
								

				outputFile.flush();
				br.close();
				outputFile.close();
				
	    } catch (FileNotFoundException e) {
			//Cette exception est levée 
			//si l'objet FileInputStream ne trouve aucun fichier
	        e.printStackTrace();
	    } catch (IOException e) {
	    	//Celle-ci se produit lors d'une erreur
	    	//d'écriture ou  de lecture
	        e.printStackTrace();}
		
	}
	
	
	public static void formatInputFile(String[] args){
		
		String inputFile;	
		String titreFilm, realActeurs, lienImage, resume;
		
		System.out.println("Online");

		inputFile = ""+ args[0]; 
		
		System.setProperty( "file.encoding", "ISO8859_15" );
		
		try {
	        	//Create HTML file
				File MyFile = new File("filmsFormat.txt"); 
				MyFile.delete();
			
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
																		
				PrintWriter outputFile = new PrintWriter(MyFile);
																				
				boolean flagCouleur=false;
				boolean flagBlueRay=false;
				String stringCouleur;
				String stringTemp[];
				int i=0;
				
				//Boucle sur l'ensemble des films que contient le fichier
				while((titreFilm = br.readLine()) != null) 
	            {
						
					//outputFile.print(titreFilm + " (" + AlloParser.getYear(url) + ") - " + AlloParser.getGenre(url));
					
					
					flagBlueRay=false;
					
					i++;
	            }
				
				

				outputFile.print("</TABLE>\n");
				outputFile.print("</CENTER>\n");
				outputFile.print("<br><input type=\"submit\" value=\"Envoyez vos choix\">   <br> N'hésitez pas, pendant les sessions, à nous soumettre des idées de DVD pour les prochains votes.<br>\n");
				outputFile.print("</div></form>\n");
				outputFile.print("<br><hr><br>\n");
				outputFile.print("<form name=\"erase\"action=\"mailto:CE.DVD@oberthurcs.com?subject=Delete\">\n");
				outputFile.print("Si vous ne souhaitez plus recevoir ce genre de message, vous pouvez nous le faire savoir en cliquant sur le bouton:<br><br>\n");
				outputFile.print("<input type=\"submit\" value=\"Supprimez moi de la liste de diffusion\">\n");
				outputFile.print("</form>\n");
				outputFile.print("<FORM name=suggestion action=mailto:CE.DVD@oberthurcs.com?subject=Suggestion>\n");
				outputFile.print("Pour des compléments d'information:\n");
				outputFile.print("<BR>\n");
				outputFile.print("<BR>\n");
				outputFile.print("<INPUT type=submit value=\"Demande d'information\">\n");
				outputFile.print("<BR><BR><BR><BR>\n");
				outputFile.print("</FORM>\n");
				outputFile.print("</BODY>\n");
				outputFile.print("</HTML>\n");
								

				outputFile.flush();
				br.close();
				outputFile.close();
				
	    } catch (FileNotFoundException e) {
			//Cette exception est levée 
			//si l'objet FileInputStream ne trouve aucun fichier
	        e.printStackTrace();
	    } catch (IOException e) {
	    	//Celle-ci se produit lors d'une erreur
	    	//d'écriture ou  de lecture
	        e.printStackTrace();}
		
	}
	
	public class aMovie{
		
		public aMovie() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String title;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getGenre() {
			return genre;
		}
		public void setGenre(String genre) {
			this.genre = genre;
		}
		public String getDirector() {
			return director;
		}
		public void setDirector(String director) {
			this.director = director;
		}
		public String getCasts() {
			return casts;
		}
		public void setCasts(String casts) {
			this.casts = casts;
		}
		public String getUrlImage() {
			return urlImage;
		}
		public void setUrlImage(String urlImage) {
			this.urlImage = urlImage;
		}
		public String getResume() {
			return resume;
		}
		public void setResume(String resume) {
			this.resume = resume;
		}
		public String year;
		public String genre;
		public String director;
		public String casts;
		public String urlImage;
		public String resume;
	}
	
	
}
