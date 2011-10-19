package manipSef;

import ihm.FenetreGeometrique;

import java.util.ArrayList;

import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

public class TestSefManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<SEF> listeDeSefs = new ArrayList<SEF>();
		ArrayList<Point> listPoints = new ArrayList<Point>();
		
		ArrayList<Point> listPoints2 = new ArrayList<Point>(); 
		//System.out.println("Coucou je suis la!");
		listPoints.add(new Point(-1.5,0));
		listPoints.add(new Point(1.25,1));
		listPoints.add(new Point(4.2,0.75));
		listPoints.add(new Point(5,0));
		
		listPoints2.add(new Point(1,0));
		listPoints2.add(new Point(4,1));
		listPoints2.add(new Point(6,1));
		listPoints2.add(new Point(8,0));
		
		SEF sef=new SEF(-20, 60, listPoints);
		SEF sef2 = new SEF(-20,60,listPoints2);
		//System.out.println("Et la!");
		listeDeSefs.add(sef);
		listeDeSefs.add(sef2);
		SefManager sefM = new SefManager(listeDeSefs, -5,15);
		//System.out.println("Coucou je suis ENCORE la!");
		XYSeriesCollection mesSefs = sefM.createMySefCollection();
		FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
		//La string passée en param du constructeur est le titre de la fenetre
		frame.pack();//? Que fait cette commande?
		RefineryUtilities.centerFrameOnScreen(frame);
		frame.setVisible(true);
		
	}

}
