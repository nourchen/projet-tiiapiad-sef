package ihm;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;


import controllers.ControllerFenetreSauver;

import manipSef.SEF;

/**
 * Classe gérant le contenu de la fenetre Sauver
 * @author Frederic
 *
 */

public class FenetreSauver extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3964985490582034705L;
	private JList jlist;
	private JButton sauver;
	private String[] total;
	
	
	private ArrayList<SEF> mesSEF;
	
	/**
	 * Construteur de la FentreOnglet. Utilise mesSEF pour remplir le contenu des JList
	 * @param mesSEF
	 */
	public FenetreSauver(ArrayList<SEF> mesSEF){
		setTitle("Sauvegarder...");
		setSize(300,200);
		//Construction de la Jlist
		total = recupNom(mesSEF);	
		jlist = new JList(total);
		JScrollPane spList = new JScrollPane( jlist );
		add( spList, BorderLayout.CENTER );
//		label = new JLabel( " Sélection: [Ctrl]+[click] ou [Shift]+[click] " );
//		add( label, BorderLayout.NORTH );
		sauver = new JButton( "Sauver les SEF séléctionnés" );
		add(sauver, BorderLayout.SOUTH );
		setVisible(true);
	//	pack();
		new ControllerFenetreSauver(this,mesSEF);
	}
	/**
	 * Cette fonction permet de recuperer les noms des SEF pour les
	 * tocker dans un tableau (utile pour le contenu des JList)
	 * @param mesSEF contient les SEF chargé dans le programme
	 * @return un tableau de String avec le nom des SEF
	 */
	public String[] recupNom(ArrayList<SEF> mesSEF){
		String[] res = new String[mesSEF.size()];
		for(int i =0; i<mesSEF.size();i++){
			res[i]= (String)mesSEF.get(i).getInflexions().getKey();
		}
		
		return res;
	}

	public JList getjlist() {
		return jlist;
	}

	public JButton getSauver() {
		return sauver;
	}

	public String[] getTotal() {
		return total;
	}

	public ArrayList<SEF> getMesSEF() {
		return mesSEF;
	}
	
	
	
	
}
