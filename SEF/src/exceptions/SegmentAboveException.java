package exceptions;

/**
 * Classe exception permettant de renvoyer une information différente
 * du type de retour attendu pour la fonction segmentIntersection
 * Ici dans le cas où il n'y a pas d'intersection
 * car l'un des segments est au dessus de l'autre
 * 
 * @author Sylvia Vieira
 *
 */
public class SegmentAboveException extends Exception{

	/**
	 * identifiant série
	 */
	private static final long serialVersionUID = 3677631093385778753L;
}
