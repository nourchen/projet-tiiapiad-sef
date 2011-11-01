package tests;

import manipSef.Norme;
import manipSef.SEF;
import manipSef.SefIntersection;

import org.jfree.data.xy.XYSeries;

import exceptions.UnknownNormeException;

/**
 * Classe permettant de tester l'intersection entre deux sous ensembles flous
 * selon la T-norme de Zadeh
 * @author Sylvia Vieira
 *
 */
public class TestSefIntersectionZadeh {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		SEF sef1 = new SEF(-30, 60, new XYSeries("sef1"));
		SEF sef2 = new SEF(-30, 60, new XYSeries("sef2"));
		SEF sef3 = new SEF(-30, 60, new XYSeries("sef3"));
		SEF sef4 = new SEF(-30, 60, new XYSeries("sef4"));
		SEF interSef;

		sef1.getInflexions().add(-1.5,0);
		sef1.getInflexions().add(1.25,1);
		sef1.getInflexions().add(4.2,0.75);
		sef1.getInflexions().add(5,0);
		
		sef2.getInflexions().add(0, 0);
		sef2.getInflexions().add(0.5, 1);
		sef2.getInflexions().add(1, 0);
		
		try {
			interSef = SefIntersection.getIntersection(sef1, sef2, Norme.ZADEH);
			interSef.printInflexions();
		} catch (UnknownNormeException e) {
			// TODO Auto-generated catch block
			System.out.println("La norme n'est pas connue!");
		}
		
		
		
		
	}

}
