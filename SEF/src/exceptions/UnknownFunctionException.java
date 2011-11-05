package exceptions;

/**
 * Exception générée lorque l'on a cherche a etendre un sous ensemble flou
 * par une fonction d'extension n'est pas connue
 * (pas présente dans le catalogue -enum- des fonctions)
 * 
 * @author Sylvia Vieira
 *
 */
public class UnknownFunctionException extends Exception {

	/**
	 * identifiant série
	 */
	private static final long serialVersionUID = 3109104373722520418L;

}
