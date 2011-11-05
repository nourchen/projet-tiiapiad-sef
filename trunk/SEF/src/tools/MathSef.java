package tools;

import org.jfree.data.xy.XYDataItem;

import exceptions.SegmentAboveException;
import exceptions.SegmentsConfondusException;

/**
 * Classe permettant d'effectuer des opération mathématiques
 * Exemple: vérifier l'intersection entre deux segments
 * @author Sylvia Vieira
 *
 */
public class MathSef {

	
	/**
	 * Cette méthode permet de vérifier la position relative de deux segments dont les extrémités
	 * respectives ont les memes abscisses.
	 * @param a1 coefficient directeur du premier segment
	 * @param b1 ordonnée à l'origine du premier segment
	 * @param a2 coefficient directeur du second segment
	 * @param b2 ordonnée à l'origine du second segment
	 * @param inf abscisse du point gauche (aussi bien celle du premier que du second segment)
	 * @param sup abscisse du point droit
	 * @return le point d'intersection s'il en existe un
	 * @throws SegmentsConfondusException si les deux segments sont confondus (même couples (a,b) )
	 * @throws SegmentAboveException s'il n'y a aucune intersection car l'un des segments est au dessus de l'autre
	 */
	public static XYDataItem segmentIntersection(double a1,double b1, double a2, double b2,double inf, double sup)
			throws SegmentsConfondusException,SegmentAboveException{
		if (a1==a2 && b1 ==b2){
			throw new SegmentsConfondusException();
		}
		double y1gauche,y2gauche,y1droit,y2droit;
		y1gauche=a1*inf +b1;
		y1droit=a1*sup +b1;
		y2gauche=a2*inf +b2;
		y2droit=a2*sup +b2;
		if (y1gauche > y2gauche && y1droit > y2droit){
			throw new SegmentAboveException();
		}
		if (y1gauche < y2gauche && y1droit < y2droit){
			throw new SegmentAboveException();
		}
		double xCommun = (b2 - b1) / (a1 - a2);
		XYDataItem ptCommun=new XYDataItem(xCommun, a1 * xCommun + b1);

		return ptCommun;
	}
}
