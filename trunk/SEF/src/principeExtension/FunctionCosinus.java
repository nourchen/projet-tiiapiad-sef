package principeExtension;

import java.util.ArrayList;

public class FunctionCosinus implements IMapping {

	private double xDomainInf;
	private double xDomainSup;
	
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
		double mesurePrincipale = Math.acos(x);
		ArrayList<Double> antecedents = new ArrayList<Double>();
		double antecedentCourant=mesurePrincipale;
		while (antecedentCourant - step >= xDomainInf){
			antecedentCourant-= step;
		}
		// A ce niveau là antecedentCourant est le plus petit antecedent possible inclus dans l'intervalle
		
		//Pourquoi deux boucles? Pour avoir une liste d'antécédents ordonnées par x croissant!!
		while (antecedentCourant<xDomainSup){
			antecedents.add(antecedentCourant);
			antecedentCourant+=step;
		}
		return antecedents;
	}

	public String toString(){
		return "cos x";
	}
}
