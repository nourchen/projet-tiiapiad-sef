package manipSef;

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;

import exceptions.NormalizationException;
import exceptions.UnknownOperationException;
import tools.OperationEnsembliste;
/**
 * Classe permettant d'effectuer une opération (Union/Intersection)
 * entre deux sous ensembles flous
 * selon la T-(co)norme probabiliste
 * @author Sylvia Vieira
 *
 */
public class SefProbaOperation {

	/**
	 * La méthode renvoyant le sous ensemble flou
	 * résultant de l'opération demandée (union ou intersection)
	 * entre deux sous ensembles flous
	 * par la T-(co)norme probabiliste
	 * 
	 * Cette opération a necessité de procéder à une discrétisation.
	 * 
	 * @param sef1 premier sous ensemble flou
	 * @param sef2 second sous ensemble flou
	 * @param operation l'opération que l'on veut effectuer: Intersection ou Union
	 * @return : le sous ensemble flou résultant de l'opération demandée
	 * @throws UnknownOperationException : si l'opération demandée
	 * n'est pas connue 
	 * @throws NormalizationException : si un problème s'est produit lors de la normalisation
	 * des deux ou de l'un des deux sous ensembles flous passées en paramètres
	 */
	public static SEF getProbaResult(SEF sef1, SEF sef2,OperationEnsembliste operation) throws UnknownOperationException{

		double inf,sup;
		SEF result;
		String ope;
		switch (operation) {
		case INTERSECTION:
			if(sef1.getInflexions().getDataItem(0).getYValue()== 0
			&& sef2.getInflexions().getDataItem(0).getYValue()== 0){
				inf= Math.max(sef1.getInflexions().getMinX(), sef2.getInflexions().getMinX());
			}else{
				inf= Math.min(sef1.getInflexions().getMinX(), sef2.getInflexions().getMinX());
			}
			if(sef1.getInflexions().getDataItem(sef1.getInflexions().getItemCount()-1).getXValue() == 0
					&& sef2.getInflexions().getDataItem(sef2.getInflexions().getItemCount()-1).getXValue() == 0 )
			{
				sup=Math.min(sef1.getInflexions().getMaxX(), sef2.getInflexions().getMaxX());
			}else{
				sup=Math.max(sef1.getInflexions().getMaxX(), sef2.getInflexions().getMaxX());
			}
			ope="Intersection ";
			break;
		case UNION:
			inf= Math.min(sef1.getInflexions().getMinX(), sef2.getInflexions().getMinX());
			sup=Math.max(sef1.getInflexions().getMaxX(), sef2.getInflexions().getMaxX());
			ope = "Union ";
			break;
		default:
			throw new UnknownOperationException();
		}
		
		XYSeries listeResult = new XYSeries(ope+"probabiliste de "
				+sef1.getInflexions().getKey()+" et de "+ sef2.getInflexions().getKey());

		SEF sef1Discretized=SefDiscretizer.discretizeSef(sef1, inf, sup, 1000);
		SEF sef2Discretized= SefDiscretizer.discretizeSef(sef2, inf, sup, 1000);
		
		for(int i =0;i < sef1Discretized.getInflexions().getItemCount();i++){
			double x,y;
			XYDataItem pointSef1, pointSef2;
			pointSef1 = sef1Discretized.getInflexions().getDataItem(i);
			pointSef2 = sef2Discretized.getInflexions().getDataItem(i);
			x= pointSef1.getXValue();
			y= computeAppartenance(pointSef1.getYValue(),pointSef2.getYValue(),operation);
			listeResult.add(x, y);
		}
		result = new SEF(inf, sup, listeResult);
		return result;
	}

	private static double computeAppartenance(double y1, double y2, OperationEnsembliste ope) 
			throws UnknownOperationException{

		switch (ope) {
		case INTERSECTION:
			return y1 * y2;
		case UNION:
			return y1 + y2 - y1 * y2;
		default:
			throw new UnknownOperationException();
		}
	}
}
