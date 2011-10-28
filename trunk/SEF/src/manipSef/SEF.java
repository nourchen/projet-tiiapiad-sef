package manipSef;

import org.jfree.data.xy.XYSeries;


/**
 * Classe permettant de modeliser un sous ensemble flou, défini comme une fonction linéaires par morceaux
 * Le sous ensemble flou est donc caractérisé par des bornes inferieures et superieures
 * ainsi que par une liste de points d'inflexions.
 * @author Sylvia Vieira
 *
 */
public class SEF {
	/**
	 * borne inférieure de l'intervalle de définition du sous ensemble flou
	 * Pour l'affichage dans l'interface, sinon useless...
	 */
	private double borneInf;
	/**
	 * borne supérieure de l'intervalle de définition du sous ensemble flou
	 */
	private double borneSup;
	/**
	 * liste des points d'inflexions de la fonction d'appartenance au sef (qui est linéaire par morceaux)
	 */
	private XYSeries inflexions;

	/*
	 * a actualiser a la volee lors du remplissage des points dans le sef
	 * servira ensuite pour savoir quels sont les min et max (vraies bornes finalement) d'entre tous les sef rentrés
	 */
	private double minX;
	private double maxX;

	public SEF(double inf,double sup, XYSeries listePoints){
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
	 * avantage des XYSeries=>il me semble qu'ils ordonnent automatiquement les points par les x...
	 */

	public void ajouterPointsInflexions(double x, double y){
		//Modification des min et max a la volee
		if(inflexions.isEmpty()){

			this.minX=x;
			this.maxX=x;
		}else{
			minX=Math.min(minX, x);
			maxX=Math.min(maxX, x);
		}
		inflexions.add(x,y);
	}

	public XYSeries getInflexions() {
		return inflexions;
	}	

	public void printInflexions(){
		System.out.println("Liste des points dans le sef \""+inflexions.getKey()+"\": \n");
		for (int i = 0; i < inflexions.getItemCount(); i++) {
			System.out.println("x: "+inflexions.getDataItem(i).getXValue()+", ");
			System.out.println("y: "+inflexions.getDataItem(i).getYValue()+".\n");
		}
	}

}
