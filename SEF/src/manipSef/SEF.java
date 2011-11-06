package manipSef;

import org.jfree.data.xy.XYSeries;


/**
 * Classe permettant de modeliser un sous ensemble flou, 
 * d�fini comme une fonction lin�aire par morceaux.
 * Le sous ensemble flou est donc caract�ris� par des bornes inferieure et superieure
 * ainsi que par une liste de points d'inflexions.
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
	private XYSeries inflexions;

	/**
	 * Constructeur de la classe SEF, permet de construire un
	 * nouveau sous ensemble flou
	 * @param inf : la borne inf�rieure du sous ensemble flou
	 * @param sup : la borne sup�rieure du sous ensemble flou
	 * @param listePoints : la liste des points d'inflexions caract�risant
	 * le sous ensemble flou (qui est lin�aire par morceaux)
	 */
	public SEF(double inf,double sup, XYSeries listePoints){
		borneInf = inf;
		borneSup = sup;
		inflexions=listePoints;
	}

	/**
	 * getter sur le champ borneInf
	 * @return la borne inf�rieure du sous ensemble flou
	 */
	public double getBorneInf() {
		return borneInf;
	}
	/**
	 * getter sur le champ borneSup
	 * @return la borne sup�rieure du sous ensemble flou
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
	 * M�thode permettant d'afficher la liste des points 
	 * d'inflexions caract�risant le SEF
	 * -cette m�thode peut s'av�rer utile, notamment au debug-
	 */
	public void printInflexions(){
		System.out.println("Liste des points dans le sef \""+inflexions.getKey()+"\": \n");
		for (int i = 0; i < inflexions.getItemCount(); i++) {
			System.out.println("x: "+inflexions.getDataItem(i).getXValue()+", ");
			System.out.println("y: "+inflexions.getDataItem(i).getYValue()+".\n");
		}
	}

}
