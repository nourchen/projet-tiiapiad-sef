package manipSef;

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;

import exceptions.DroitesConfonduesException;
import exceptions.NoIntersectionException;
import exceptions.SegmentAboveException;

public final class SefIntersection {

	public SefIntersection(){
	}

	public static SEF getIntersection(SEF sef1, SEF sef2,Norme tnorme){
		XYSeries ptsSef1,ptsSef2;
		ptsSef1 = sef1.getInflexions();
		ptsSef2 = sef2.getInflexions();
		normalizeSerie(ptsSef1,ptsSef2.getMinX(),ptsSef2.getMaxX(),sef1.getBorneInf(),sef1.getBorneSup());
		normalizeSerie(ptsSef2,ptsSef1.getMinX(),ptsSef1.getMaxX(),sef2.getBorneInf(),sef2.getBorneSup());

		XYSeries interPts=new XYSeries("Intersection de "+ptsSef1.getDescription()+" et: "+ptsSef2.getDescription());
		int indiceParcoursSef1;
		int indiceParcoursSef2=0;
		XYDataItem ptGauche1, ptDroit1, ptGauche2, ptDroit2;
		double a1,b1,a2,b2;
		ptGauche1 = ptsSef1.getDataItem(0);
		ptDroit1 = ptsSef1.getDataItem(1);
		ptGauche2 = ptsSef2.getDataItem(indiceParcoursSef2);
		ptDroit2 = ptsSef2.getDataItem(indiceParcoursSef2+1);
		a1=(ptGauche1.getYValue()-ptDroit1.getYValue())/(ptGauche1.getXValue()-ptDroit1.getXValue());
		b1=ptDroit1.getYValue()-a1*ptDroit1.getXValue();
		a2=(ptGauche2.getYValue()-ptDroit2.getYValue())/(ptGauche2.getXValue()-ptDroit2.getXValue());
		b2=ptDroit2.getYValue()-a2*ptDroit2.getXValue();

		for(indiceParcoursSef1=1 ; indiceParcoursSef1 < ptsSef1.getItemCount() - 1; indiceParcoursSef1++){
			


			//Penser à incrémenter indiceParcoursSef2 au bon moment!!
		}

		//ATTENTION, les bornes sont a modifier...juste pour passer la compil pour le moment TODO
		SEF inter = new SEF(-5, 1000, interPts);
		return inter;
	}

	/**
	 * méthode permettant de considérer les deux sefs sur le meme intervalle 
	 * cad d'ajouter artificiellement des pts d'inflexion histoire de faciliter la lecture pour l'intersection
	 * @param toNormalize: séries de points à normaliser
	 * @param ptRefMin : 
	 * @param ptRefMax
	 * @return 
	 */
	private static void normalizeSerie(XYSeries toNormalize, double xminRef, double xmaxRef,double inf,double sup){
		XYDataItem firstPoint = toNormalize.getDataItem(0);
		XYDataItem lastPoint = toNormalize.getDataItem(toNormalize.getItemCount()-1);
		if( xminRef< firstPoint.getXValue()){
			if(xminRef < inf){
				toNormalize.add(xminRef, 0 );
			}else{
				toNormalize.add(xminRef, firstPoint.getYValue() );
			}
		}
		if( xmaxRef > lastPoint.getXValue()){
			if(xmaxRef > sup){
				toNormalize.add(xmaxRef, 0 );
			}else{
				toNormalize.add(xmaxRef, lastPoint.getYValue() );
			}
		}
	}

	private static XYDataItem intersection(double a1,double b1, double a2, double b2,double inf, double sup)
			throws NoIntersectionException, DroitesConfonduesException,SegmentAboveException{
		if (a1==a2){
			if(b1 ==b2){
				throw new DroitesConfonduesException();
			}
			throw new NoIntersectionException();
		}
		double y1gauche,y2gauche,y1droit,y2droit;
		y1gauche=a1*inf +b1;
		y1droit=a1*sup +b1;
		y2gauche=a2*inf +b2;
		y2droit=a2*sup +b2;
		if (y1gauche > y2gauche && y1droit > y2droit){
			throw new SegmentAboveException(true);
		}
		if (y1gauche < y2gauche && y1droit < y2droit){
			throw new SegmentAboveException(false);
		}
		double xCommun = (b1 - b2) / (a1 - a2);
		XYDataItem ptCommun=new XYDataItem(xCommun, a1 * xCommun + b1);
		
		return ptCommun;
	}

}
