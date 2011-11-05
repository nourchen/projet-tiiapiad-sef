package principeExtension;

import java.util.ArrayList;
/**
* Classe représentant la fonction carrée
 * @author Sylvia Vieira
 *
 */
public class FunctionSquare implements IMapping {

	@Override
	public double compute(double x) {

		return Math.pow(x, 2);
	}

	@Override
	public ArrayList<Double> reverse(double x) {
		ArrayList<Double> antecedents = new ArrayList<Double>();
		if (x >= 0){
			antecedents.add(Math.sqrt(x));
			antecedents.add(- Math.sqrt(x));
		}
		return antecedents;
	}

	public String toString(){
		return "x²";
	}
}
