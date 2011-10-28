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
	 * 
	 */
	private static final long serialVersionUID = -6416172822069208940L;
	private String errorMsg;
	
	public NormalizationException() {
		
	}
	public NormalizationException(String message){
		errorMsg=message;
	}
	public String getErrorMsg() {
		return errorMsg;
	}

}
