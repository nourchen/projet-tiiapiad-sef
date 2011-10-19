package manipFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import manipSef.Point;
import manipSef.SEF;


// lancer une exception sur parser

public class Charger {

	//private static String test = "-inf,2011#-1.5,0;1.25,1;4.2,0.75;5,0";
	private ArrayList<SEF> mesSEF = new ArrayList<SEF> ();//A remplir au fur et a mesure de la lecture du fichier

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
					parserLigne(line);
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

	public void parserLigne(String f) {

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

		ArrayList<Point> pts = new ArrayList<Point>();
		// la on peut reccuperer les bonnes valeurs : reste a les stocker proprement
		for (int k = 0; k < temp.length ; k++){
			int recup = temp[k].indexOf(",");
			String valX = temp[k].substring(0, recup);
			String valY = temp[k].substring(recup+1);
			System.out.println("Valx  " +valX+ "  Val Y :" +valY);
			pts.add(new Point (Double.parseDouble(valX) ,
					Double.parseDouble(valY)));    
		}

		double binf;
		double bsup;

		if(borneinf.equals("-inf")){
			binf = Double.MIN_VALUE;
		} else {
			binf = Double.parseDouble(borneinf);    
		}

		if(bornesup.equals("inf")) {
			bsup = Double.MAX_VALUE;
		} else {
			bsup = Double.parseDouble(bornesup);
		}

		SEF essai = new SEF(binf,bsup, pts);
		mesSEF.add(essai);
	}

	public ArrayList<SEF> getMesSEF() {
		return mesSEF;
	}

	public void setMesSEF(ArrayList<SEF> mesSEF) {
		this.mesSEF = mesSEF;
	}

	/*      public static void main(String[] args) {
                parserFichier(test);
        }*/

}

