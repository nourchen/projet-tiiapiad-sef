package exceptions;

/**
 * Classe exception permettant de gérer les cas 
 * où la normalisation ne s'est pas déroulée correctement
 * 
 * @author Sylvia Vieira
 *
 */
public class NormalizationException extends Exception {

	/**
	 * identifiant série
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
	 * @param message le message d'erreur spécifique associé a la levée de l'exception
	 */
	public NormalizationException(String message){
		errorMsg=message;
	}
	/**
	 * getter sur le champ message d'erreur
	 * @return le message d'erreur associé à l'exception
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

}
