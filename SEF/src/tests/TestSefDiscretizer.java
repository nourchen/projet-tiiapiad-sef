package tests;

import ihm.FenetreGeometrique;

import java.util.ArrayList;

import manipSef.SEF;
import manipSef.SefDiscretizer;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

public class TestSefDiscretizer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<SEF> listeDeSefs = new ArrayList<SEF>();
		XYSeries listPoints = new XYSeries("sef1");
		XYSeries listPoints2 = new XYSeries("sef2");
		
		//System.out.println("Coucou je suis la!");
		listPoints.add(-1.5,0);
		listPoints.add(1.25,1);
		listPoints.add(4.2,0.75);
		listPoints.add(5,0);
		
		listPoints2.add(1,0);
		listPoints2.add(4,1);
		listPoints2.add(6,1);
		listPoints2.add(8,0);
		
		
		SEF sef=new SEF(-20, 60, listPoints);
		SEF sef2 = new SEF(-20,60,listPoints2);
		//System.out.println("Et la!");
		listeDeSefs.add(sef);
		listeDeSefs.add(sef2);
		SefDiscretizer sefM = new SefDiscretizer(listeDeSefs, -5,15);
		//System.out.println("Coucou je suis ENCORE la!");
		XYSeriesCollection mesSefs = sefM.createMySefCollection();
		FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
		//La string passée en param du constructeur est le titre de la fenetre
		frame.pack();//? Que fait cette commande?
		RefineryUtilities.centerFrameOnScreen(frame);
		frame.setVisible(true);
		
	}

}
