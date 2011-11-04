package principeExtension;

import java.util.ArrayList;

public class Carree implements IMapping{

	@Override
	public double compute(double x) {
		return (x * x);
	}

	@Override
	public ArrayList<Double> reverse(double x) {
		// TODO Auto-generated method stub
		return null;
	}

}
