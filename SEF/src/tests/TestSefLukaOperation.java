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
 * selon la T-norme de Lukasiewicz
 * @author Sylvia Vieira
 *
 */
public class TestSefLukaOperation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SEF sef1 = new SEF(-30, 60, new XYSeries("sef1"));
		SEF sef2 = new SEF(-30, 60, new XYSeries("sef2"));
//		SEF sef3 = new SEF(-30, 60, new XYSeries("sef3"));
//		SEF sef4 = new SEF(-30, 60, new XYSeries("sef4"));
		SEF interSef;

		sef1.getInflexions().add(-1.5,0);
		sef1.getInflexions().add(1.25,1);
		sef1.getInflexions().add(4.2,0.75);
		sef1.getInflexions().add(5,0);
		
		sef2.getInflexions().add(0, 0);
		sef2.getInflexions().add(0.5, 1);
		sef2.getInflexions().add(1, 0);
		
		try {
			interSef = SefManager.getResultOperation(sef1, sef2, Norme.LUKASIEWICZ,OperationEnsembliste.UNION);
			interSef.printInflexions();
		} catch (UnknownNormeException e) {
			//  Auto-generated catch block
			System.out.println("La norme n'est pas connue!");
		} catch (UnknownOperationException e) {
			//  Auto-generated catch block
			System.out.println("L'opération n'est pas connue!");
		} catch (NormalizationException e) {
			System.out.println("La normalisation ne s'est pas bien passée!");
		}
		
	}

}
