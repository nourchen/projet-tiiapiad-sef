package principeExtension;

import java.util.ArrayList;

public class FunctionAbs implements IMapping {

	@Override
	public double compute(double x) {

		return Math.abs(x);
	}

	@Override
	public ArrayList<Double> reverse(double x) {
		ArrayList<Double> antecedents = new ArrayList<Double>();
		if (x >= 0){
			antecedents.add(x);
			antecedents.add(-x);
		}
		return antecedents;
	}

	public String toString(){
		return "abs(x)";
	}

}
