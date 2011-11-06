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

/**
 * Classe qui gere le chargement des fichiers contenant les SEF
 * @author Frederic
 *
 */
public class Charger {

	
	private ArrayList<SEF> mesSEF;//A remplir au fur et a mesure de la lecture du fichier
	private ControllerFenetrePincipale cfp;
	private FenetrePrincipale fp;
	
	/**
	 * Constructeur qui gere la connexion entre les différents elements
	 * et permet d'ecrire aussi dans le panneau de gauche et remplit l'arraylist de SEF
	 * @param mesSEF la liste de SEF existant déjà
	 * @param cfp le controler de la fenetre principale
	 * @param fp la fenetre prinicipale
	 */
	public Charger(ArrayList<SEF> mesSEF,ControllerFenetrePincipale cfp,FenetrePrincipale fp) {
		this.cfp = cfp;
		this.mesSEF = cfp.getMesSEF();
		this.fp = fp;
	}
	
	/**
	 * charge et lit ligne a ligne un fichier
	 * @param file chemin du fichier
	 */
	public void chargerfichier(String file) {
		boolean ok = true;

		try{
			BufferedReader buff = new BufferedReader(new FileReader(file));

			try {
				String line;
				// Lecture du fichier ligne par ligne. Cette boucle se termine
				// quand la méthode retourne la valeur null.
				// ok permet de recup si le parser plante ou pas
				while ((line = buff.readLine()) != null && ok) {
					//System.out.println(line);
					ok = parserLigne(line);
				}
			} finally {
				// dans tous les cas, on ferme les flux
				buff.close();
			}
		} catch (IOException ioe) {
			// erreur de fermeture des flux
			System.out.println("Erreur --" + ioe.toString());
			return;
		}
	}
	
	/**
	 * fonction qui test et remplit les SEF a partir d'un fichier
	 * @param f la chaine a tester
	 * @return true si l'opération a réussi, false sinon
	 * @throws IOException
	 */
	public boolean parserLigne(String f) throws IOException {

		int bornes = f.indexOf("#");
		
		try {
		String recupbornes = f.substring(0, bornes);
		String ptsInflexion = f.substring(bornes+1);
	//	System.out.println("Bornes :"+recupbornes+"  \t "+ptsInflexion);

		int separation = f.indexOf(",");
		String borneinf = recupbornes.substring(0, separation);
		String bornesup = recupbornes.substring(separation+1);
	//	System.out.println("Borne inf :"+borneinf);
	//	System.out.println("Borne sup :"+bornesup+"\n");
		
		
		//System.out.println("Nb points détectés :");

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

	public ControllerFenetrePincipale getCfp() {
		return cfp;
	}

	public void setCfp(ControllerFenetrePincipale cfp) {
		this.cfp = cfp;
	}

	

}

