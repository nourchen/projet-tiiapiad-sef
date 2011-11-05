package manipSef;

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;

import tools.MathSef;
import tools.OperationEnsembliste;
import exceptions.NormalizationException;
import exceptions.SegmentAboveException;
import exceptions.SegmentsConfondusException;
import exceptions.UnknownOperationException;

public class SefZadehOperation {


	public static SEF getZadehResult(SEF sef1, SEF sef2,OperationEnsembliste operation)
			throws UnknownOperationException, NormalizationException{
		
		XYSeries ptsSef1,ptsSef2;
		sef1.printInflexions();
		sef2.printInflexions();
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
			sef1.printInflexions();
			sef2.printInflexions();
		} catch (NormalizationException e1) {
			// Auto-generated catch block
			e1.printStackTrace();
			System.out.println(e1.getErrorMsg());		
		}

		XYSeries resultPts;
		switch (operation) {
		case INTERSECTION:
			resultPts=new XYSeries("Intersection Zadeh de "+ptsSef1.getKey()+" et: "+ptsSef2.getKey());
			break;
		case UNION:
			resultPts=new XYSeries("Union Zadeh de "+ptsSef1.getKey()+" et: "+ptsSef2.getKey()); 
			break;
		default:
			throw new UnknownOperationException();
		}

		XYDataItem ptGauche1, ptDroit1, ptGauche2, ptDroit2;
		double a1,b1,a2,b2;

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
			//			System.out.println("a1: "+ a1 + " b1 : "+b1);
			//			System.out.println("a2: "+ a2 + " b2 : "+b2);
			try {
				XYDataItem ptCommun;
				double inf,sup;
				inf=ptGauche1.getXValue();
				sup= ptDroit2.getXValue();
				ptCommun=MathSef.segmentIntersection(a1, b1, a2, b2, inf, sup);

				/*
				 * Si on arrive ici, aucune exception n'a été générée
				 * Donc il y a bien une intersection dans l'intervalle considéré
				 * Il faut donc:
				 * => Ajouter le point gauche dont le y est le plus petit
				 * => Ajouter le point d'intersection
				 */
				if(ptGauche1.getYValue() < ptGauche2.getYValue()){
					/* le point gauche 1 est sous le point gauche 2,
					 *  il faut donc ajouter le pointGauche 1 au sef intersection
					 *  Ou le point Gauche 2 au sef Union
					 */
					switch (operation) {
					case INTERSECTION:
						resultPts.add(ptGauche1);	
						break;
					case UNION:resultPts.add(ptGauche2); break;
					default:
						throw new UnknownOperationException();
					}

				}else{ // On ajoute sinon le point gauche 2 (Inter) ou le 1 (Union)
					switch (operation) {
					case INTERSECTION:
						resultPts.add(ptGauche2);	
						break;
					case UNION:resultPts.add(ptGauche1); break;
					default:
						throw new UnknownOperationException();
					}
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
				if(ptGauche1.getYValue() < ptGauche2.getYValue()){

					switch (operation) {
					case INTERSECTION:
						resultPts.add(ptGauche1);	
						break;
					case UNION:resultPts.add(ptGauche2); break;
					default:
						throw new UnknownOperationException();
					}
				}else{
					switch (operation) {
					case INTERSECTION:
						resultPts.add(ptGauche2);	
						break;
					case UNION:resultPts.add(ptGauche1); break;
					default:
						throw new UnknownOperationException();
					}
				}
			}
			//On ajoute le point droit si l'on arrive en bout de liste
			if (i == ptsSef1.getItemCount() - 2) {
				if(ptDroit1.getYValue() < ptDroit2.getYValue()){
					/* le point droit 1 est sous le point droit 2,
					 *  il faut donc ajouter le pointDroit 1 au sef intersection
					 *  OU  le pointDroit 2 au sef union
					 */
					switch (operation) {
					case INTERSECTION:
						resultPts.add(ptDroit1);	
						break;
					case UNION:resultPts.add(ptDroit2); break;
					default:
						throw new UnknownOperationException();
					}
				}else{ // On ajoute sinon l'autre point (selon union ou intersection
					switch (operation) {
					case INTERSECTION:
						resultPts.add(ptDroit2);	
						break;
					case UNION:resultPts.add(ptDroit1); break;
					default:
						throw new UnknownOperationException();
					}
				}
			}

		}

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
		result = new SEF(resultInf, resultSup, resultPts);
		return result;
	}

}
