package principeExtension;

import java.util.ArrayList;

public class FunctionCosinus implements IMapping {

	@Override
	public double compute(double x) {
		return Math.cos(x);
	}

	@Override
	public ArrayList<Double> reverse(double x) {
		ArrayList<Double> antecedents = new ArrayList<Double>();
		// Vérifier comment fonction arcCos...ca me semble bien suspect quand meme!!
		//Normalement il y a plusieurs valeurs possibles!!!
		// Math.acos(x) modulo 2pi!! donc il faudrait avoir les bornes pour pouvoir avoir la liste des valeurs!!
		antecedents.add(Math.acos(x));
		return antecedents;
	}

}
