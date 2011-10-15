package ihm;

import org.jfree.ui.RefineryUtilities;

public class Launcher {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		FenetrePrincipale fp = new FenetrePrincipale();
		
		/*
		 * Demo pour le JFreeChart...pas forcement voire pas du tout dans le main
		 * a deplacer plus tard (cette fenetre sera cree lorsque l'on demandera l'affichage d'un sef...
		 */
		final FenetreGeometrique demo = new FenetreGeometrique("Manipulation des Sous Ensembles Flous");
		//La string passée en param du constructeur est le titre de la fenetre
        demo.pack();//? Que fait cette commande?
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);


	}

}
