package manipSef;

import java.util.ArrayList;

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;

import exceptions.SegmentAboveException;
import exceptions.SegmentsConfondusException;

public final class SefIntersection {

	public SefIntersection(){
	}

	public static SEF getIntersection(SEF sef1, SEF sef2,Norme tnorme){
		SEF inter;
		switch (tnorme)
		{
		case LUKASIEWICZ: inter = getIntersectionLuka(sef1, sef2);
		case PROBA: inter = getIntersectionProba(sef1, sef2);
		case ZADEH: inter = getIntersectionZadeh(sef1, sef2);
		default: inter =null;

		}
		return inter;
	}

	private static SEF getIntersectionZadeh(SEF sef1, SEF sef2){
		XYSeries ptsSef1,ptsSef2;
		ptsSef1 = sef1.getInflexions();
		ptsSef2 = sef2.getInflexions();
		//normalizeSerie(ptsSef1,ptsSef2.getMinX(),ptsSef2.getMaxX(),sef1.getBorneInf(),sef1.getBorneSup());
		//normalizeSerie(ptsSef2,ptsSef1.getMinX(),ptsSef1.getMaxX(),sef2.getBorneInf(),sef2.getBorneSup());

		XYSeries interPts=new XYSeries("Intersection de "+ptsSef1.getDescription()+" et: "+ptsSef2.getDescription());
		int indiceParcoursSef1;
		int indiceParcoursSef2=0;
		XYDataItem ptGauche1, ptDroit1, ptGauche2, ptDroit2;
		double a1,b1,a2,b2, xInf,xSup;
		ptGauche1 = ptsSef1.getDataItem(0);
		ptDroit1 = ptsSef1.getDataItem(1);
		ptGauche2 = ptsSef2.getDataItem(indiceParcoursSef2);
		ptDroit2 = ptsSef2.getDataItem(indiceParcoursSef2+1);
		a1=(ptGauche1.getYValue()-ptDroit1.getYValue())/(ptGauche1.getXValue()-ptDroit1.getXValue());
		b1=ptDroit1.getYValue()-a1*ptDroit1.getXValue();
		a2=(ptGauche2.getYValue()-ptDroit2.getYValue())/(ptGauche2.getXValue()-ptDroit2.getXValue());
		b2=ptDroit2.getYValue()-a2*ptDroit2.getXValue();

		ArrayList<CouplePointSef> candidats = new ArrayList<CouplePointSef>();
		candidats.add(new CouplePointSef(ptGauche1, 1));
		candidats.add(new CouplePointSef(ptDroit1, 1));
		candidats.add(new CouplePointSef(ptGauche2, 2));
		candidats.add(new CouplePointSef(ptGauche2, 2));
		while (!candidats.isEmpty()){

		}
		for(indiceParcoursSef1=1 ; indiceParcoursSef1 < ptsSef1.getItemCount() - 1; indiceParcoursSef1++){
			//xInf=Math.min(arg0, arg1)
			if (ptDroit1.getXValue() < ptDroit2.getXValue()){
				try {
					segmentIntersection(a1, b1, a2, b2, ptGauche1.getXValue(), ptDroit1.getXValue());
				} catch (SegmentsConfondusException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SegmentAboveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					segmentIntersection(a1, b1, a2, b2, ptGauche1.getXValue(), ptDroit2.getXValue());
				} catch (SegmentsConfondusException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SegmentAboveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}


			//Penser à incrémenter indiceParcoursSef2 au bon moment!!
		}

		//ATTENTION à modifier!! TODO
		SEF inter = null;// new SEF(double borneInf, double borneSup, interPts);
		return inter;
	}


	private static SEF getIntersectionLuka(SEF sef1, SEF sef2){
		//TODO
		return null;//new SEF(inf, sup);
	}

	private static SEF getIntersectionProba(SEF sef1, SEF sef2){
		//TODO
		return null;//new SEF(inf, sup);
	}

	/**
	 * Cette methode permet de normaliser les deux sef (représentés ici par la liste de leur points d'inflexions respectifs)
	 * En d'autres termes, en sortie, les points d'inflexion des deux sefs seront définis selon les memes abscisses
	 * donc les deux listes de points auront la meme longueur.
	 * @param toNormalize1
	 * @param inf1
	 * @param sup1
	 * @param toNormalize2
	 * @param inf
	 * @param sup
	 */
	public static void normalizeSerie(XYSeries toNormalize1,double inf1,double sup1, XYSeries toNormalize2,double inf,double sup){
		double xminRef,xmaxRef,xminRef2,xmaxRef2;
		xminRef=toNormalize2.getMinX();
		xmaxRef=toNormalize2.getMaxX();
		xminRef2=toNormalize1.getMinX();
		xmaxRef2=toNormalize1.getMaxX();

		//On commence par ajouter à chaque sef, si nécessaire
		// des couples de points, de sorte que les points "a l'extremite" des deux sef auront les meme abscisses
		XYDataItem firstPoint = toNormalize1.getDataItem(0);
		XYDataItem lastPoint = toNormalize1.getDataItem(toNormalize1.getItemCount()-1);
		if( xminRef< firstPoint.getXValue()){
			if(xminRef < inf){
				toNormalize1.add(xminRef, 0 );
			}else{
				toNormalize1.add(xminRef, firstPoint.getYValue() );
			}
		}
		if( xmaxRef > lastPoint.getXValue()){
			if(xmaxRef > sup){
				toNormalize1.add(xmaxRef, 0 );
			}else{
				toNormalize1.add(xmaxRef, lastPoint.getYValue() );
			}
		}
		XYDataItem firstPoint2 = toNormalize2.getDataItem(0);
		XYDataItem lastPoint2 = toNormalize2.getDataItem(toNormalize2.getItemCount()-1);
		if( xminRef2< firstPoint2.getXValue()){
			if(xminRef2 < inf1){
				toNormalize2.add(xminRef2, 0 );
			}else{
				toNormalize2.add(xminRef2, firstPoint2.getYValue() );
			}
		}
		if( xmaxRef2 > lastPoint2.getXValue()){
			if(xmaxRef2 > sup1){
				toNormalize2.add(xmaxRef2, 0 );
			}else{
				toNormalize2.add(xmaxRef2, lastPoint2.getYValue() );
			}
		}

		//Balayons maintenant les deux sefs en ajoutant a chacun les points manquants
		int i1,i2;
		i2=1;
		XYDataItem ptG1,ptD1,ptG2,ptD2;
		ptG1 = toNormalize1.getDataItem(0);
		ptG2 = toNormalize2.getDataItem(0);
		ptD1 = toNormalize1.getDataItem(1);
		ptD2 = toNormalize2.getDataItem(1);
		double a1,a2,b1,b2;
		i1=1;
		a2=(ptG2.getYValue()-ptD2.getYValue())/(ptG2.getXValue()-ptD2.getXValue());
		b2=ptD2.getYValue()-a2*ptD2.getXValue();

		while (i1< (toNormalize1.getItemCount()-1) || i2 < (toNormalize2.getItemCount() -1)){
			a1=(ptG1.getYValue()-ptD1.getYValue())/(ptG1.getXValue()-ptD1.getXValue());
			b1=ptD1.getYValue()-a1*ptD1.getXValue();

			while(ptD2.getXValue() < ptD1.getXValue()){

				toNormalize1.add(ptD2.getXValue(), ptD2.getXValue()* a1 + b1);

				i2++;
				i1++;
				ptG2=ptD2;
				ptD2=toNormalize2.getDataItem(i2);
				a2=(ptG2.getYValue()-ptD2.getYValue())/(ptG2.getXValue()-ptD2.getXValue());
				b2=ptD2.getYValue()-a2*ptD2.getXValue();
			}//xD2 > = xD1
			if(i1 < toNormalize1.getItemCount()-1 && ptD1.getXValue()!=ptD2.getXValue()){
				toNormalize2.add(ptD1.getXValue(), ptD1.getXValue()* a2 + b2);
				i1++;
				i2++;
				ptG1=ptD1;
				if(i1<(toNormalize1.getItemCount())){
					ptD1=toNormalize1.getDataItem(i1);	
				}
			}
			
			if(ptD1.getXValue()==ptD2.getXValue()){
				i1++;
				i2++;
				ptG1=ptD1;
				if(i1<(toNormalize1.getItemCount())){
					ptD1=toNormalize1.getDataItem(i1);	
				}
				ptG2=ptD2;
				if(i1<(toNormalize2.getItemCount())){
					ptD2=toNormalize2.getDataItem(i2);
				}
			}

		}
	}

	private static XYDataItem segmentIntersection(double a1,double b1, double a2, double b2,double inf, double sup)
			throws SegmentsConfondusException,SegmentAboveException{
		if (a1==a2 && b1 ==b2){
			throw new SegmentsConfondusException();
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
