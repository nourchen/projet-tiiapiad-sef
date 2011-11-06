package manipSef;

import org.jfree.data.xy.XYSeries;


/**
 * Classe permettant de modeliser un sous ensemble flou, 
 * défini comme une fonction linéaire par morceaux.
 * Le sous ensemble flou est donc caractérisé par des bornes inferieure et superieure
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

	/**
	 * Constructeur de la classe SEF, permet de construire un
	 * nouveau sous ensemble flou
	 * @param inf : la borne inférieure du sous ensemble flou
	 * @param sup : la borne supérieure du sous ensemble flou
	 * @param listePoints : la liste des points d'inflexions caractérisant
	 * le sous ensemble flou (qui est linéaire par morceaux)
	 */
	public SEF(double inf,double sup, XYSeries listePoints){
		borneInf = inf;
		borneSup = sup;
		inflexions=listePoints;
	}

	/**
	 * getter sur le champ borneInf
	 * @return la borne inférieure du sous ensemble flou
	 */
	public double getBorneInf() {
		return borneInf;
	}
	/**
	 * getter sur le champ borneSup
	 * @return la borne supérieure du sous ensemble flou
	 */
	public double getBorneSup() {
		return borneSup;
	}
	/**
	 * getter sur le champ inflexions
	 * @return la liste des points d'inflexion du sous ensemble flou
	 */
	public XYSeries getInflexions() {
		return inflexions;
	}	

	/**
	 * Méthode permettant d'afficher la liste des points 
	 * d'inflexions caractérisant le SEF
	 * -cette méthode peut s'avérer utile, notamment au debug-
	 */
	public void printInflexions(){
		System.out.println("Liste des points dans le sef \""+inflexions.getKey()+"\": \n");
		for (int i = 0; i < inflexions.getItemCount(); i++) {
			System.out.println("x: "+inflexions.getDataItem(i).getXValue()+", ");
			System.out.println("y: "+inflexions.getDataItem(i).getYValue()+".\n");
		}
	}

}
