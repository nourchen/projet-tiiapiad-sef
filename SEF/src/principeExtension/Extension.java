package principeExtension;

import java.util.ArrayList;

import org.jfree.data.xy.XYSeries;

import exceptions.UnknownFunctionException;
import manipSef.SEF;
import manipSef.SefDiscretizer;
/**
 * Classe permettant d'effectuer l'extension d'un sous ensemble flou
 * par une fonction mathématique, ce qui donne alors un nouveau
 * sous ensemble flou.
 * @author Sylvia Vieira
 *
 */
public class Extension {

	/**
	 * La fonction mathématique utilisée pour appliquer le principe d'extension
	 */
	private IMapping func;
	/**
	 * La borne inférieure du domaine sur lequel on va l'appliquer
	 */
	private double inf;
	/**
	 * La borne supérieure du domaine sur lequel on va l'appliquer
	 */
	private double sup;
	/**
	 * Le sous ensemble flou subissant l'extension
	 */
	private SEF sefDiscretized;

	/**
	 * Constructeur de la classe Extension
	 * permet de créer un objet pour effectuer l'extension d'un sef
	 * @param sef le sef a étendre
	 * @param Xinf la borne inférieure
	 * @param Xsup la borne supérieure
	 * @param f la fonction à appliquer (un élément de enum FunctionChoice)
	 * @throws UnknownFunctionException : si la fonction n'est pas présente dans le catalogue(enum)
	 */
	public Extension(SEF sef,double Xinf,double Xsup, FunctionChoice f) throws UnknownFunctionException {
		switch (f) {
		case COSINUS:
			this.func = new FunctionCosinus(Xinf,Xsup); 
			break;
		case SQUARE:
			this.func = new FunctionSquare();
			break;
		case ABS:
			this.func = new FunctionAbs();
			break;
		case EXPONENTIAL:
			this.func = new FunctionExp();
			break;
		case SINUS:
			this.func = new FunctionSinus(Xinf, Xsup);
			break;
		default:
			throw new UnknownFunctionException();
		}
		this.inf=Xinf;
		this.sup=Xsup;
		this.sefDiscretized = SefDiscretizer.discretizeSef(sef, Xinf, Xsup, 1000);
	}

	/**
	 * La fonction permettant de renvoyerle nouveau sous ensemble flou image par 
	 * l'extension de la fonction mathématique
	 * @return
	 */
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
			if(antecedents.isEmpty()){
				ptsSefImage.add(y, 0);
				//				System.out.println("x: "+ y +" y: 0");
			}else{
				double sup = sefDis.getMinY();
				double a,b;
				int nb_dec =100;
				int mult = (int) Math.log10(sefImageBorneSup-sefImageBorneInf);
//				System.out.println("mult: "+mult);
				if ( mult!= 0  ){
					nb_dec = nb_dec * (int) Math.pow(10, mult);
				}
//				System.out.println("nb_dec "+nb_dec);
				for (int i=0;i < antecedents.size(); i++){
					// Pour chaque antecedent, on cherche sa valeur d'appartenance associée
					for(int indiceX =0; indiceX < sefDis.getItemCount(); indiceX++){
						a = sefDis.getDataItem(indiceX).getXValue();
						b = antecedents.get(i);

						a =((double) ((int) (a * nb_dec)))/nb_dec ;
						b = ((double) ((int) (b * nb_dec)))/nb_dec ;
						if (  a	== b ){
							sup = Math.max(sup, sefDis.getDataItem(indiceX).getYValue());
							break;
						}
					}
				}
				// Ici on doit ajouter au sef image le point (y, sup)  (sup ici étant sup(fa(x)))
				ptsSefImage.add(y, sup);
				//				System.out.println("x: "+ y +" y: "+sup);
			}
		}

		SEF etendu = new SEF(inf, sup, ptsSefImage);
//		etendu.printInflexions();
		return etendu;
	}

}
