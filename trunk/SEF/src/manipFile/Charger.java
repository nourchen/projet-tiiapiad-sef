package manipFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import manipSef.SEF;




public class Charger {

	private static String test = "-inf,2011#-1.5,0;1.25,1;4.2,0.75;5,0";
	
	public Charger() {}
	
	public void chargerfichier(String file) {
		
		try{
		// Création du flux bufférisé sur un FileReader, immédiatement suivi par un 
		// try/finally, ce qui permet de ne fermer le flux QUE s'il le reader
		// est correctement instancié (évite les NullPointerException)
		BufferedReader buff = new BufferedReader(new FileReader(file));

		try {
		String line;
		// Lecture du fichier ligne par ligne. Cette boucle se termine
		// quand la méthode retourne la valeur null.
		while ((line = buff.readLine()) != null) {
		System.out.println(line);
		//faites ici votre traitement
		}
		} finally {
		// dans tous les cas, on ferme nos flux
		buff.close();
		}
		} catch (IOException ioe) {
		// erreur de fermeture des flux
		System.out.println("Erreur --" + ioe.toString());
		}
}
	
	public static void parserFichier(String f) {
		
//	String test ="-inf,2011#-1.5,0;1.25,1;4.2,0.75;5,0";
	
	int bornes = f.indexOf("#");
	
	String recupbornes = f.substring(0, bornes);
	String ptsInflexion = f.substring(bornes+1);
	System.out.println("Bornes :"+recupbornes+"  \t "+ptsInflexion);
	
	int separation = f.indexOf(",");
	String borneinf = recupbornes.substring(0, separation);
	String bornesup = recupbornes.substring(separation+1);
	System.out.println("Borne inf :"+borneinf);
	System.out.println("Borne sup :"+bornesup+"\n");
	
	System.out.println("Nb points détectés :");
	
	String[] temp;
	 
	/* delimiter les differents points */
	String delimiterPoint = ";";
	
	temp = ptsInflexion.split(delimiterPoint);
	/* Boucle de test pour vérifier */

	/*
	for(int i =0; i < temp.length ; i++){
	System.out.println(temp[i]);
	}
	
	System.out.println("===== Passage en SEF ======");
	
	System.out.println("La virgule :"+temp[0].indexOf(","));
	System.out.println("La virgule :"+temp[1].getClass());
	*/
	ArrayList<SEF> tempor = new ArrayList<SEF> ();
	// la on peut reccuperer les bonnes valeurs : reste a les stocker proprement
	for (int k = 0; k < temp.length ; k++){
		int recup = temp[k].indexOf(",");
		String valX = temp[k].substring(0, recup);
		String valY = temp[k].substring(recup+1);
		System.out.println("Valx  " +valX+ "  Val Y :" +valY);
	}
	
	
	}

	
/*	public static void main(String[] args) {
		parserFichier(test);
	}
	*/
}
