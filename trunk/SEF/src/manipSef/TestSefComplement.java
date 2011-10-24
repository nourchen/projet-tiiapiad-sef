package manipSef;

import ihm.FenetreGeometrique;

import java.util.ArrayList;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

public class TestSefComplement {

	public static void main(String[] args) {
		XYSeries sef1 = new XYSeries("sef1");
		XYSeries sef2 = new XYSeries("sef2");
		 
		sef1.add(-1.5,0);
		sef1.add(1.25,1);
		sef1.add(4.2,0.75);
		sef1.add(5,0);
		
		sef2.add(-0.5,0);
		sef2.add(0,1);
		sef2.add(0.5,0);
		
		
		XYSeriesCollection mesSefs= new XYSeriesCollection();
		mesSefs.addSeries(sef1);
		mesSefs.addSeries(sef2);
		mesSefs.addSeries(sef1);
		
		FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
		//La string passée en param du constructeur est le titre de la fenetre
		frame.pack();//? Que fait cette commande?
		RefineryUtilities.centerFrameOnScreen(frame);
		frame.setVisible(true);
	}
}
