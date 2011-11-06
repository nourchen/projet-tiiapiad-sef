package manipFile;

import ihm.FenetrePrincipale;
import controllers.ControllerFenetrePincipale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.data.xy.XYSeries;

import javax.swing.JOptionPane;


import manipSef.SEF;


// lancer une exception sur parser

public class Charger {

	//private static String test = "-inf,2011#-1.5,0;1.25,1;4.2,0.75;5,0";
	//TODO verifier le remplissage FenetrePrincipale fp
	private ArrayList<SEF> mesSEF;//A remplir au fur et a mesure de la lecture du fichier
	private ControllerFenetrePincipale cfp;
	private FenetrePrincipale fp;
	
	public Charger(ArrayList<SEF> mesSEF,ControllerFenetrePincipale cfp,FenetrePrincipale fp) {
		this.cfp = cfp;
		this.mesSEF = cfp.getMesSEF();
		this.fp = fp;
	}

	public void chargerfichier(String file) {
		boolean ok = true;

		try{
			// Création du flux bufférisé sur un FileReader, immédiatement suivi par un
			// try/finally, ce qui permet de ne fermer le flux QUE s'il le reader
			// est correctement instancié (évite les NullPointerException)
			BufferedReader buff = new BufferedReader(new FileReader(file));

			try {
				String line;
				// Lecture du fichier ligne par ligne. Cette boucle se termine
				// quand la méthode retourne la valeur null.
				while ((line = buff.readLine()) != null && ok) {
					System.out.println(line);
					ok = parserLigne(line);
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

	public boolean parserLigne(String f) throws IOException {

		int bornes = f.indexOf("#");
		
		try {
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

		XYSeries pts = new XYSeries("SEF "+(mesSEF.size()+1));
		// la on peut reccuperer les bonnes valeurs : reste a les stocker proprement
		String stocker ="";
		for (int k = 0; k < temp.length ; k++){
			int recup = temp[k].indexOf(",");
			String valX = temp[k].substring(0, recup);
			String valY = temp[k].substring(recup+1);
			stocker +=""+valX+" "+valY+"\n";
			pts.add((Double.parseDouble(valX)) ,
					Double.parseDouble(valY));    
		}
		stocker = stocker.substring(0, stocker.length() - 1);
		fp.pointEntree(borneinf,bornesup,stocker);
		
		double binf;
		double bsup;

		if(borneinf.equals("-inf")||borneinf.equals("inf")||borneinf.equals("+inf")){
			binf = Double.MIN_VALUE;
		} else {
			binf = Double.parseDouble(borneinf);    
		}

		if(bornesup.equals("inf")|| bornesup.equals("+inf") || bornesup.equals("-inf")) {
			bsup = Double.MAX_VALUE;
		} else {
			bsup = Double.parseDouble(bornesup);
		}
		SEF essai = new SEF(binf,bsup, pts);
		mesSEF.add(essai);
		// on peut continuer a lire la ligne suivante si elle existe
		return true;
		} catch (Exception e){
			// Affichage du message d'erreur
			JOptionPane.showMessageDialog(null,"Erreur ! Format du fichier incorrect !","ERREUR",JOptionPane.ERROR_MESSAGE);
			// On quitte la boucle si y a une erreur (on arrete la lecture)
			return false;
		}
		
	}

	public ArrayList<SEF> getMesSEF() {
		return mesSEF;
	}

	public void setMesSEF(ArrayList<SEF> mesSEF) {
		this.mesSEF = mesSEF;
	}


}

