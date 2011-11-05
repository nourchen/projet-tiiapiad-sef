package exceptions;

/**
 * Classe exception permettant de g�rer les cas 
 * o� la normalisation ne s'est pas d�roul�e correctement
 * 
 * @author Sylvia Vieira
 *
 */
public class NormalizationException extends Exception {

	/**
	 * identifiant s�rie
	 */
	private static final long serialVersionUID = -6416172822069208940L;
	/**
	 * message d'erreur
	 */
	private String errorMsg;
	/**
	 * Constructeur vide
	 */
	public NormalizationException() {
		
	}
	/**
	 * Constructeur de la classe NormalizationException
	 * @param message le message d'erreur sp�cifique associ� a la lev�e de l'exception
	 */
	public NormalizationException(String message){
		errorMsg=message;
	}
	/**
	 * getter sur le champ message d'erreur
	 * @return le message d'erreur associ� � l'exception
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

}
