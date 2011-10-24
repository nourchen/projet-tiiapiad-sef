package manipSef;

import ihm.FenetreGeometrique;



import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

public class TestSefComplement {

	public static void main(String[] args) {
		SEF sef1 = new SEF(-30, 60, new XYSeries("sef1"));
		SEF sef2 = new SEF(-30, 60, new XYSeries("sef2"));
		 
		sef1.getInflexions().add(-1.5,0);
		sef1.getInflexions().add(1.25,1);
		sef1.getInflexions().add(4.2,0.75);
		sef1.getInflexions().add(5,0);
		
		sef2.getInflexions().add(-0.5,0);
		sef2.getInflexions().add(0,1);
		sef2.getInflexions().add(0.5,0);
		
		
		XYSeriesCollection mesSefs= new XYSeriesCollection();
		mesSefs.addSeries(sef1.getInflexions());
		mesSefs.addSeries(sef2.getInflexions());
		mesSefs.addSeries(SefComplement.getComplement(sef1).getInflexions());
		mesSefs.addSeries(SefComplement.getComplement(sef2).getInflexions());
		
		FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
		//La string passée en param du constructeur est le titre de la fenetre
		frame.pack();//? Que fait cette commande?
		RefineryUtilities.centerFrameOnScreen(frame);
		frame.setVisible(true);
	}
}
