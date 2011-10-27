package ihm;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;


/**
 * Classe repr�sentant un rep�re, permettant le rendu graphique d'un sous ensemble flou
 * @author Sylvia Vieira
 *
 */

public class FenetreGeometrique extends ApplicationFrame {

	//A priori => utiliser XY Plot de JFreeChart?

	//Pour le moment on se contentera d'un graphique simple.

	/*Id�es "si on a le temps":
	 * =>afficher plusieurs "courbes" (en fait une infinit� de droites) sur le m�me graphique
	 *         avec couleur et l�gendes associ�es
	 * =>Permettre � l'utilisateur de voir les coordonn�es d'un point s'afficher lors du passage du curseur dessus
	 * (quelque part dans la fen�tre, peu importe ou, � d�finir plus tard...)
	 */
	
	private XYSeriesCollection sefsCollec;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A demonstration application showing an XY series containing a null value.
	 *
	 * @param title  the frame title.
	 */
	public FenetreGeometrique(final String title,XYSeriesCollection sefsCollec) {
		super(title);//title=titre de la FENETRE!!
		this.sefsCollec=sefsCollec;
		
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Un sous ensemble flou",//Le titre du graphique
				//TODO: il serait plus interessant de le passer en param du constructeur et non en dur => a voir plus tard
				"X", //titre des abscisses
				"Y", //titre des ordonnees
				sefsCollec,//La collection de XYSeries a afficher
				PlotOrientation.VERTICAL,
				true,  //Show legend
				true, // Tools Tips
				false  //urls
				);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);

	}
}
