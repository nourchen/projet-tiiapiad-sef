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
	 * Pour l'affichage dans l'interface, sinon useless...
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
	
	/*
	 * a actualiser a la volee lors du remplissage des points dans le sef
	 * servira ensuite pour savoir quels sont les min et max (vraies bornes finalement) d'entre tous les sef rentr�s
	 */
	private double minX;
	private double maxX;
	
	public SEF(double inf,double sup,ArrayList<Point> listePoints){
		borneInf = inf;
		borneSup = sup;
		inflexions=listePoints;
	}
	
	public SEF(double inf, double sup){//preferable, mieux vaut ajouter les points au fur et a mesure de la lecture du fichier
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

	/*
	 * Il faudra penser � ordonner la liste (en fct des abscisses) � un moment donn� 
	 * => o�? PB de mod�lisation, � voir plus tard!
	 * 
	 * Au pire (selon MJ Lesot) preciser dans les spec qu'une liste ordonnee est attendue
	 * Sinon, avantage des XYSeries=>il me semble qu'ils ordonnent automatiquement les points par les x...
	 */
	
	public void ajouterPointsInflexions(Point p){
		//Modification des min et max a la volee
		if(inflexions.isEmpty()){
			
			this.minX=p.getX();
			this.maxX=p.getX();
		}else{
			minX=Math.min(minX, p.getX());
			maxX=Math.min(maxX, p.getX());
		}
		inflexions.add(p);
	}

	public ArrayList<Point> getInflexions() {
		return inflexions;
	}	
	
	
	
}
