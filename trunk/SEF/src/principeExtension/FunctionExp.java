package principeExtension;

import java.util.ArrayList;
/**
* Classe repr�sentant la fonction exponentielle
* @author Sylvia Vieira
*
*/
public class FunctionExp implements IMapping {

	@Override
	public double compute(double x) {

		return Math.exp(x);
	}

	@Override
	public ArrayList<Double> reverse(double x) {
		ArrayList<Double> antecedents = new ArrayList<Double>();
		if (x > 0){
			antecedents.add(Math.log(x));
		}
		return antecedents;
	}

	public String toString(){
		return "exp(x)";
	}

}
