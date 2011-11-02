package manipSef;

import tools.Norme;
import tools.OperationEnsembliste;
import exceptions.UnknownNormeException;
import exceptions.UnknownOperationException;


/**
 * *La classe permettant d'effectuer les trois opérations ensemblistes de base, soit:
 *  => Le complément
 *  => L'intersection, avec l'une des trois T-normes laissés au choix de l'utilisateur:
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
	 * Methode permettant d'effectuer l'une des deux opérations ensemblistes 
	 * intersection ou union, sur les deux sous ensembles flous passés en paramètre
	 * @param sef1 premier sous ensemble flou
	 * @param sef2 deuxieme sous ensemble flou
	 * @param norme  T -(co) norme selon laquelle doit etre faite l'opération
	 * @param operation intersection ou union 
	 * @return le sous ensemble flou résultat de l'opération (union ou intersection) effectuée
	 * @throws UnknownNormeException si la norme passée n'est pas correcte
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
	 * Méthode permettant d'obtenir le complémentaire d'un sous ensemble flou
	 * @param sef1 le sous ensemble flou à complémenter
	 * @return le sous ensemble flou complément
	 */
	public static SEF getComplement(SEF sef1){
		return SefComplement.getComplement(sef1);
	}
}
