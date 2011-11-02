package manipSef;

import tools.Norme;
import tools.OperationEnsembliste;
import exceptions.UnknownNormeException;
import exceptions.UnknownOperationException;


/**
 * *La classe permettant d'effectuer les trois op�rations ensemblistes de base, soit:
 *  => Le compl�ment
 *  => L'intersection, avec l'une des trois T-normes laiss�s au choix de l'utilisateur:
 *       Norme de Zadeh, LUKASIEWICZ, ou probabiliste
 *  => L'union, avec l'une des trois T-conormes (les memes que les T-normes)
 *  
 * @author Sylvia Vieira
 *
 */
public class SefManager {

	public SefManager(){

	}
	/**
	 * Methode permettant d'effectuer l'une des deux op�rations ensemblistes 
	 * intersection ou union, sur les deux sous ensembles flous pass�s en param�tre
	 * @param sef1 premier sous ensemble flou
	 * @param sef2 deuxieme sous ensemble flou
	 * @param norme  T -(co) norme selon laquelle doit etre faite l'op�ration
	 * @param operation intersection ou union 
	 * @return le sous ensemble flou r�sultat de l'op�ration (union ou intersection) effectu�e
	 * @throws UnknownNormeException si la norme pass�e n'est pas correcte
	 */
	public static SEF getResultOperation(SEF sef1, SEF sef2,Norme norme,OperationEnsembliste operation)
			throws UnknownNormeException, UnknownOperationException{

		SEF result;

		switch (norme)
		{
		case LUKASIEWICZ: result = SefLukaOperation.getLukaResult(sef1, sef2,operation); return result;
		case PROBA: result = SefProbaOperation.getProbaResult(sef1, sef2,operation); return result;
		case ZADEH: result = SefZadehOperation.getZadehResult(sef1, sef2,operation); return result;
		default: throw new UnknownNormeException();

		}
	}

	/**
	 * M�thode permettant d'obtenir le compl�mentaire d'un sous ensemble flou
	 * @param sef1 le sous ensemble flou � compl�menter
	 * @return le sous ensemble flou compl�ment
	 */
	public static SEF getComplement(SEF sef1){
		return SefComplement.getComplement(sef1);
	}
}
