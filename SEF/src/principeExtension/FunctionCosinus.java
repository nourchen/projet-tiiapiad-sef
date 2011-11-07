package principeExtension;

import java.util.ArrayList;

/**
* Classe représentant la fonction cosinus
* @author Sylvia Vieira
*
*/
public class FunctionCosinus implements IMapping {

	/**
	 * borne inférieure de l'intervalle 
	 * sur lequel on considère la fonction
	 * (sorte de domaine)
	 */
	private double xDomainInf;
	/**
	 * borne supérieure de l'intervalle 
	 * sur lequel on considère la fonction
	 * (sorte de domaine)
	 */
	private double xDomainSup;
	
	/**
	 * Constructeur de la classe FunctionCosinus
	 * @param borneInf la borne inférieure du domaine
	 * @param borneSup la borne supérieure du domaine
	 */
	public FunctionCosinus(double borneInf,double borneSup) {
		this.xDomainInf=borneInf;
		this.xDomainSup=borneSup;
	}
	
	@Override
	public double compute(double x) {
		return Math.cos(x);
	}

	@Override
	public ArrayList<Double> reverse(double x) {
		double step = Math.PI * 2;
		double mesurePrincipale = Math.acos(x); //Valeur comprise entre 0 et Pi
		ArrayList<Double> antecedents = new ArrayList<Double>();
		double antecedentCourant=mesurePrincipale;
		double antecedentCourant2= -1 * mesurePrincipale;
		while (antecedentCourant - step >= xDomainInf){
			antecedentCourant -= step;
			antecedentCourant2 -= step;
		}
		// A ce niveau là antecedentCourant est le plus petit antecedent possible inclus dans l'intervalle
		
		//Pourquoi deux boucles? Pour avoir une liste d'antécédents ordonnées par x croissant!!
		while (antecedentCourant<xDomainSup){
			if(antecedentCourant2 >= xDomainInf && x!=1 && x!=-1){
				antecedents.add(antecedentCourant2);
			}
			antecedents.add(antecedentCourant);
			antecedentCourant+=step;
			antecedentCourant2+=step;
		}
		return antecedents;
	}

	public String toString(){
		return "cos (x)";
	}
}
