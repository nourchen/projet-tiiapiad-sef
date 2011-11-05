package ihm;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controllers.ControllerFenetreSauver;

import manipSef.SEF;

public class FenetreSauver extends JFrame {

	private JList couleursJList;
	private JLabel label;
	private JButton sauver;
	private String[] total;
	
	
	private ArrayList<SEF> mesSEF;
	
	private final static String[] TAB_COULEURS = 
	{ "blanc", "jaune", "rouge", "vert", "bleu", "orange", "noir", 
	  "bleu clair", "rouge clair", "vert clair" };
	
	
	public FenetreSauver(ArrayList<SEF> mesSEF){
		
		Object tab[] = mesSEF.toArray();
		mesSEF.get(0).getInflexions().getDescription();
		String[] tab2 = {"SEF1",(String) mesSEF.get(0).getInflexions().getKey()};
		System.out.println("yakoi "+mesSEF.size());
		total = recupNom(mesSEF);
		
		couleursJList = new JList(total);
		// nb de lignes visbles
		couleursJList.setVisibleRowCount( 5 );
		// autorise la selection multiple
		couleursJList.setSelectionMode( ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
		// ajoute une scrollBar
		JScrollPane spList = new JScrollPane( couleursJList );
		// ajoute le composant dans la fenêtre
		add( spList, BorderLayout.CENTER );
		
		//--- définition du libellé
		
		label = new JLabel( " Sélection: [Ctrl]+[click] ou [Shift]+[click] " );
		add( label, BorderLayout.NORTH );
		
		//--- définition bouton
		sauver = new JButton( "Sauver les SEF séléctionnés" );
//		button.addActionListener(new SelectionAction() );
		add(sauver, BorderLayout.SOUTH );
		setVisible(true);
		pack();
		new ControllerFenetreSauver(this,mesSEF);
	}
	
	public String[] recupNom(ArrayList<SEF> mesSEF){
		String[] res = new String[mesSEF.size()];
		for(int i =0; i<mesSEF.size();i++){
			res[i]= (String)mesSEF.get(i).getInflexions().getKey();
		}
		
		return res;
	}

	public JList getCouleursJList() {
		return couleursJList;
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
