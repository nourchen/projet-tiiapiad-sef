package manipSef;

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;

import exceptions.NormalizationException;
import exceptions.SegmentAboveException;
import exceptions.SegmentsConfondusException;
import exceptions.UnknownOperationException;
import tools.MathSef;
import tools.OperationEnsembliste;
/**
 * Classe permettant d'effectuer une opération (Union/Intersection)
 * entre deux sous ensembles flous
 * selon la T-(co)norme de Lukasiewicz
 * @author Sylvia Vieira
 *
 */
public class SefLukaOperation {

	/**
	 * La méthode renvoyant le sous ensemble flou
	 * résultant de l'opération demandée (union ou intersection)
	 * entre deux sous ensembles flous
	 * par la T-(co)norme de Lukasiewicz
	 * 
	 * Cette opération est effectuée SANS discrétisation
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
	public static SEF getLukaResult(SEF sef1, SEF sef2, OperationEnsembliste operation)
			throws UnknownOperationException, NormalizationException{		

		/*
		 * La formule de Lukasiewicz :
		 * => pour l'intersection : max (y1 + y2 -1, 0)
		 * 		y1 le étant y du sef 1 et y2 le y du sef 2
		 * => pour l'union : min ( 1, y1 + y2)
		 * 
		 * 
		 * Donc finalement, il suffit d'appliquer le meme genre d'algo que pour
		 * Zadeh sauf que l'on comparera alors:
		 *   => la fonction y1 + y2 - 1  avec 0 pour l'intersection
		 *   => la fonction y1 + y2 avec 1 pour l'union
		 */

		XYSeries ptsSef1,ptsSef2;
//		sef1.printInflexions();
//		sef2.printInflexions();
		try {
			ptsSef1 = sef1.getInflexions().createCopy(0,sef1.getInflexions().getItemCount()-1);
			ptsSef1.setKey(sef1.getInflexions().getKey());
			ptsSef2 = sef2.getInflexions().createCopy(0,sef2.getInflexions().getItemCount()-1);
			ptsSef2.setKey(sef2.getInflexions().getKey());

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.out.println("Il y a eu un souci dans la copie de la liste de points du sef "
					+sef1.getInflexions().getKey());
			throw new NormalizationException();
		}
		
		try {
			SefDiscretizer.normalizeSerie(ptsSef1, sef1.getBorneInf(), sef1.getBorneSup(),
					ptsSef2,sef2.getBorneInf(), sef2.getBorneSup());
//			sef1.printInflexions();
//			sef2.printInflexions();
		} catch (NormalizationException e1) {
			// Auto-generated catch block
			e1.printStackTrace();
			System.out.println(e1.getErrorMsg());		
		}

		XYSeries resultPts;
		switch (operation) {
		case INTERSECTION:
			resultPts=new XYSeries("Intersection Lukasiewicz de "+ptsSef1.getKey()+" et: "+ptsSef2.getKey());
			break;
		case UNION:
			resultPts=new XYSeries("Union Lukasiewicz de "+ptsSef1.getKey()+" et: "+ptsSef2.getKey()); 
			break;
		default:
			throw new UnknownOperationException();
		}

		/*
		 * Pour Lukasiewicz, on n'aura pas besoin des points gauches et droits des deux sef (soit 4 points)
		 * Ici il nous faut uniquement les points gauche et droit de la fonction composée
		 * y1+y2 -1  pour l'intersection
		 * y1+y2 pour l'union 
		 */

		XYDataItem ptGauche1, ptDroit1, ptGauche2, ptDroit2, ptGaucheCompose,ptDroitCompose;
		double a1,b1,a2,b2, aCompose, bCompose;


