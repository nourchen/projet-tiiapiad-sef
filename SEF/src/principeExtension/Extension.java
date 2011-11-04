package principeExtension;

import org.jfree.data.xy.XYSeries;

import exceptions.UnknownFunctionException;
import manipSef.SEF;

public class Extension {

	private IMapping func;
	private double inf;
	private double sup;
	private SEF sef;

	public Extension(SEF sef,double Xinf,double Xsup, FunctionChoice f) throws UnknownFunctionException {
		switch (f) {
		case COS:
			this.func = new FunctionCosinus(Xinf,Xsup); 
			break;
		case SQUARE:
			this.func = new FunctionSquare();
			break;
		default:
			throw new UnknownFunctionException();
		}
		this.inf=Xinf;
		this.sup=Xsup;
		this.sef = sef;
	}


	public SEF getExtendedSef(){
		XYSeries ptsSefImage = new XYSeries(sef.getInflexions().getKey()+ " étendu par la fonction "+func.toString());

		//On discrétise le sef sur l'intervalle donné
		
		//Tout d'abord on se crée une liste d'image par mapping de compute sur l'ensemble des x discrétisés

		return new SEF(inf, sup, ptsSefImage);
	}

}
