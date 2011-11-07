package principeExtension;

import java.util.ArrayList;
/**
* Classe repr�sentant la fonction sinus
* @author Sylvia Vieira
*
*/
public class FunctionSinus implements IMapping {
	/**
	 * borne inf�rieure de l'intervalle 
	 * sur lequel on consid�re la fonction
	 * (sorte de domaine)
	 */
	private double xDomainInf;
	/**
	 * borne sup�rieure de l'intervalle 
	 * sur lequel on consid�re la fonction
	 * (sorte de domaine)
	 */
	private double xDomainSup;
	/**
	 * Constructeur de la classe FunctionSinus
	 * @param borneInf la borne inf�rieure du domaine
	 * @param borneSup la borne sup�rieure du domaine
	 */
	public FunctionSinus(double borneInf,double borneSup) {
		this.xDomainInf=borneInf;
		this.xDomainSup=borneSup;
	}
	
	@Override
	public double compute(double x) {
		return Math.sin(x);
	}

	@Override
	public ArrayList<Double> reverse(double x) {
		double step = Math.PI * 2;
		double mesurePrincipale = Math.asin(x); //dans l'intervalle [-pi/2;pi/2]
		ArrayList<Double> antecedents = new ArrayList<Double>();
		double antecedentCourant=mesurePrincipale;
		double antecedentCourant2=Math.PI - mesurePrincipale;
		while (antecedentCourant - step >= xDomainInf){
			antecedentCourant-= step;
			antecedentCourant2-= step;
		}
		// A ce niveau l� antecedentCourant est le plus petit antecedent possible inclus dans l'intervalle
		
		//Pourquoi deux boucles? Pour avoir une liste d'ant�c�dents ordonn�es par x croissant!!
		while (antecedentCourant<xDomainSup){
			antecedents.add(antecedentCourant);
			if(antecedentCourant2 >= xDomainInf && antecedentCourant2 < xDomainSup&&x!=1 && x!=-1){
				antecedents.add(antecedentCourant2);
			}
			antecedentCourant+=step;
			antecedentCourant2+=step;
		}
		return antecedents;
	}

	public String toString(){
		return "sin (x)";
	}

}