		for(int i=0;i<ptsSef1.getItemCount() - 1;i++){
			ptGauche1 = ptsSef1.getDataItem(i);
			ptDroit1 = ptsSef1.getDataItem(i+1);
			ptGauche2 = ptsSef2.getDataItem(i);
			ptDroit2 = ptsSef2.getDataItem(i+1);

			//ATTENTION, ne gère pas les cas ou il y a des doublons de x dans les listes!!
			a1=(ptGauche1.getYValue()-ptDroit1.getYValue())/(ptGauche1.getXValue()-ptDroit1.getXValue());
			b1=ptDroit1.getYValue()-a1*ptDroit1.getXValue();
			a2=(ptGauche2.getYValue()-ptDroit2.getYValue())/(ptGauche2.getXValue()-ptDroit2.getXValue());
			b2=ptDroit2.getYValue()-a2*ptDroit2.getXValue();
			//System.out.println("La valeur de l'itérateur i d'incide de parcours des points: "+i);
			aCompose = a1 + a2;
			bCompose = b1 + b2 ;
			switch (operation) {
			case INTERSECTION:
				bCompose = bCompose - 1;
				break;
			case UNION:
				break;
			default:
				throw new UnknownOperationException();
			}
			//Creation des nouveaux points gauches et droit
			//En fait ceux de la fonction composée
			ptGaucheCompose = new XYDataItem(ptGauche1.getXValue(), aCompose*ptGauche1.getXValue()+bCompose);
			try {
				XYDataItem ptCommun;
				double inf,sup;
				inf=ptGauche1.getXValue();
				sup= ptDroit2.getXValue();
				switch (operation) {
				case INTERSECTION:
					ptCommun=MathSef.segmentIntersection(aCompose, bCompose, 0, 0, inf, sup);
					break;
				case UNION:
					ptCommun=MathSef.segmentIntersection(aCompose, bCompose, 0, 1, inf, sup);
					break;
				default:
					throw new UnknownOperationException();
				}


				/*
				 * Si on arrive ici, aucune exception n'a été générée(intersection dans l'intervalle considéré)
				 * Il faut => Ajouter le point gauche dont le y est le plus petit
				 * 		   => Ajouter le point d'intersection
				 */

				switch (operation) {
				case INTERSECTION:
					if(ptGaucheCompose.getYValue() < 0){
						/* le point gaucheCompose est sous la droite y=0,
						 *  il faut donc ajouter le point (xCourant,0) au sef intersection*/
						resultPts.add(ptGaucheCompose.getXValue(), 0);

					}else{//sinon on ajoute le pointCompose
						resultPts.add(ptGaucheCompose);
					}
					break;
				case UNION:
					if(ptGaucheCompose.getYValue() < 1){
						/* le point gaucheCompose est sous la droite y=1,
						 *  il faut donc ajouter le pointCompose au sef intersection*/
						resultPts.add(ptGaucheCompose);
					}else{//sinon on ajoute le point (xCourant,1)
						resultPts.add(ptGaucheCompose.getXValue(), 1);
					}
					break;
				default:
					throw new UnknownOperationException();
				}

				//On ajoute le point d'intersection
				//uniquement si celui ci n'est pas déjà une des extrémités d'un segment
				if(ptCommun.getXValue() != inf && ptCommun.getXValue()!=sup ){
					resultPts.add(ptCommun);
				}

			} catch (SegmentsConfondusException e) {
				//e.printStackTrace();

				/* Dans le cas ou les segments sont confondus, on ajoute
				 * au résultat de l'opération indifféremment le point Gauche du sef 1 ou du sef 2
				 */
				resultPts.add(ptGauche1);
			} catch (SegmentAboveException e) {
				//				e.printStackTrace();

				/* Ici on est dans le cas ou l'un de deux segments est au dessus de l'autre
				 * On ajoute donc le point Gauche qui est sous l'autre
				 */


				switch (operation) {
				case INTERSECTION:
					if(ptGaucheCompose.getYValue() < 0){
						/* le point gaucheCompose est sous la droite y=0,
						 *  il faut donc ajouter le point (xCourant,0) au sef intersection*/
						resultPts.add(ptGaucheCompose.getXValue(), 0);
					}else{//sinon on ajoute le pointCompose
						resultPts.add(ptGaucheCompose);
					}
					break;
				case UNION:
					if(ptGaucheCompose.getYValue() < 1){
						/* le point gaucheCompose est sous la droite y=1,
						 *  il faut donc ajouter le pointCompose au sef intersection*/
						resultPts.add(ptGaucheCompose);
					}else{//sinon on ajoute le point (xCourant,1)
						resultPts.add(ptGaucheCompose.getXValue(), 1);
					}
					break;
				default:
					throw new UnknownOperationException();
				}
			}

			//On ajoute le point droit si l'on arrive en bout de liste

			if (i == (ptsSef1.getItemCount() - 2)) {
				ptDroitCompose = new XYDataItem(ptDroit1.getXValue(), aCompose*ptDroit1.getXValue()+bCompose);
				//System.out.println("coucou");
				switch (operation) {
				case INTERSECTION:
					if(ptDroitCompose.getYValue() < 0){
						/* le point gaucheCompose est sous la droite y=0,
						 *  il faut donc ajouter le point (xCourant,0) au sef intersection*/
						resultPts.add(ptDroitCompose.getXValue(), 0);
					}else{//sinon on ajoute le pointCompose
						resultPts.add(ptDroitCompose);
					}
					break;
				case UNION:
					if(ptDroitCompose.getYValue() < 1){
						/* le point gaucheCompose est sous la droite y=1,
						 *  il faut donc ajouter le pointCompose au sef intersection*/
						resultPts.add(ptDroitCompose);
					}else{//sinon on ajoute le point (xCourant,1)
						resultPts.add(ptDroitCompose.getXValue(), 1);
					}
					break;
				default:
					throw new UnknownOperationException();
				}
			}
		}
		
		/*
		 * Les bornes sont les plus restreintes pour l'intersection
		 * et les plus larges pour l'union
		 */
		double resultInf, resultSup;
		SEF result;
		switch (operation) {
		case INTERSECTION: //les bornes doivent etre les plus restreintes
			resultInf = Math.max(sef1.getBorneInf(), sef2.getBorneInf());
			resultSup = Math.min(sef1.getBorneSup(), sef2.getBorneSup());
			break;
		case UNION: // bornes les plus larges
			resultInf = Math.min(sef1.getBorneInf(), sef2.getBorneInf());
			resultSup = Math.max(sef1.getBorneSup(), sef2.getBorneSup());
			break;
		default:
			throw new UnknownOperationException();
		}

		result = new SEF(resultInf,resultSup, resultPts);
		return result;
	}


}
