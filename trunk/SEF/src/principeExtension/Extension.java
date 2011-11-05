package principeExtension;

import java.util.ArrayList;

import org.jfree.data.xy.XYSeries;

import exceptions.UnknownFunctionException;
import manipSef.SEF;
import manipSef.SefDiscretizer;

public class Extension {

	private IMapping func;
	private double inf;
	private double sup;
	private SEF sefDiscretized;

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
		this.sefDiscretized = SefDiscretizer.discretizeSef(sef, Xinf, Xsup, 1000);
	}


	public SEF getExtendedSef(){
		XYSeries ptsSefImage = new XYSeries(sefDiscretized.getInflexions().getKey()+ " étendu par la fonction "+func.toString());
		XYSeries sefDis = this.sefDiscretized.getInflexions();
		double sefImageBorneInf, sefImageBorneSup;
		ArrayList<Double> listeImages = new ArrayList<Double>();
		
		//Tout d'abord on se crée une liste d'image (création du domaine "Y") 
		//par mapping de compute sur l'ensemble des x discrétisés
		sefImageBorneInf = func.compute(sefDis.getDataItem(0).getXValue());
		sefImageBorneSup = sefImageBorneInf;
		for(int i =0; i < sefDis.getItemCount(); i++){
			Double y = func.compute(sefDis.getDataItem(i).getXValue());
			listeImages.add(y);
			sefImageBorneInf = Math.min(sefImageBorneInf, y);
			sefImageBorneSup = Math.max(sefImageBorneSup, y);
		}
		
		/*
		 * Maintenant pour chaque y de la liste d'images:
		 * => on cherche à avoir l'ensemble de ses antécédents
		 * 		=> Sur cet ensemble d'antécédents x on récupère les valeur d'appartenance
		 * 			associée et on prend le max.
		 */
		
		for(int indiceImage = 0; indiceImage<listeImages.size();indiceImage++){ // Pr chq y de la liste d'images
			double y = listeImages.get(indiceImage);
			ArrayList<Double> antecedents = func.reverse(y);
			double sup = sefDis.getMinY();
			
			for (int i=0;i < antecedents.size(); i++){
				// Pour chaque antecedent, on cherche sa valeur d'appartenance associée
				
				for(int indiceX =0; indiceX < sefDis.getItemCount(); indiceX++){
					if (sefDis.getDataItem(indiceX).getXValue() == antecedents.get(i) ){
						sup = Math.max(sup, sefDis.getDataItem(indiceX).getYValue());
						break;
					}
					
				}
			}
			// Ici on doit ajouter au sef image le point (y, sup)  (sup ici étant sup(fa(x)))
			ptsSefImage.add(y, sup);
		}
		

		return new SEF(inf, sup, ptsSefImage);
	}

}
