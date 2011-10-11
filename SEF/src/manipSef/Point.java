package manipSef;
/**
 * 
 * @author Sylvia Vieira
 *
 */
public class Point {

	/**
	 * abscisse du Point
	 */
	private double x;
	/**
	 * ordonnee du Point
	 */
	private double y;
	
	public Point(double x,double y){
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
