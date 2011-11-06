package tests;

import manipSef.SEF;
import manipSef.SefDiscretizer;

import org.jfree.data.xy.XYSeries;

import exceptions.NormalizationException;

/**
 * Classe permettant de tester à loisir la fonction de normalisation de deux sefs
 * La normalisation étant le prérequis, dans notre modèle, de toute opération
 * ensembliste impliquant deux sefs
 * @author Sylvia Vieira
 *
 */
public class TestNormalize {

	public static void main(String[] args) {
		/*
		SEF sef1 = new SEF(-30, 60, new XYSeries("sef1"));
		SEF sef2 = new SEF(-30, 60, new XYSeries("sef2"));
		SEF sef3 = new SEF(-30, 60, new XYSeries("sef3"));
		SEF sef4 = new SEF(-30, 60, new XYSeries("sef4"));

		sef1.getInflexions().add(-1.5,0);
		sef1.getInflexions().add(1.25,1);
		sef1.getInflexions().add(4.2,0.75);
		sef1.getInflexions().add(5,0);*/
		/*
		sef2.getInflexions().add(-0.5,0.6);
		sef2.getInflexions().add(0,1);
		sef2.getInflexions().add(0.5,0);
		 */

		/*		sef4.getInflexions().add(-0.5,0.6);
		sef4.getInflexions().add(0,0.8);
		sef4.getInflexions().add(0.5,0);


		sef2.getInflexions().add(3,0);
		sef2.getInflexions().add(4,1);
		sef2.getInflexions().add(5,0);
		sef2.getInflexions().add(6, 0.7);
		
		sef4.getInflexions().add(-3,0);
		sef4.getInflexions().add(0,1);
		sef4.getInflexions().add(1,1);

		sef3.getInflexions().add(-6, 0);
		sef3.getInflexions().add(-4, 0.75);
		sef3.getInflexions().add(2, 0.75);
		sef3.getInflexions().add(5, 0.75);
		//sef3.getInflexions().add(6, 1);
		//sef3.getInflexions().add(7, 1);
		//sef3.getInflexions().add(9, 0);

		//sef1.printInflexions();
		//sef2.printInflexions();
//		sef3.printInflexions();
//		sef4.printInflexions();
		System.out.println("Apres avoir fait la normalisation: \n");
		 */
		//		SefIntersection.normalizeSerie(sef1.getInflexions(),sef1.getBorneInf(),sef1.getBorneSup(),
		//			sef2.getInflexions(),sef2.getBorneInf(),sef2.getBorneSup());
		//		SefIntersection.normalizeSerie(sef2.getInflexions(),sef2.getBorneInf(),sef2.getBorneSup(),
		//					sef1.getInflexions(),sef1.getBorneInf(),sef1.getBorneSup());

		//		SefIntersection.normalizeSerie(sef3.getInflexions(),sef3.getBorneInf(),sef3.getBorneSup(),
		//				sef2.getInflexions(),sef2.getBorneInf(),sef2.getBorneSup());
		//		SefIntersection.normalizeSerie(sef2.getInflexions(),sef2.getBorneInf(),sef2.getBorneSup(),
		//				sef3.getInflexions(),sef3.getBorneInf(),sef3.getBorneSup());

		SEF sef3 = new SEF(-30, 60, new XYSeries("sef3"));
		SEF sef4 = new SEF(-30, 0, new XYSeries("sef4"));

		sef4.getInflexions().add(-3,0);
		sef4.getInflexions().add(0,1);
		//sef4.getInflexions().add(1,1);

		sef3.getInflexions().add(-6, 0);
		sef3.getInflexions().add(-4, 0.75);
		sef3.getInflexions().add(2, 0.75);
		sef3.getInflexions().add(5, 0.75);
		
		sef3.printInflexions();
		sef4.printInflexions();
		try {
			SefDiscretizer.normalizeSerie(sef3.getInflexions(),sef3.getBorneInf(),sef3.getBorneSup(),
					sef4.getInflexions(),sef4.getBorneInf(),sef4.getBorneSup());
			sef3.printInflexions();
			sef4.printInflexions();
		} catch (NormalizationException e) {
			// Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getErrorMsg());
		}

//		sef3.printInflexions();
//		sef4.printInflexions();
		//sef1.printInflexions();
		//sef2.printInflexions();
//		finally{
//			//sef3.printInflexions();
//			//sef4.printInflexions();
//		}



		/*
		 * Essai de la copie d'une liste XYSeries, celle ci a l'air de fonctionner bien
		 *  ====>sera utile plus tard!!!!
		 *//*
		XYSeries listePts5 ;
		try {
			listePts5 = sef1.getInflexions().createCopy(0,sef1.getInflexions().getItemCount()-1);
			listePts5.setKey("sef5");
			SEF sef5 = new SEF(sef1.getBorneInf(), sef1.getBorneSup(), listePts5);
		//	sef5.printInflexions();
		//	sef1.printInflexions();
			sef5.getInflexions().add(10, 20);

		//	sef5.printInflexions();
		//	sef1.printInflexions();
		} catch (CloneNotSupportedException e) {
			//  Auto-generated catch block
			e.printStackTrace();
			System.out.println("Il y a eu un souci dans la copie de la liste de points du sef "
					+sef1.getInflexions().getKey());
		}
*/
	}

}
