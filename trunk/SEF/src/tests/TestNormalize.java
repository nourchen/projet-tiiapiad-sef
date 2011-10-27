package tests;

import manipSef.SEF;
import manipSef.SefIntersection;

import org.jfree.data.xy.XYSeries;

public class TestNormalize {

	public static void main(String[] args) {
		
		SEF sef1 = new SEF(-30, 60, new XYSeries("sef1"));
		SEF sef2 = new SEF(-30, 60, new XYSeries("sef2"));
		SEF sef3 = new SEF(-30, 60, new XYSeries("sef3"));
		 
		sef1.getInflexions().add(-1.5,0);
		sef1.getInflexions().add(1.25,1);
		sef1.getInflexions().add(4.2,0.75);
		sef1.getInflexions().add(5,0);
		
		sef2.getInflexions().add(-0.5,0);
		sef2.getInflexions().add(0,1);
		sef2.getInflexions().add(0.5,0);
		
		sef2.getInflexions().add(3,0);
		sef2.getInflexions().add(4,1);
		sef2.getInflexions().add(5,0);
		sef2.getInflexions().add(6, 0.7);
		
		sef1.printInflexions();
		sef2.printInflexions();
		//sef3.printInflexions();
		System.out.println("Apres avoir fait la normalisation: \n");
		
//		SefIntersection.normalizeSerie(sef1.getInflexions(),sef1.getBorneInf(),sef1.getBorneSup(),
//			sef2.getInflexions(),sef2.getBorneInf(),sef2.getBorneSup());
		SefIntersection.normalizeSerie(sef2.getInflexions(),sef2.getBorneInf(),sef2.getBorneSup(),
					sef1.getInflexions(),sef1.getBorneInf(),sef1.getBorneSup());
		
//		SefIntersection.normalizeSerie(sef3.getInflexions(),sef3.getBorneInf(),sef3.getBorneSup(),
//				sef2.getInflexions(),sef2.getBorneInf(),sef2.getBorneSup());
//		SefIntersection.normalizeSerie(sef2.getInflexions(),sef2.getBorneInf(),sef2.getBorneSup(),
//				sef3.getInflexions(),sef3.getBorneInf(),sef3.getBorneSup());
		
		sef1.printInflexions();
		sef2.printInflexions();
		//sef3.printInflexions();
		
	}
	
}
