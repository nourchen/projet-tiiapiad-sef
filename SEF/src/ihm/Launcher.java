package ihm;

import org.jfree.ui.RefineryUtilities;

public class Launcher {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		FenetrePrincipale fp = new FenetrePrincipale();
		
		//Demo
		final FenetreGeometrique demo = new FenetreGeometrique("XY Series Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);


	}

}
