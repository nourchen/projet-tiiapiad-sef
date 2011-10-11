package manipSef;

import java.util.ArrayList;


/**
 * Classe permettant de modeliser un sous ensemble flou, d�fini comme une fonction lin�aires par morceaux
 * @author Sylvia Vieira
 *
 */
public class SEF {
	/**
	 * borne inf�rieure de l'intervalle de d�finition du sous ensemble flou
	 */
	private double borneInf;
	/**
	 * borne sup�rieure de l'intervalle de d�finition du sous ensemble flou
	 */
	private double borneSup;
	/**
	 * liste des points d'inflexions de la fonction d'appartenance au sef (qui est lin�aire par morceaux)
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

	//Il faudra penser � ordonner la liste (en fct des abscisses) � un moment donn� 
	//=> o�? PB de mod�lisation, � voir plus tard!
	public void ajouterPointInflexion(Point p){
		inflexions.add(p);
	}	
	
	
	
}
