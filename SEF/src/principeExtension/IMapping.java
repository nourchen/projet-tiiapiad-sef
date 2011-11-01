package principeExtension;

/**
 * FuzzyToolbox
 *
 * File: IMapping.java
 * Created on 2011-09-16
 */

import java.util.ArrayList;

/**
 * Interface de définition des fonctions mathématiques 
 * pour le projet flou 2011
 * 
 * @author Equipe TIIAPIAD (tiiapflou@poleia.lip6.fr)
 * @version 2011/09/16
 * 
 */

public interface IMapping {

	/**
	 * Calcule l'image d'une valeur
	 * 
	 * @param x valeur réelle 
	 * @return image de x par la fonction
	 */
	public abstract double compute(double x);

	/**
	 * Rend la liste des antécédents d'une valeur
	 * 
	 * @param x valeur réelle 
	 * @return liste des antécédents de x
	 */
	public abstract ArrayList<Double> reverse(double x);

	/**
	 * Donne l'expression de la fonction
	 * 
	 * @return une chaine avec l'expression de la fonction
	 */
	public abstract String toString();

}


