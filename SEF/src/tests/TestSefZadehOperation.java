package tests;

import manipSef.SEF;
import manipSef.SefManager;

import org.jfree.data.xy.XYSeries;

import tools.Norme;
import tools.OperationEnsembliste;

import exceptions.NormalizationException;
import exceptions.UnknownNormeException;
import exceptions.UnknownOperationException;

/**
 * Classe permettant de tester l'intersection entre deux sous ensembles flous
 * selon la T-norme de Zadeh
 * @author Sylvia Vieira
 *
 */
public class TestSefZadehOperation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		SEF sef1 = new SEF(10, 20, new XYSeries("sef1"));
		SEF sef2 = new SEF(10, 20, new XYSeries("sef2"));
//		SEF sef3 = new SEF(-30, 60, new XYSeries("sef3"));
//		SEF sef4 = new SEF(-30, 60, new XYSeries("sef4"));
		SEF interSef;
//
//		sef1.getInflexions().add(-1.5,0);
//		sef1.getInflexions().add(1.25,1);
//		sef1.getInflexions().add(4.2,0.75);
//		sef1.getInflexions().add(5,0);
//		
//		sef2.getInflexions().add(0, 0);
//		sef2.getInflexions().add(0.5, 1);
//		sef2.getInflexions().add(1, 0);
		
		sef1.getInflexions().add(12, 0);
		sef1.getInflexions().add(13, 1);
		sef1.getInflexions().add(14, 1);
		sef1.getInflexions().add(15, 0);
		
		sef2.getInflexions().add(12, 0);
		sef2.getInflexions().add(13, 1);
		sef2.getInflexions().add(15, 0);
		
		try {
			interSef = SefManager.getResultOperation(sef1, sef2, Norme.ZADEH,OperationEnsembliste.INTERSECTION);
			interSef.printInflexions();
		} catch (UnknownNormeException e) {
			//  Auto-generated catch block
			System.out.println("La norme n'est pas connue!");
		} catch (UnknownOperationException e) {
			//  Auto-generated catch block
			System.out.println("L'op�ration n'est pas connue!");
		} catch (NormalizationException e) {
			System.out.println("La normalisation n'a pas fonctionn�!");
		}
		System.out.println(Norme.ZADEH);
		
		
	}

}
