package exceptions;

public class SegmentAboveException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3677631093385778753L;
	/**
	 * 1 si la premiere droite est au dessus de l'autre
	 * 0 si c'est linverse
	 */
	private boolean firstAbove;
	
	public SegmentAboveException(boolean above){
		firstAbove=above;
	}
	
	public boolean getFirstAbove (){
		return firstAbove;
	}

}
