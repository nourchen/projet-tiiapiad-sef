package manipSef;

import java.util.ArrayList;


/**
 * Classe permettant de modeliser un sous ensemble flou, défini comme une fonction linéaires par morceaux
 * @author Sylvia Vieira
 *
 */
public class SEF {
	/**
	 * borne inférieure de l'intervalle de définition du sous ensemble flou
	 */
	private double borneInf;
	/**
	 * borne supérieure de l'intervalle de définition du sous ensemble flou
	 */
	private double borneSup;
	/**
	 * liste des points d'inflexions de la fonction d'appartenance au sef (qui est linéaire par morceaux)
	 */
	private ArrayList<Point> inflexions;
	
	public SEF(double inf,double sup,ArrayList<Point> listePoints){
		borneInf = inf;
		borneSup = sup;
	}

	public double getBorneInf() {
		return borneInf;
	}

	public void setBorneInf(double inf) {
		borneInf = inf;
	}

	public double getBorneSup() {
		return borneSup;
	}

	public void setBorneSup(double sup) {
		borneSup = sup;
	}

	//Il faudra penser à ordonner la liste (en fct des abscisses) à un moment donné 
	//=> où? PB de modélisation, à voir plus tard!
	public void ajouterPointInflexion(Point p){
		inflexions.add(p);
	}	
	
	
	
}
